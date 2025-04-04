package com.techie.microservices.menu.services;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.GroupProcessParameters;
import com.techie.microservices.menu.dto.requests.CreateGroupProcessDto;
import com.techie.microservices.menu.dto.requests.UpdateGroupProcessDto;
import com.techie.microservices.menu.dto.responses.GroupProcessDto;
import com.techie.microservices.menu.entities.GroupProcess;
import com.techie.microservices.menu.exceptions.ResponseException;
import com.techie.microservices.menu.extensions.AutoMapperExtension;
import com.techie.microservices.menu.repositories.interfaces.IGroupProcessRepository;
import com.techie.microservices.menu.services.interfaces.IGroupProcessService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class GroupProcessService implements IGroupProcessService {
    private final IGroupProcessRepository repository;
    private final ModelMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(GroupProcessService.class);

    public GroupProcessService(IGroupProcessRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PagingDto<GroupProcessDto> getAllPaging(GroupProcessParameters query) {
        try {
            var paging = repository.getGroupProcess(query);
            return AutoMapperExtension.mapPagingList(paging, GroupProcessDto.class);
        } catch (Exception e) {
            logger.error("Error Get all group process: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống, vui lòng thử lại sau!", e);
        }
    }

    @Override
    public Optional<GroupProcessDto> getById(Long id) {
        return repository.findById(id).map(groupProcess -> mapper.map(groupProcess, GroupProcessDto.class));
    }

    @Override
    public GroupProcessDto createGroupProcess(CreateGroupProcessDto model) {
        try {
            Optional<GroupProcess> groupProcessByCode = repository.findByCode(model.getCode());
            if (groupProcessByCode.isPresent()) {
                throw new ResponseException("GroupProcess code already exists: " + model.getCode());
            }
            GroupProcess entity = mapper.map(model, GroupProcess.class);
            repository.create(entity);
            return mapper.map(entity, GroupProcessDto.class);
        } catch (Exception e) {
            logger.error("Error creating groupProcess: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean updateGroupProcess(Long id, UpdateGroupProcessDto groupProcessDto) {
        try {
            GroupProcess groupProcess = repository.findById(id).orElseThrow(() -> {
                return new ResponseException("GroupProcess not found");
            });
            mapper.map(groupProcessDto, groupProcess);
            repository.update(groupProcess);
            return true;
        } catch (Exception e) {
            logger.error("Error updating groupProcess {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            GroupProcess groupProcess = repository.findById(id).orElseThrow(() -> {
                return new ResponseException("GroupProcess not found");
            });
            repository.delete(id);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting groupProcess {}: {}", id, e.getMessage());
            throw e;
        }
    }
}
