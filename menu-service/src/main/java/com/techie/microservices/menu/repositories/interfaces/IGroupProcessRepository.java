package com.techie.microservices.menu.repositories.interfaces;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.GroupProcessParameters;
import com.techie.microservices.menu.entities.GroupProcess;
import com.techie.microservices.menu.infrastructure.common.IRepositoryBase;

import java.util.List;
import java.util.Optional;

public interface IGroupProcessRepository extends IRepositoryBase<GroupProcess, Long> {
    PagingDto<GroupProcess> getGroupProcess(GroupProcessParameters parameters);

    List<GroupProcess> findByCodes(List<String> codes);

    Optional<GroupProcess> findByCode(String code);

    GroupProcess create(GroupProcess entity);

    List<GroupProcess> createMany(List<GroupProcess> entities);

    GroupProcess update(GroupProcess entity);

    List<GroupProcess> updateMany(List<GroupProcess> entities);

    void delete(Long id);

    void deleteMany(List<Long> ids);
}
