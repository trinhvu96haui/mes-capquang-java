package com.techie.microservices.menu.services.interfaces;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.FormLoadParameters;
import com.techie.microservices.menu.dto.requests.CreateFormLoadDto;
import com.techie.microservices.menu.dto.requests.UpdateFormLoadDto;
import com.techie.microservices.menu.dto.responses.FormLoadDto;

import java.util.List;
import java.util.Optional;

public interface IFormLoadService {
    PagingDto<FormLoadDto> getAllPaging(FormLoadParameters query);

    Optional<FormLoadDto> getById(Long id);

    boolean updateFormLoad(Long id, UpdateFormLoadDto process);

    FormLoadDto createFormLoad(CreateFormLoadDto process);

    boolean deleteById(Long id);

    List<FormLoadDto> findByFormLoadIds(List<Integer> processIds);
}
