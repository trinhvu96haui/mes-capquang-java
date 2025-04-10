package com.techie.microservices.menu.services.interfaces;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.CheckListParameters;
import com.techie.microservices.menu.dto.requests.CreateCheckListDto;
import com.techie.microservices.menu.dto.requests.UpdateCheckListDto;
import com.techie.microservices.menu.dto.responses.CheckListDto;

import java.util.List;
import java.util.Optional;

public interface ICheckListService {
    PagingDto<CheckListDto> getAllPaging(CheckListParameters query);

    Optional<CheckListDto> getById(Long id);

    boolean updateCheckList(Long id, UpdateCheckListDto checkList);

    CheckListDto createCheckList(CreateCheckListDto checkList);

    boolean deleteById(Long id);

    List<CheckListDto> findByCheckListIds(List<Integer> checkListIds);
}
