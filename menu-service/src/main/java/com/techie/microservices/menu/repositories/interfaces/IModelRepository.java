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
    List<Model> findByCodes(List<String> codes);

    Model createModel(Model model);
    List<Model> createManyModels(List<Model> models);
    Model updateModel(Model model);
    List<Model> updateManyModels(List<Model> models);

    void deleteModel(Long id);
    void deleteManyModels(List<Long> ids);
}
