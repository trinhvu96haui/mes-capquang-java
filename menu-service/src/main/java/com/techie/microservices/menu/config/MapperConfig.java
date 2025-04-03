package com.techie.microservices.menu.config;

import com.techie.microservices.menu.dto.requests.CreateModelDto;
import com.techie.microservices.menu.dto.requests.CreateProductDto;
import com.techie.microservices.menu.dto.requests.UpdateModelDto;
import com.techie.microservices.menu.dto.requests.UpdateProductDto;
import com.techie.microservices.menu.dto.responses.ModelDto;
import com.techie.microservices.menu.dto.responses.ProductDto;
import com.techie.microservices.menu.entities.Model;
import com.techie.microservices.menu.entities.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(Product.class, ProductDto.class);
        modelMapper.createTypeMap(CreateProductDto.class, Product.class);
        modelMapper.createTypeMap(UpdateProductDto.class, Product.class);

        modelMapper.createTypeMap(Model.class, ModelDto.class);
        modelMapper.createTypeMap(CreateModelDto.class, Model.class);
        modelMapper.createTypeMap(UpdateModelDto.class, Model.class);

        return modelMapper;
    }
}
