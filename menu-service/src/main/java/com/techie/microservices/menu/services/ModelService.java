package com.techie.microservices.menu.services;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ModelParameters;
import com.techie.microservices.menu.dto.requests.CreateModelDto;
import com.techie.microservices.menu.dto.requests.UpdateModelDto;
import com.techie.microservices.menu.dto.responses.ModelDto;
import com.techie.microservices.menu.entities.Model;
import com.techie.microservices.menu.exceptions.ResponseException;
import com.techie.microservices.menu.extensions.AutoMapperExtension;
import com.techie.microservices.menu.repositories.interfaces.IModelRepository;
import com.techie.microservices.menu.services.interfaces.IModelService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService implements IModelService {
    private final IModelRepository modelRepository;
    private final ModelMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(ModelService.class);

    public ModelService(IModelRepository modelRepository, ModelMapper mapper) {
        this.modelRepository = modelRepository;
        this.mapper = mapper;
    }

    @Override
    public PagingDto<ModelDto> getAllPaging(ModelParameters query) {
        try {
            var paging = modelRepository.getModels(query);
            return AutoMapperExtension.mapPagingList(paging, ModelDto.class);
        } catch (Exception e) {
            logger.error("Error Get all model{}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống, vui lòng thử lại sau!", e);
        }
    }

    @Override
    public Optional<ModelDto> getById(Long id) {
        return modelRepository.findById(id).map(model -> mapper.map(model, ModelDto.class));
    }

    @Override
    public ModelDto createModel(CreateModelDto model) {
        try {
            Optional<Model> modelByValue = modelRepository.findByValue(model.getValue());
            if (modelByValue.isPresent()) {
                throw new ResponseException("Model value already exists: " + model.getValue());
            }

            Optional<Model> modelByModelId = modelRepository.findByModelId(model.getModelId());
            if (modelByModelId.isPresent()) {
                throw new ResponseException("Model ID already exists: " + model.getModelId());
            }
            Model entity = mapper.map(model, Model.class);
            modelRepository.create(entity);
            return mapper.map(entity, ModelDto.class);
        } catch (Exception e) {
            logger.error("Error creating model: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean updateModel(Long id, UpdateModelDto modelDto) {
        try {
            Model model = modelRepository.findById(id).orElseThrow(() -> {
                return new ResponseException("Model not found");
            });
            mapper.map(modelDto, model);
            modelRepository.update(model);
            return true;
        } catch (Exception e) {
            logger.error("Error updating model {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            Model model = modelRepository.findById(id).orElseThrow(() -> {
                return new ResponseException("Model not found");
            });
            modelRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting model {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<ModelDto> findByModelIds(List<Integer> modelIds) {
        var entities = modelRepository.findByModelIds(modelIds);
        return AutoMapperExtension.mapList(entities, ModelDto.class);
    }
}
