package com.techie.microservices.menu.services.interfaces;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ModelParameters;
import com.techie.microservices.menu.dto.requests.CreateModelDto;
import com.techie.microservices.menu.dto.requests.UpdateModelDto;
import com.techie.microservices.menu.dto.responses.ModelDto;

import java.util.List;
import java.util.Optional;

public interface IModelService {
    PagingDto<ModelDto> getAllPaging(ModelParameters query);

    Optional<ModelDto> getById(Long id);

    boolean updateModel(Long id, UpdateModelDto product);

    ModelDto createModel(CreateModelDto model);

    boolean deleteById(Long id);

    List<ModelDto> findByModelIds(List<Integer> productIds);
}
