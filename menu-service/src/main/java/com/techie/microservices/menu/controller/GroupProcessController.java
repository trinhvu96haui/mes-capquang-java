package com.techie.microservices.menu.controller;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.GroupProcessParameters;
import com.techie.microservices.menu.dto.requests.CreateGroupProcessDto;
import com.techie.microservices.menu.dto.requests.UpdateGroupProcessDto;
import com.techie.microservices.menu.dto.responses.GroupProcessDto;
import com.techie.microservices.menu.services.interfaces.IGroupProcessService;
import jakarta.validation.constraints.NotNull;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/group-process")
@Validated
public class GroupProcessController {

    private final IGroupProcessService groupProcessService;

    public GroupProcessController(IGroupProcessService groupProcessService) {
        this.groupProcessService = groupProcessService;
    }

    // Lấy danh sách quy trình có phân trang
    @GetMapping
    public ResponseEntity<PagingDto<GroupProcessDto>> getGroupProcess(@ParameterObject GroupProcessParameters parameters) {
        var result = groupProcessService.getAllPaging(parameters);
        return ResponseEntity.ok(result);
    }

    // Lấy quy trình theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<GroupProcessDto>> findById(@PathVariable @NotNull Long id) {
        var result = groupProcessService.getById(id);
        return ResponseEntity.ok(result);
    }

    // Tạo mới quy trình
    @PostMapping
    public ResponseEntity<GroupProcessDto> createGroupProcess(@RequestBody @Validated CreateGroupProcessDto groupProcessDto) {
        var result = groupProcessService.createGroupProcess(groupProcessDto);
        return ResponseEntity.ok(result);
    }

    // Cập nhật quy trình
    @PutMapping("/{id}/update")
    public ResponseEntity<Boolean> updateGroupProcess(@PathVariable @NotNull Long id,
                                                      @RequestBody @Validated UpdateGroupProcessDto groupProcessDto) {
        var result = groupProcessService.updateGroupProcess(id, groupProcessDto);
        return ResponseEntity.ok(result);
    }

    // Xóa quy trình
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteGroupProcess(@PathVariable @NotNull Long id) {
        groupProcessService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

