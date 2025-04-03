package com.techie.microservices.menu.controller;

import com.techie.microservices.menu.dto.parameters.ProductParameters;
import com.techie.microservices.menu.dto.requests.CreateProductDto;
import com.techie.microservices.menu.dto.requests.UpdateProductDto;
import com.techie.microservices.menu.dto.responses.ProductDto;
import com.techie.microservices.menu.services.interfaces.IProductService;
import com.techie.microservices.menu.dto.common.PagingDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    // Lấy danh sách sản phẩm có phân trang
    @GetMapping
    public ResponseEntity<PagingDto<ProductDto>> getProducts(@ParameterObject ProductParameters parameters) {
        var result = productService.getAllPaging(parameters);
        return ResponseEntity.ok(result);
    }

    // Lấy sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductDto>> getProduct(@PathVariable @NotNull Long id) {
        var result = productService.getById(id);
        return ResponseEntity.ok(result);
    }

    // Tạo mới sản phẩm
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Validated CreateProductDto productDto) {
        var result = productService.createProduct(productDto);
        return ResponseEntity.ok(result);
    }

    // Cập nhật sản phẩm
    @PutMapping("/{id}/update")
    public ResponseEntity<Boolean> updateProduct(@PathVariable @NotNull Long id,
                                                 @RequestBody @Validated UpdateProductDto productDto) {
        var result = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(result);
    }

    // Xóa sản phẩm
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteProduct(@PathVariable @NotNull Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Lấy sản phẩm theo productIds
    @GetMapping("/find-by-productIds")
    public ResponseEntity<List<ProductDto>> findByProducts(@RequestParam List<Integer> productIds) {
        var result = productService.findByProductIds(productIds);
        return ResponseEntity.ok(result);
    }
}

