package com.techie.microservices.menu.repositories.interfaces;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.CheckListParameters;
import com.techie.microservices.menu.entities.CheckList;
import com.techie.microservices.menu.infrastructure.common.IRepositoryBase;

import java.util.List;
import java.util.Optional;

public interface ICheckListRepository extends IRepositoryBase<CheckList, Long> {
    PagingDto<CheckList> getCheckList(CheckListParameters parameters);

    Optional<CheckList> findByCheckListId(Integer checkListId);

    List<CheckList> findByCheckListIds(List<Integer> checkListIds);

    List<CheckList> findByValues(List<String> values);

    Optional<CheckList> findByValue(String value);

    CheckList create(CheckList entity);

    List<CheckList> createMany(List<CheckList> entities);

    CheckList update(CheckList entity);

    List<CheckList> updateMany(List<CheckList> entities);

    void delete(Long id);

    void deleteMany(List<Long> ids);
}
