package com.techie.microservices.menu.controller;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ProcessParameters;
import com.techie.microservices.menu.dto.requests.CreateProcessDto;
import com.techie.microservices.menu.dto.requests.UpdateProcessDto;
import com.techie.microservices.menu.dto.responses.ProcessDto;
import com.techie.microservices.menu.services.interfaces.IProcessService;
import jakarta.validation.constraints.NotNull;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/process")
@Validated
public class ProcessController {

    private final IProcessService processService;

    public ProcessController(IProcessService processService) {
        this.processService = processService;
    }

    // Lấy danh sách process có phân trang
    @GetMapping
    public ResponseEntity<PagingDto<ProcessDto>> getProcess(@ParameterObject ProcessParameters parameters) {
        var result = processService.getAllPaging(parameters);
        return ResponseEntity.ok(result);
    }

    // Lấy process theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProcessDto>> getProcess(@PathVariable @NotNull Long id) {
        var result = processService.getById(id);
        return ResponseEntity.ok(result);
    }

    // Tạo mới process
    @PostMapping
    public ResponseEntity<ProcessDto> createProcess(@RequestBody @Validated CreateProcessDto processDto) {
        var result = processService.createProcess(processDto);
        return ResponseEntity.ok(result);
    }

    // Cập nhật process
    @PutMapping("/{id}/update")
    public ResponseEntity<Boolean> updateProcess(@PathVariable @NotNull Long id,
                                               @RequestBody @Validated UpdateProcessDto processDto) {
        var result = processService.updateProcess(id, processDto);
        return ResponseEntity.ok(result);
    }

    // Xóa process
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteProcess(@PathVariable @NotNull Long id) {
        processService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

