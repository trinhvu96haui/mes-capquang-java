package com.techie.microservices.menu.repositories;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.GroupProcessParameters;
import com.techie.microservices.menu.entities.GroupProcess;
import com.techie.microservices.menu.extensions.QueryableExtensions;
import com.techie.microservices.menu.infrastructure.common.RepositoryBase;
import com.techie.microservices.menu.repositories.interfaces.IGroupProcessRepository;
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
public class GroupProcessRepository extends RepositoryBase<GroupProcess, Long> implements IGroupProcessRepository {

    public GroupProcessRepository(EntityManager entityManager) {
        super(GroupProcess.class);
        this.entityManager = entityManager;
    }

    @Override
    public PagingDto<GroupProcess> getGroupProcess(GroupProcessParameters parameters) {
        Stream<GroupProcess> query = findAll().stream();

        // Áp dụng điều kiện tìm kiếm theo keyword nếu có sử dụng whereIf
        query = new QueryableExtensions<>(query)
                .whereIf(parameters.getKeyword() != null,
                        p -> p.getName().toLowerCase().contains(parameters.getKeyword().toLowerCase()) ||
                                p.getCode().toLowerCase().contains(parameters.getKeyword().toLowerCase()))
                .getStream(); // Lấy lại stream sau khi lọc từ whereIf

        // Áp dụng sắp xếp giảm dần theo trường Created
        query = query.sorted(Comparator.comparing(GroupProcess::getCreated).reversed());

        // Tiến hành phân trang và trả về kết quả
        return new QueryableExtensions<>(query)
                .paginate(parameters);
    }

    @Override
    public List<GroupProcess> findByCodes(List<String> codes) {
        var sql = "SELECT p FROM GroupProcess p WHERE p.code IN :codes";
        TypedQuery<GroupProcess> query = entityManager.createQuery(sql, GroupProcess.class);
        query.setParameter("codes", codes);
        return query.getResultList();
    }

    @Override
    public Optional<GroupProcess> findByCode(String code) {
        var sql = "SELECT p FROM GroupProcess p WHERE p.code = :code";
        TypedQuery<GroupProcess> query = entityManager.createQuery(sql, GroupProcess.class);
        query.setParameter("code", code);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public GroupProcess create(GroupProcess entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public List<GroupProcess> createMany(List<GroupProcess> entities) {
        for (GroupProcess entity : entities) {
            entityManager.persist(entity);
        }
        return entities;
    }

    @Override
    public GroupProcess update(GroupProcess entity) {
        return entityManager.merge(entity);
    }

    @Override
    public List<GroupProcess> updateMany(List<GroupProcess> entities) {
        for (GroupProcess entity : entities) {
            entityManager.merge(entity);
        }
        return entities;
    }

    @Override
    public void delete(Long id) {
        GroupProcess product = entityManager.find(GroupProcess.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    @Override
    public void deleteMany(List<Long> ids) {
        for (Long id : ids) {
            delete(id);
        }
    }
}
