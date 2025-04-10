package com.techie.microservices.menu.repositories;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.CheckListParameters;
import com.techie.microservices.menu.entities.CheckList;
import com.techie.microservices.menu.extensions.QueryableExtensions;
import com.techie.microservices.menu.infrastructure.common.RepositoryBase;
import com.techie.microservices.menu.repositories.interfaces.ICheckListRepository;
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
public class CheckListRepository extends RepositoryBase<CheckList, Long> implements ICheckListRepository {

    public CheckListRepository(EntityManager entityManager) {
        super(CheckList.class);
        this.entityManager = entityManager;
    }

    @Override
    public PagingDto<CheckList> getCheckList(CheckListParameters parameters) {
        Stream<CheckList> query = findAll().stream();

        // Áp dụng điều kiện tìm kiếm theo keyword nếu có sử dụng whereIf
        query = new QueryableExtensions<>(query)
                .whereIf(parameters.getKeyword() != null,
                        p -> p.getName().toLowerCase().contains(parameters.getKeyword().toLowerCase()) ||
                                p.getValue().toLowerCase().contains(parameters.getKeyword().toLowerCase()))
                .whereIf(parameters.getType() != null,
                        p -> p.getType() == parameters.getType())
                .getStream(); // Lấy lại stream sau khi lọc từ whereIf

        // Áp dụng sắp xếp giảm dần theo trường Created
        query = query.sorted(Comparator.comparing(CheckList::getCreated).reversed());

        // Tiến hành phân trang và trả về kết quả
        return new QueryableExtensions<>(query)
                .paginate(parameters);
    }

    @Override
    public Optional<CheckList> findByCheckListId(Integer checkListId) {
        TypedQuery<CheckList> query = entityManager.createQuery(
                "SELECT p FROM CheckList p WHERE p.checkListId = :checkListId", CheckList.class);
        query.setParameter("checkListId", checkListId);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<CheckList> findByCheckListIds(List<Integer> checkListIds) {
        TypedQuery<CheckList> query = entityManager.createQuery(
                "SELECT p FROM CheckList p WHERE p.checkListId IN :checkListIds", CheckList.class);
        query.setParameter("checkListIds", checkListIds);
        return query.getResultList();
    }

    @Override
    public List<CheckList> findByValues(List<String> values) {
        TypedQuery<CheckList> query = entityManager.createQuery(
                "SELECT p FROM CheckList p WHERE p.value IN :values", CheckList.class);
        query.setParameter("values", values);
        return query.getResultList();
    }

    @Override
    public Optional<CheckList> findByValue(String value) {
        var sql = "SELECT p FROM CheckList p WHERE p.value = :value";
        TypedQuery<CheckList> query = entityManager.createQuery(sql, CheckList.class);
        query.setParameter("value", value);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public CheckList create(CheckList checkList) {
        entityManager.persist(checkList);
        return checkList;
    }

    @Override
    public List<CheckList> createMany(List<CheckList> manyCheckList) {
        for (CheckList checkList : manyCheckList) {
            entityManager.persist(checkList);
        }
        return manyCheckList;
    }

    @Override
    public CheckList update(CheckList checkList) {
        return entityManager.merge(checkList);
    }

    @Override
    public List<CheckList> updateMany(List<CheckList> manyCheckList) {
        for (CheckList checkList : manyCheckList) {
            entityManager.merge(checkList);
        }
        return manyCheckList;
    }

    @Override
    public void delete(Long id) {
        CheckList checkList = entityManager.find(CheckList.class, id);
        if (checkList != null) {
            entityManager.remove(checkList);
        }
    }

    @Override
    public void deleteMany(List<Long> ids) {
        for (Long id : ids) {
            delete(id);
        }
    }

}
