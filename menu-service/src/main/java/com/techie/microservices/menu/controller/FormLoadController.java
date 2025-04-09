package com.techie.microservices.menu.controller;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.FormLoadParameters;
import com.techie.microservices.menu.dto.requests.CreateFormLoadDto;
import com.techie.microservices.menu.dto.requests.UpdateFormLoadDto;
import com.techie.microservices.menu.dto.responses.FormLoadDto;
import com.techie.microservices.menu.services.interfaces.IFormLoadService;
import jakarta.validation.constraints.NotNull;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/formLoad")
@Validated
public class FormLoadController {

    private final IFormLoadService formLoadService;

    public FormLoadController(IFormLoadService formLoadService) {
        this.formLoadService = formLoadService;
    }

    // Lấy danh sách formLoad có phân trang
    @GetMapping
    public ResponseEntity<PagingDto<FormLoadDto>> getFormLoadPaging(@ParameterObject FormLoadParameters parameters) {
        var result = formLoadService.getAllPaging(parameters);
        return ResponseEntity.ok(result);
    }

    // Lấy formLoad theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<FormLoadDto>> getFormLoad(@PathVariable @NotNull Long id) {
        var result = formLoadService.getById(id);
        return ResponseEntity.ok(result);
    }

    // Tạo mới formLoad
    @PostMapping
    public ResponseEntity<FormLoadDto> createFormLoad(@RequestBody @Validated CreateFormLoadDto formLoadDto) {
        var result = formLoadService.createFormLoad(formLoadDto);
        return ResponseEntity.ok(result);
    }

    // Cập nhật formLoad
    @PutMapping("/{id}/update")
    public ResponseEntity<Boolean> updateFormLoad(@PathVariable @NotNull Long id,
                                               @RequestBody @Validated UpdateFormLoadDto formLoadDto) {
        var result = formLoadService.updateFormLoad(id, formLoadDto);
        return ResponseEntity.ok(result);
    }

    // Xóa formLoad
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteFormLoad(@PathVariable @NotNull Long id) {
        formLoadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

