package com.techie.microservices.menu.infrastructure.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class RepositoryBase<T, K extends Serializable> implements IRepositoryBase<T, K> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> entityClass;

    protected RepositoryBase(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> findById(K id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll(boolean trackChanges) {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);

        if (!trackChanges) {
            query.setHint("org.hibernate.readOnly", true); // Giống AsNoTracking()
        }

        return query.getResultList();
    }


    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public List<T> saveAll(List<T> entities) {
        for (T entity : entities) {
            entityManager.persist(entity);
        }
        return entities;
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void updateAll(List<T> entities) {
        for (T entity : entities) {
            entityManager.merge(entity);
        }
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public void deleteById(K id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    public void deleteAll(List<T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }
}