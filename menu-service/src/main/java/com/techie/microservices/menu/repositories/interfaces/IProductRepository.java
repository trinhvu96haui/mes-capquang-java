package com.techie.microservices.menu.repositories.interfaces;

import com.techie.microservices.menu.infrastructure.common.IRepositoryBase;
import com.techie.microservices.menu.entities.Product;

import java.util.List;
import java.util.Optional;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ProductParameters;

public interface IProductRepository extends IRepositoryBase<Product, Long> {
    PagingDto<Product> getProducts(ProductParameters parameters);

    Optional<Product> findByProductId(int productId);

    List<Product> findByProductIds(List<Integer> productIds);

    List<Product> findByValues(List<String> values);

    Optional<Product> findByValue(String value);

    Product create(Product product);

    List<Product> createMany(List<Product> products);

    Product update(Product product);

    List<Product> updateMany(List<Product> products);

    void delete(Long id);

    void deleteMany(List<Long> ids);
}
