package com.techie.microservices.menu.services.interfaces;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.GroupProcessParameters;
import com.techie.microservices.menu.dto.requests.CreateGroupProcessDto;
import com.techie.microservices.menu.dto.requests.UpdateGroupProcessDto;
import com.techie.microservices.menu.dto.responses.GroupProcessDto;

import java.util.Optional;

public interface IGroupProcessService {
    PagingDto<GroupProcessDto> getAllPaging(GroupProcessParameters query);

    Optional<GroupProcessDto> getById(Long id);

    boolean updateGroupProcess(Long id, UpdateGroupProcessDto dto);

    GroupProcessDto createGroupProcess(CreateGroupProcessDto dto);

    boolean deleteById(Long id);
}
