package com.techie.microservices.menu.config;

import com.techie.microservices.menu.dto.requests.*;
import com.techie.microservices.menu.dto.responses.*;
import com.techie.microservices.menu.entities.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.Process;

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

        modelMapper.createTypeMap(FormLoad.class, FormLoadDto.class);
        modelMapper.createTypeMap(CreateFormLoadDto.class, FormLoad.class);
        modelMapper.createTypeMap(UpdateFormLoadDto.class, FormLoad.class);

        modelMapper.createTypeMap(CheckList.class, CheckListDto.class);
        modelMapper.createTypeMap(CreateCheckListDto.class, CheckList.class);
        modelMapper.createTypeMap(UpdateCheckListDto.class, CheckList.class);

        return modelMapper;
    }
}
