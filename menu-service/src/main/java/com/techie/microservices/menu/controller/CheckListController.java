package com.techie.microservices.menu.controller;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.CheckListParameters;
import com.techie.microservices.menu.dto.requests.CreateCheckListDto;
import com.techie.microservices.menu.dto.requests.UpdateCheckListDto;
import com.techie.microservices.menu.dto.responses.CheckListDto;
import com.techie.microservices.menu.services.interfaces.ICheckListService;
import jakarta.validation.constraints.NotNull;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/check-list")
@Validated
public class CheckListController {

    private final ICheckListService checkListService;

    public CheckListController(ICheckListService checkListService) {
        this.checkListService = checkListService;
    }

    // Lấy danh sách checkList có phân trang
    @GetMapping
    public ResponseEntity<PagingDto<CheckListDto>> getCheckList(@ParameterObject CheckListParameters parameters) {
        var result = checkListService.getAllPaging(parameters);
        return ResponseEntity.ok(result);
    }

    // Lấy checkList theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CheckListDto>> getCheckList(@PathVariable @NotNull Long id) {
        var result = checkListService.getById(id);
        return ResponseEntity.ok(result);
    }

    // Tạo mới checkList
    @PostMapping
    public ResponseEntity<CheckListDto> createCheckList(@RequestBody @Validated CreateCheckListDto checkListDto) {
        var result = checkListService.createCheckList(checkListDto);
        return ResponseEntity.ok(result);
    }

    // Cập nhật checkList
    @PutMapping("/{id}/update")
    public ResponseEntity<Boolean> updateCheckList(@PathVariable @NotNull Long id,
                                               @RequestBody @Validated UpdateCheckListDto checkListDto) {
        var result = checkListService.updateCheckList(id, checkListDto);
        return ResponseEntity.ok(result);
    }

    // Xóa checkList
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCheckList(@PathVariable @NotNull Long id) {
        checkListService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

