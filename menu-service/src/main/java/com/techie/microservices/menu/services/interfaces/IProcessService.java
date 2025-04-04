package com.techie.microservices.menu.services.interfaces;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ProcessParameters;
import com.techie.microservices.menu.dto.requests.CreateProcessDto;
import com.techie.microservices.menu.dto.requests.UpdateProcessDto;
import com.techie.microservices.menu.dto.responses.ProcessDto;

import java.util.List;
import java.util.Optional;

public interface IProcessService {
    PagingDto<ProcessDto> getAllPaging(ProcessParameters query);

    Optional<ProcessDto> getById(Long id);

    boolean updateProcess(Long id, UpdateProcessDto process);

    ProcessDto createProcess(CreateProcessDto process);

    boolean deleteById(Long id);

    List<ProcessDto> findByProcessIds(List<Integer> processIds);
}
