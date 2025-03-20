package com.techie.microservices.menu.repositories.interfaces;

import com.techie.microservices.menu.infrastructure.common.IRepositoryBase;
import com.techie.microservices.menu.entities.Product;
import java.util.List;
import java.util.Optional;
import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.common.PagingRequestParameters;
import com.techie.microservices.menu.dto.parameters.ProductParameters;

public interface IProductRepository extends IRepositoryBase<Product, Long> {
    PagingDto<Product> getProducts(ProductParameters parameters);
    Optional<Product> findByProductId(int productId);
    List<Product> findByProductIds(List<Integer> productIds);
    List<Product> findByCodes(List<String> codes);

    Product createProduct(Product product);
    List<Product> createManyProducts(List<Product> products);
    Product updateProduct(Product product);
    List<Product> updateManyProducts(List<Product> products);

    void deleteProduct(Long id);
    void deleteManyProducts(List<Long> ids);
}
