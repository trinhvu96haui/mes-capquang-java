package com.techie.microservices.menu.services;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.FormLoadParameters;
import com.techie.microservices.menu.dto.requests.CreateFormLoadDto;
import com.techie.microservices.menu.dto.requests.UpdateFormLoadDto;
import com.techie.microservices.menu.dto.responses.FormLoadDto;
import com.techie.microservices.menu.entities.FormLoad;
import com.techie.microservices.menu.exceptions.ResponseException;
import com.techie.microservices.menu.extensions.AutoMapperExtension;
import com.techie.microservices.menu.repositories.interfaces.IFormLoadRepository;
import com.techie.microservices.menu.services.interfaces.IFormLoadService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FormLoadService implements IFormLoadService {
    private final IFormLoadRepository repository;
    private final ModelMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(FormLoadService.class);

    public FormLoadService(IFormLoadRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PagingDto<FormLoadDto> getAllPaging(FormLoadParameters query) {
        try {
            var paging = repository.getFormLoad(query);
            return AutoMapperExtension.mapPagingList(paging, FormLoadDto.class);
        } catch (Exception e) {
            logger.error("Error Get All FormLoad: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống, vui lòng thử lại sau!", e);
        }
    }

    @Override
    public Optional<FormLoadDto> getById(Long id) {
        return repository.findById(id).map(formLoad -> mapper.map(formLoad, FormLoadDto.class));
    }

    @Override
    public FormLoadDto createFormLoad(CreateFormLoadDto model) {
        try {
            Optional<FormLoad> formLoadByName = repository.findByName(model.getName());
            if (formLoadByName.isPresent()) {
                throw new ResponseException("FormLoad name already exists: " + model.getName());
            }

            Optional<FormLoad> formLoadByFormLoadId = repository.findByFormLoadId(model.getFormLoadId());
            if (formLoadByFormLoadId.isPresent()) {
                throw new ResponseException("FormLoad formLoadId already exists: " + model.getFormLoadId());
            }
            FormLoad entity = mapper.map(model, FormLoad.class);
            repository.create(entity);
            return mapper.map(entity, FormLoadDto.class);
        } catch (Exception e) {
            logger.error("Error creating formLoad: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean updateFormLoad(Long id, UpdateFormLoadDto formLoadDto) {
        try {
            FormLoad formLoad = repository.findById(id).orElseThrow(() -> {
                return new ResponseException("FormLoad not found");
            });
            mapper.map(formLoadDto, formLoad);
            formLoad.setUpdated(Instant.now());
            repository.update(formLoad);
            return true;
        } catch (Exception e) {
            logger.error("Error updating formLoad {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            FormLoad formLoad = repository.findById(id).orElseThrow(() -> {
                return new ResponseException("FormLoad not found");
            });
            repository.delete(id);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting formLoad {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<FormLoadDto> findByFormLoadIds(List<Integer> formLoadIds) {
        var entities = repository.findByFormLoadIds(formLoadIds);
        return AutoMapperExtension.mapList(entities, FormLoadDto.class);
    }
}
