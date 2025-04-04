package com.techie.microservices.menu.repositories;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ProcessParameters;
import com.techie.microservices.menu.entities.Process;
import com.techie.microservices.menu.extensions.QueryableExtensions;
import com.techie.microservices.menu.infrastructure.common.RepositoryBase;
import com.techie.microservices.menu.repositories.interfaces.IProcessRepository;
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
public class ProcessRepository extends RepositoryBase<Process, Long> implements IProcessRepository {

    public ProcessRepository(EntityManager entityManager) {
        super(Process.class);
        this.entityManager = entityManager;
    }

    @Override
    public PagingDto<Process> getProcess(ProcessParameters parameters) {
        Stream<Process> query = findAll().stream();

        // Áp dụng điều kiện tìm kiếm theo keyword nếu có sử dụng whereIf
        query = new QueryableExtensions<>(query)
                .whereIf(parameters.getKeyword() != null,
                        p -> p.getName().toLowerCase().contains(parameters.getKeyword().toLowerCase()) ||
                                p.getValue().toLowerCase().contains(parameters.getKeyword().toLowerCase()))
                .getStream(); // Lấy lại stream sau khi lọc từ whereIf

        // Áp dụng sắp xếp giảm dần theo trường Created
        query = query.sorted(Comparator.comparing(Process::getCreated).reversed());

        // Tiến hành phân trang và trả về kết quả
        return new QueryableExtensions<>(query)
                .paginate(parameters);
    }

    @Override
    public Optional<Process> findByProcessId(int processId) {
        TypedQuery<Process> query = entityManager.createQuery(
                "SELECT p FROM Process p WHERE p.processId = :processId", Process.class);
        query.setParameter("processId", processId);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<Process> findByProcessIds(List<Integer> processIds) {
        TypedQuery<Process> query = entityManager.createQuery(
                "SELECT p FROM Process p WHERE p.processId IN :processIds", Process.class);
        query.setParameter("processIds", processIds);
        return query.getResultList();
    }

    @Override
    public List<Process> findByValues(List<String> values) {
        TypedQuery<Process> query = entityManager.createQuery(
                "SELECT p FROM Process p WHERE p.value IN :values", Process.class);
        query.setParameter("values", values);
        return query.getResultList();
    }

    @Override
    public Optional<Process> findByValue(String value) {
        var sql = "SELECT p FROM Process p WHERE p.value = :value";
        TypedQuery<Process> query = entityManager.createQuery(sql, Process.class);
        query.setParameter("value", value);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Process create(Process process) {
        entityManager.persist(process);
        return process;
    }

    @Override
    public List<Process> createMany(List<Process> manyProcess) {
        for (Process process : manyProcess) {
            entityManager.persist(process);
        }
        return manyProcess;
    }

    @Override
    public Process update(Process process) {
        return entityManager.merge(process);
    }

    @Override
    public List<Process> updateMany(List<Process> manyProcess) {
        for (Process process : manyProcess) {
            entityManager.merge(process);
        }
        return manyProcess;
    }

    @Override
    public void delete(Long id) {
        Process process = entityManager.find(Process.class, id);
        if (process != null) {
            entityManager.remove(process);
        }
    }

    @Override
    public void deleteMany(List<Long> ids) {
        for (Long id : ids) {
            delete(id);
        }
    }

}
