package com.techie.microservices.menu.services;

import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.parameters.ProductParameters;
import com.techie.microservices.menu.dto.requests.CreateProductDto;
import com.techie.microservices.menu.dto.requests.UpdateProductDto;
import com.techie.microservices.menu.dto.responses.ProductDto;
import com.techie.microservices.menu.entities.Product;
import com.techie.microservices.menu.exceptions.ResponseException;
import com.techie.microservices.menu.extensions.AutoMapperExtension;
import com.techie.microservices.menu.repositories.interfaces.IModelRepository;
import com.techie.microservices.menu.repositories.interfaces.IProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.techie.microservices.menu.services.interfaces.IProductService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final IProductRepository repository;
    private final IModelRepository modelRepository;
    private final ModelMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(IProductRepository repository, IModelRepository modelRepository, ModelMapper mapper) {
        this.repository = repository;
        this.modelRepository = modelRepository;
        this.mapper = mapper;
    }

    @Override
    public PagingDto<ProductDto> getAllPaging(ProductParameters query) {
        try {
            var pagging = repository.getProducts(query);
            return AutoMapperExtension.mapPagingList(pagging, ProductDto.class);
        } catch (Exception e) {
            logger.error("Error Get Product by model Id: {} : {}", query.getModelId(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống, vui lòng thử lại sau!", e);
        }
    }

    @Override
    public Optional<ProductDto> getById(Long id) {
        return repository.findById(id).map(product -> mapper.map(product, ProductDto.class));
    }

    @Override
    public ProductDto createProduct(CreateProductDto model) {
        try {
            Optional<Product> productByValue = repository.findByProductId(model.getProductId());
            if (productByValue.isPresent()) {
                throw new ResponseException("Product ID already exists: " + model.getProductId());
            }
            Product entity = mapper.map(model, Product.class);
            // Tìm Model từ modelId
            var modelEntity = modelRepository.findById(Long.valueOf(model.getModelId())).orElseThrow(() -> new ResponseException("Model id do not exits"));
            entity.setModel(modelEntity);
            repository.createProduct(entity);
            return mapper.map(entity, ProductDto.class);
        } catch (ResponseException e) {
            logger.error("Error creating product: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean updateProduct(Long id, UpdateProductDto productDto) {
        try {
            Optional<Product> productOpt = repository.findById(id);
            if (productOpt.isEmpty()) {
                throw new ResponseException("Product not found");
            }
            Product product = productOpt.get();
            mapper.map(productDto, product);
            repository.updateProduct(product);
            return true;
        } catch (ResponseException e) {
            logger.error("Error updating product {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            repository.deleteProduct(id);
            return true;
        } catch (ResponseException e) {
            logger.error("Error deleting product {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<ProductDto> findByProductIds(List<Integer> productIds) {
        var entities = repository.findByProductIds(productIds);
        return AutoMapperExtension.mapList(entities, ProductDto.class);
    }
}
