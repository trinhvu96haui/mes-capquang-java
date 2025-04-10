package com.techie.microservices.menu.services;

import com.techie.microservices.menu.Enums.InputMethod;
import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ProcessParameters;
import com.techie.microservices.menu.dto.requests.CreateProcessDto;
import com.techie.microservices.menu.dto.requests.UpdateProcessDto;
import com.techie.microservices.menu.dto.responses.ProcessDto;
import com.techie.microservices.menu.entities.CheckListProcess;
import com.techie.microservices.menu.entities.Process;
import com.techie.microservices.menu.exceptions.ResponseException;
import com.techie.microservices.menu.extensions.AutoMapperExtension;
import com.techie.microservices.menu.repositories.interfaces.ICheckListProcessRepository;
import com.techie.microservices.menu.repositories.interfaces.ICheckListRepository;
import com.techie.microservices.menu.repositories.interfaces.IProcessRepository;
import com.techie.microservices.menu.services.interfaces.IProcessService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcessService implements IProcessService {
    private final IProcessRepository repository;
    private final ICheckListRepository checkListRepository;
    private final ICheckListProcessRepository checkListProcessRepository;
    private final ModelMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(ProcessService.class);

    public ProcessService(IProcessRepository repository, ICheckListRepository checkListRepository, ICheckListProcessRepository checkListProcessRepository, ModelMapper mapper) {
        this.repository = repository;
        this.checkListRepository = checkListRepository;
        this.checkListProcessRepository = checkListProcessRepository;
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
    @Transactional
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

            if (model.getInputMethod() == InputMethod.CHECKLIST && model.getCheckListIds() == null) {
                throw new ResponseException("InputMethod is CHECKLIST, CheckList do not empty");
            }

            Process entity = mapper.map(model, Process.class);
            repository.create(entity);

            if (model.getCheckListIds() != null) {
                validCheckList(model.getCheckListIds());
                var getCheckListProcess = checkListProcessRepository.findByProcessId(Long.valueOf(model.getProcessId()));
                checkListProcessRepository.deleteMany(getCheckListProcess);
                List<CheckListProcess> newCheckListProcesses = model.getCheckListIds().stream()
                        .map(item -> {
                            CheckListProcess obj = new CheckListProcess();
                            obj.setCheckListId(item.longValue());
                            obj.setProcessId(Long.valueOf(entity.getProcessId()));
                            return obj;
                        })
                        .collect(Collectors.toList());
                checkListProcessRepository.createMany(newCheckListProcesses);
            }

            return mapper.map(entity, ProcessDto.class);
        } catch (Exception e) {
            logger.error("Error creating process: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public boolean updateProcess(Long id, UpdateProcessDto model) {
        try {
            Process process = repository.findById(id).orElseThrow(() -> {
                return new ResponseException("Process not found");
            });
            mapper.map(model, process);
            repository.update(process);
            if (model.getCheckListIds() != null) {
                validCheckList(model.getCheckListIds());
                var getCheckListProcess = checkListProcessRepository.findByProcessId(Long.valueOf(process.getProcessId()));
                checkListProcessRepository.deleteMany(getCheckListProcess);
                List<CheckListProcess> newCheckListProcesses = model.getCheckListIds().stream()
                        .map(item -> {
                            CheckListProcess obj = new CheckListProcess();
                            obj.setCheckListId(item.longValue());
                            obj.setProcessId(Long.valueOf(process.getProcessId()));
                            return obj;
                        })
                        .collect(Collectors.toList());
                checkListProcessRepository.createMany(newCheckListProcesses);
            }
            return true;
        } catch (Exception e) {
            logger.error("Error updating process {}: {}", id, e.getMessage());
            throw e;
        }
    }

    public void validCheckList(List<Integer> checkListIds) {
        var checkLists = checkListRepository.findByCheckListIds(checkListIds);
        for (Integer item : checkListIds) {
            var checkList = checkLists.stream().filter(it -> it.getCheckListId().equals(item)).findFirst();
            if (checkList.isEmpty()) {
                throw new ResponseException("CheckList not found: " + item);
            }
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
