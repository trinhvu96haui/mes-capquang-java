package com.techie.microservices.menu.repositories;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ModelParameters;
import com.techie.microservices.menu.entities.Model;
import com.techie.microservices.menu.extensions.QueryableExtensions;
import com.techie.microservices.menu.infrastructure.common.RepositoryBase;
import com.techie.microservices.menu.repositories.interfaces.IModelRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@Transactional
public class ModelRepository extends RepositoryBase<Model, Long> implements IModelRepository {

    public ModelRepository(EntityManager entityManager) {
        super(Model.class);
        this.entityManager = entityManager;
    }

    @Override
    public PagingDto<Model> getModels(ModelParameters parameters) {
        Stream<Model> query = findAll().stream();

        return new QueryableExtensions<>(query)
                .whereIf(parameters.getKeyword() != null,
                        p -> p.getName().toLowerCase().contains(parameters.getKeyword().toLowerCase()) ||
                                p.getValue().toLowerCase().contains(parameters.getKeyword().toLowerCase()))
                .paginate(parameters);
    }

    @Override
    public Optional<Model> findByModelId(int modelId) {
        TypedQuery<Model> query = entityManager.createQuery(
                "SELECT p FROM Model p WHERE p.modelId = :modelId", Model.class);
        query.setParameter("modelId", modelId);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<Model> findByModelIds(List<Integer> modelIds) {
        TypedQuery<Model> query = entityManager.createQuery(
                "SELECT p FROM Model p WHERE p.modelId IN :modelIds", Model.class);
        query.setParameter("modelIds", modelIds);
        return query.getResultList();
    }

    @Override
    public List<Model> findByValues(List<String> values) {
        TypedQuery<Model> query = entityManager.createQuery(
                "SELECT p FROM Model p WHERE p.value IN :values", Model.class);
        query.setParameter("values", values);
        return query.getResultList();
    }

    @Override
    public Optional<Model> findByValue(String value) {
        TypedQuery<Model> query = entityManager.createQuery(
                "SELECT p FROM Model p WHERE p.value = :value", Model.class);
        query.setParameter("value", value);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Model create(Model model) {
        entityManager.persist(model);
        return model;
    }

    @Override
    public List<Model> createMany(List<Model> models) {
        for (Model model : models) {
            entityManager.persist(model);
        }
        return models;
    }

    @Override
    public Model update(Model model) {
        return entityManager.merge(model);
    }

    @Override
    public List<Model> updateMany(List<Model> models) {
        for (Model model : models) {
            entityManager.merge(model);
        }
        return models;
    }

    @Override
    public void delete(Long id) {
        Model model = entityManager.find(Model.class, id);
        if (model != null) {
            entityManager.remove(model);
        }
    }

    @Override
    public void deleteMany(List<Long> ids) {
        for (Long id : ids) {
            delete(id);
        }
    }

}
