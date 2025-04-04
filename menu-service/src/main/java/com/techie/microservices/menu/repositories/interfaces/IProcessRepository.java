package com.techie.microservices.menu.repositories.interfaces;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ProcessParameters;
import com.techie.microservices.menu.entities.Process;
import com.techie.microservices.menu.infrastructure.common.IRepositoryBase;

import java.util.List;
import java.util.Optional;

public interface IProcessRepository extends IRepositoryBase<Process, Long> {
    PagingDto<Process> getProcess(ProcessParameters parameters);

    Optional<Process> findByProcessId(int processId);

    List<Process> findByProcessIds(List<Integer> processIds);

    List<Process> findByValues(List<String> values);

    Optional<Process> findByValue(String value);

    Process create(Process entity);

    List<Process> createMany(List<Process> entities);

    Process update(Process entity);

    List<Process> updateMany(List<Process> entities);

    void delete(Long id);

    void deleteMany(List<Long> ids);
}
