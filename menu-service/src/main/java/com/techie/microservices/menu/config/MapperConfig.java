package com.techie.microservices.menu.config;

import com.techie.microservices.menu.dto.requests.*;
import com.techie.microservices.menu.dto.responses.GroupProcessDto;
import com.techie.microservices.menu.dto.responses.ModelDto;
import com.techie.microservices.menu.dto.responses.ProcessDto;
import com.techie.microservices.menu.dto.responses.ProductDto;
import com.techie.microservices.menu.entities.GroupProcess;
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

        modelMapper.createTypeMap(Process.class, ProcessDto.class);
        modelMapper.createTypeMap(CreateProcessDto.class, Process.class);
        modelMapper.createTypeMap(UpdateProcessDto.class, Process.class);

        modelMapper.createTypeMap(GroupProcess.class, GroupProcessDto.class);
        modelMapper.createTypeMap(CreateGroupProcessDto.class, GroupProcess.class);
        modelMapper.createTypeMap(UpdateGroupProcessDto.class, GroupProcess.class);

        return modelMapper;
    }
}
