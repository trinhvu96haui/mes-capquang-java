package com.techie.microservices.menu.repositories;

import com.techie.microservices.menu.infrastructure.common.RepositoryBase;
import com.techie.microservices.menu.entities.Product;
import com.techie.microservices.menu.repositories.interfaces.IProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.common.PagingRequestParameters;
import com.techie.microservices.menu.dto.parameters.ProductParameters;
import com.techie.microservices.menu.extensions.QueryableExtensions;

@Repository
@Transactional
public class ProductRepository extends RepositoryBase<Product, Long> implements IProductRepository {

    public ProductRepository(EntityManager entityManager) {
        super(Product.class);
        this.entityManager = entityManager;
    }

    @Override
    public PagingDto<Product> getProducts(ProductParameters parameters) {
        Stream<Product> query = findAll().stream();

        // Áp dụng điều kiện tìm kiếm theo keyword nếu có sử dụng whereIf
        query = new QueryableExtensions<>(query)
                .whereIf(parameters.getKeyword() != null,
                        p -> p.getName().toLowerCase().contains(parameters.getKeyword().toLowerCase()) ||
                                p.getValue().toLowerCase().contains(parameters.getKeyword().toLowerCase()))
                .getStream(); // Lấy lại stream sau khi lọc từ whereIf

        // Áp dụng sắp xếp giảm dần theo trường Created
        query = query.sorted(Comparator.comparing(Product::getCreated).reversed());

        // Tiến hành phân trang và trả về kết quả
        return new QueryableExtensions<>(query)
                .paginate(parameters);
    }

    @Override
    public Optional<Product> findByProductId(int productId) {
        TypedQuery<Product> query = entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.productId = :productId", Product.class);
        query.setParameter("productId", productId);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<Product> findByProductIds(List<Integer> productIds) {
        TypedQuery<Product> query = entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.productId IN :productIds", Product.class);
        query.setParameter("productIds", productIds);
        return query.getResultList();
    }

    @Override
    public List<Product> findByValues(List<String> values) {
        var sql = "SELECT p FROM Product p WHERE p.value IN :values";
        TypedQuery<Product> query = entityManager.createQuery(sql, Product.class);
        query.setParameter("values", values);
        return query.getResultList();
    }

    @Override
    public Optional<Product> findByValue(String value) {
        var sql = "SELECT p FROM Product p WHERE p.value = :value";
        TypedQuery<Product> query = entityManager.createQuery(sql, Product.class);
        query.setParameter("value", value);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Product create(Product product) {
        entityManager.persist(product);
        return product;
    }

    @Override
    public List<Product> createMany(List<Product> products) {
        for (Product product : products) {
            entityManager.persist(product);
        }
        return products;
    }

    @Override
    public Product update(Product product) {
        return entityManager.merge(product);
    }

    @Override
    public List<Product> updateMany(List<Product> products) {
        for (Product product : products) {
            entityManager.merge(product);
        }
        return products;
    }

    @Override
    public void delete(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    @Override
    public void deleteMany(List<Long> ids) {
        for (Long id : ids) {
            delete(id);
        }
    }

}
