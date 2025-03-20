package com.techie.microservices.menu.services.interfaces;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.requests.CreateProductDto;
import com.techie.microservices.menu.dto.requests.UpdateProductDto;
import com.techie.microservices.menu.dto.responses.ProductDto;
import com.techie.microservices.menu.dto.parameters.ProductParameters;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    PagingDto<ProductDto> getAllPaging(ProductParameters query);
    Optional<ProductDto> getById(Long id);
    boolean updateProduct(Long id, UpdateProductDto product);
    ProductDto createProduct(CreateProductDto model);
    boolean deleteById(Long id);
    List<ProductDto> findByProductIds(List<Integer> productIds);
}
