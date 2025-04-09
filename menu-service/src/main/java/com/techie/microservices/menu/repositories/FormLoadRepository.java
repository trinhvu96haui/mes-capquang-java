package com.techie.microservices.menu.repositories;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.FormLoadParameters;
import com.techie.microservices.menu.entities.FormLoad;
import com.techie.microservices.menu.extensions.QueryableExtensions;
import com.techie.microservices.menu.infrastructure.common.RepositoryBase;
import com.techie.microservices.menu.repositories.interfaces.IFormLoadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@Transactional
public class FormLoadRepository extends RepositoryBase<FormLoad, Long> implements IFormLoadRepository {

    public FormLoadRepository(EntityManager entityManager) {
        super(FormLoad.class);
        this.entityManager = entityManager;
    }

    @Override
    public PagingDto<FormLoad> getFormLoad(FormLoadParameters parameters) {
        Stream<FormLoad> query = findAll().stream();

        // Áp dụng điều kiện tìm kiếm theo keyword nếu có sử dụng whereIf
        query = new QueryableExtensions<>(query)
                .whereIf(parameters.getKeyword() != null,
                        p -> p.getName().toLowerCase().contains(parameters.getKeyword().toLowerCase()) ||
                                p.getName().toLowerCase().contains(parameters.getKeyword().toLowerCase()))
                .getStream(); // Lấy lại stream sau khi lọc từ whereIf

        // Áp dụng sắp xếp giảm dần theo trường Created
        query = query.sorted(Comparator.comparing(FormLoad::getCreated).reversed());

        // Tiến hành phân trang và trả về kết quả
        return new QueryableExtensions<>(query)
                .paginate(parameters);
    }

    @Override
    public Optional<FormLoad> findByFormLoadId(int formLoadId) {
        TypedQuery<FormLoad> query = entityManager.createQuery(
                "SELECT p FROM FormLoad p WHERE p.formLoadId = :formLoadId", FormLoad.class);
        query.setParameter("formLoadId", formLoadId);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<FormLoad> findByFormLoadIds(List<Integer> formLoadIds) {
        TypedQuery<FormLoad> query = entityManager.createQuery(
                "SELECT p FROM FormLoad p WHERE p.formLoadId IN :formLoadIds", FormLoad.class);
        query.setParameter("formLoadIds", formLoadIds);
        return query.getResultList();
    }

    @Override
    public List<FormLoad> findByNames(List<String> names) {
        TypedQuery<FormLoad> query = entityManager.createQuery(
                "SELECT p FROM FormLoad p WHERE p.name IN :names", FormLoad.class);
        query.setParameter("names", names);
        return query.getResultList();
    }

    @Override
    public Optional<FormLoad> findByName(String name) {
        var sql = "SELECT p FROM FormLoad p WHERE p.name = :name";
        TypedQuery<FormLoad> query = entityManager.createQuery(sql, FormLoad.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public FormLoad create(FormLoad formLoad) {
        entityManager.persist(formLoad);
        return formLoad;
    }

    @Override
    public List<FormLoad> createMany(List<FormLoad> manyFormLoad) {
        for (FormLoad formLoad : manyFormLoad) {
            entityManager.persist(formLoad);
        }
        return manyFormLoad;
    }

    @Override
    public FormLoad update(FormLoad formLoad) {
        return entityManager.merge(formLoad);
    }

    @Override
    public List<FormLoad> updateMany(List<FormLoad> manyFormLoad) {
        for (FormLoad formLoad : manyFormLoad) {
            entityManager.merge(formLoad);
        }
        return manyFormLoad;
    }

    @Override
    public void delete(Long id) {
        FormLoad formLoad = entityManager.find(FormLoad.class, id);
        if (formLoad != null) {
            entityManager.remove(formLoad);
        }
    }

    @Override
    public void deleteMany(List<Long> ids) {
        for (Long id : ids) {
            delete(id);
        }
    }

}
