package com.techie.microservices.menu.services;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ProcessParameters;
import com.techie.microservices.menu.dto.requests.CreateProcessDto;
import com.techie.microservices.menu.dto.requests.UpdateProcessDto;
import com.techie.microservices.menu.dto.responses.ProcessDto;
import com.techie.microservices.menu.entities.Process;
import com.techie.microservices.menu.exceptions.ResponseException;
import com.techie.microservices.menu.extensions.AutoMapperExtension;
import com.techie.microservices.menu.repositories.interfaces.IProcessRepository;
import com.techie.microservices.menu.services.interfaces.IProcessService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessService implements IProcessService {
    private final IProcessRepository repository;
    private final ModelMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(ProcessService.class);

    public ProcessService(IProcessRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PagingDto<ProcessDto> getAllPaging(ProcessParameters query) {
        try {
            var paging = repository.getProcess(query);
            return AutoMapperExtension.mapPagingList(paging, ProcessDto.class);
        } catch (Exception e) {
            logger.error("Error Get All Process: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống, vui lòng thử lại sau!", e);
        }
    }

    @Override
    public Optional<ProcessDto> getById(Long id) {
        return repository.findById(id).map(process -> mapper.map(process, ProcessDto.class));
    }

    @Override
    public ProcessDto createProcess(CreateProcessDto model) {
        try {
            Optional<Process> processByValue = repository.findByValue(model.getValue());
            if (processByValue.isPresent()) {
                throw new ResponseException("Process value already exists: " + model.getValue());
            }

            Optional<Process> processByProcessId = repository.findByProcessId(model.getProcessId());
            if (processByProcessId.isPresent()) {
                throw new ResponseException("Process processId already exists: " + model.getProcessId());
            }
            Process entity = mapper.map(model, Process.class);
            repository.create(entity);
            return mapper.map(entity, ProcessDto.class);
        } catch (Exception e) {
            logger.error("Error creating process: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean updateProcess(Long id, UpdateProcessDto processDto) {
        try {
            Process process = repository.findById(id).orElseThrow(() -> {
                return new ResponseException("Process not found");
            });
            mapper.map(processDto, process);
            repository.update(process);
            return true;
        } catch (Exception e) {
            logger.error("Error updating process {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            Process process = repository.findById(id).orElseThrow(() -> {
                return new ResponseException("Process not found");
            });
            repository.delete(id);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting process {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<ProcessDto> findByProcessIds(List<Integer> processIds) {
        var entities = repository.findByProcessIds(processIds);
        return AutoMapperExtension.mapList(entities, ProcessDto.class);
    }
}
