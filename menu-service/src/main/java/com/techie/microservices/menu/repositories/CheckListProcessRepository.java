package com.techie.microservices.menu.repositories;

import com.techie.microservices.menu.entities.CheckListProcess;
import com.techie.microservices.menu.infrastructure.common.RepositoryBase;
import com.techie.microservices.menu.repositories.interfaces.ICheckListProcessRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CheckListProcessRepository extends RepositoryBase<CheckListProcess, Long> implements ICheckListProcessRepository {

    public CheckListProcessRepository(EntityManager entityManager) {
        super(CheckListProcess.class);
        this.entityManager = entityManager;
    }

    @Override
    public List<CheckListProcess> findByProcessId(Long processId) {
        TypedQuery<CheckListProcess> query = entityManager.createQuery(
                "SELECT p FROM CheckListProcess p WHERE p.processId = :processId", CheckListProcess.class);
        query.setParameter("processId", processId);
        return query.getResultList();
    }

    @Override
    public List<CheckListProcess> createMany(List<CheckListProcess> entities) {
        for (CheckListProcess entity : entities) {
            entityManager.persist(entity);
        }
        return entities;
    }


    @Override
    @Transactional
    public void deleteMany(List<CheckListProcess> entities) {
        for (CheckListProcess entity : entities) {
            entityManager.remove(entity);
        }
    }

}
