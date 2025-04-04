package com.techie.microservices.menu.repositories.interfaces;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ModelParameters;
import com.techie.microservices.menu.entities.Model;
import com.techie.microservices.menu.infrastructure.common.IRepositoryBase;

import java.util.List;
import java.util.Optional;

public interface IModelRepository extends IRepositoryBase<Model, Long> {
    PagingDto<Model> getModels(ModelParameters parameters);

    Optional<Model> findByModelId(int modelId);

    List<Model> findByModelIds(List<Integer> modelIds);

    List<Model> findByValues(List<String> values);

    Optional<Model> findByValue(String value);

    Model create(Model model);

    List<Model> createMany(List<Model> models);

    Model update(Model model);

    List<Model> updateMany(List<Model> models);

    void delete(Long id);

    void deleteMany(List<Long> ids);
}
