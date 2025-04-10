package com.techie.microservices.menu.services;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.CheckListParameters;
import com.techie.microservices.menu.dto.requests.CreateCheckListDto;
import com.techie.microservices.menu.dto.requests.UpdateCheckListDto;
import com.techie.microservices.menu.dto.responses.CheckListDto;
import com.techie.microservices.menu.entities.CheckList;
import com.techie.microservices.menu.exceptions.ResponseException;
import com.techie.microservices.menu.extensions.AutoMapperExtension;
import com.techie.microservices.menu.repositories.interfaces.ICheckListRepository;
import com.techie.microservices.menu.services.interfaces.ICheckListService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CheckListService implements ICheckListService {
    private final ICheckListRepository repository;
    private final ModelMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(CheckListService.class);

    public CheckListService(ICheckListRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PagingDto<CheckListDto> getAllPaging(CheckListParameters query) {
        try {
            var paging = repository.getCheckList(query);
            return AutoMapperExtension.mapPagingList(paging, CheckListDto.class);
        } catch (Exception e) {
            logger.error("Error Get All CheckList: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống, vui lòng thử lại sau!", e);
        }
    }

    @Override
    public Optional<CheckListDto> getById(Long id) {
        return repository.findById(id).map(checkList -> mapper.map(checkList, CheckListDto.class));
    }

    @Override
    public CheckListDto createCheckList(CreateCheckListDto model) {
        try {
            Optional<CheckList> checkListByValue = repository.findByValue(model.getValue());
            if (checkListByValue.isPresent()) {
                throw new ResponseException("CheckList value already exists: " + model.getValue());
            }

            Optional<CheckList> checkListByCheckListId = repository.findByCheckListId(model.getCheckListId());
            if (checkListByCheckListId.isPresent()) {
                throw new ResponseException("CheckList checkListId already exists: " + model.getCheckListId());
            }
            CheckList entity = mapper.map(model, CheckList.class);
            repository.create(entity);
            return mapper.map(entity, CheckListDto.class);
        } catch (Exception e) {
            logger.error("Error creating checkList: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean updateCheckList(Long id, UpdateCheckListDto checkListDto) {
        try {
            CheckList checkList = repository.findById(id).orElseThrow(() -> {
                return new ResponseException("CheckList not found");
            });
            mapper.map(checkListDto, checkList);
            repository.update(checkList);
            return true;
        } catch (Exception e) {
            logger.error("Error updating checkList {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            CheckList checkList = repository.findById(id).orElseThrow(() -> {
                return new ResponseException("CheckList not found");
            });
            repository.delete(id);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting checkList {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<CheckListDto> findByCheckListIds(List<Integer> checkListIds) {
        var entities = repository.findByCheckListIds(checkListIds);
        return AutoMapperExtension.mapList(entities, CheckListDto.class);
    }
}
