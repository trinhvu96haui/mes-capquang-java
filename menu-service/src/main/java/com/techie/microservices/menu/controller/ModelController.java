package com.techie.microservices.menu.controller;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ModelParameters;
import com.techie.microservices.menu.dto.requests.CreateModelDto;
import com.techie.microservices.menu.dto.requests.UpdateModelDto;
import com.techie.microservices.menu.dto.responses.ModelDto;
import com.techie.microservices.menu.services.interfaces.IModelService;
import jakarta.validation.constraints.NotNull;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/models")
@Validated
public class ModelController {

    private final IModelService modelService;

    public ModelController(IModelService modelService) {
        this.modelService = modelService;
    }

    // Lấy danh sách model có phân trang
    @GetMapping
    public ResponseEntity<PagingDto<ModelDto>> getModels(@ParameterObject ModelParameters parameters) {
        var result = modelService.getAllPaging(parameters);
        return ResponseEntity.ok(result);
    }

    // Lấy model theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ModelDto>> getModel(@PathVariable @NotNull Long id) {
        var result = modelService.getById(id);
        return ResponseEntity.ok(result);
    }

    // Tạo mới model
    @PostMapping
    public ResponseEntity<ModelDto> createModel(@RequestBody @Validated CreateModelDto modelDto) {
        var result = modelService.createModel(modelDto);
        return ResponseEntity.ok(result);
    }

    // Cập nhật model
    @PutMapping("/{id}/update")
    public ResponseEntity<Boolean> updateModel(@PathVariable @NotNull Long id,
                                               @RequestBody @Validated UpdateModelDto modelDto) {
        var result = modelService.updateModel(id, modelDto);
        return ResponseEntity.ok(result);
    }

    // Xóa model
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteModel(@PathVariable @NotNull Long id) {
        modelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

