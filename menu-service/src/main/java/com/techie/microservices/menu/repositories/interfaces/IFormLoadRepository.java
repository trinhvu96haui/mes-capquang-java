package com.techie.microservices.menu.repositories.interfaces;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.FormLoadParameters;
import com.techie.microservices.menu.entities.FormLoad;
import com.techie.microservices.menu.infrastructure.common.IRepositoryBase;

import java.util.List;
import java.util.Optional;

public interface IFormLoadRepository extends IRepositoryBase<FormLoad, Long> {
    PagingDto<FormLoad> getFormLoad(FormLoadParameters parameters);

    Optional<FormLoad> findByFormLoadId(int formLoadId);

    List<FormLoad> findByFormLoadIds(List<Integer> formLoadIds);

    List<FormLoad> findByNames(List<String> names);

    Optional<FormLoad> findByName(String name);

    FormLoad create(FormLoad entity);

    List<FormLoad> createMany(List<FormLoad> entities);

    FormLoad update(FormLoad entity);

    List<FormLoad> updateMany(List<FormLoad> entities);

    void delete(Long id);

    void deleteMany(List<Long> ids);
}
