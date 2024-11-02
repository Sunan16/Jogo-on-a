package com.jogonca.api_backend.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jogonca.api_backend.mapper.Mapper;
import com.jogonca.api_backend.models.Category;
import com.jogonca.api_backend.models.Coin;
import com.jogonca.api_backend.models.Hability;
import com.jogonca.api_backend.models.Item;
import com.jogonca.api_backend.models.Payment;
import com.jogonca.api_backend.models.User;
import com.jogonca.api_backend.models.dtos.receiveDTOs.CategoryDTO;
import com.jogonca.api_backend.models.dtos.receiveDTOs.CoinDTO;
import com.jogonca.api_backend.models.dtos.receiveDTOs.HabilityDTO;
import com.jogonca.api_backend.models.dtos.receiveDTOs.ItemDTO;
import com.jogonca.api_backend.models.dtos.receiveDTOs.PaymentDTO;
import com.jogonca.api_backend.models.dtos.receiveDTOs.UserDTO;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        var modelMapper = new ModelMapper();


        // Category
        modelMapper.createTypeMap(Category.class, CategoryDTO.class)
            .addMappings(mapper -> {
                mapper.<Long>map(src -> src.getId(), (dest, value) -> dest.setKey(value));
        });
        modelMapper.createTypeMap(CategoryDTO.class, Category.class)
            .addMappings(mapper -> {
                mapper.<Long>map(src -> src.getKey(), (dest, value) -> dest.setId(value));
        });


        // Coin
        modelMapper.createTypeMap(Coin.class, CoinDTO.class)
            .addMappings(mapper -> {
                mapper.<Long>map(src -> src.getId(), (dest, value) -> dest.setKey(value));
                mapper.<Long>map(src -> src.getPayment().getId(), (dest, value) -> dest.setIdPayment(value));
        });
        modelMapper.createTypeMap(CoinDTO.class, Coin.class)
            .addMappings(mapper -> {
                mapper.<Long>map(src -> src.getKey(), (dest, value) -> dest.setId(value));
        }); 


        // Hability
        modelMapper.createTypeMap(Hability.class, HabilityDTO.class)
            .addMappings(mapper -> {
                mapper.<Long>map(src -> src.getId(), (dest, value) -> dest.setKey(value));
        });
        modelMapper.createTypeMap(HabilityDTO.class, Hability.class)
            .addMappings(mapper -> {
                mapper.<Long>map(src -> src.getKey(), (dest, value) -> dest.setId(value));
        });
        

        // Item
        modelMapper.createTypeMap(Item.class, ItemDTO.class)
        .addMappings(mapper -> {
            mapper.<Long>map(src -> src.getId(), (dest, value) -> dest.setKey(value));
            mapper.<Long>map(src -> src.getCategory(), (dest, value) -> dest.setCategory(Mapper.parseObject(value, CategoryDTO.class)));
        });
        modelMapper.createTypeMap(ItemDTO.class, Item.class)
            .addMappings(mapper -> {
                mapper.<Long>map(src -> src.getKey(), (dest, value) -> dest.setId(value));
        });


        // Payment
        modelMapper.createTypeMap(Payment.class, PaymentDTO.class)
            .addMappings(mapper -> {
                mapper.<Long>map(src -> src.getId(), (dest, value) -> dest.setKey(value));
                mapper.<Long>map(src -> src.getUser().getId(), (dest, value) -> dest.setIdUser(value));
        });
        modelMapper.createTypeMap(PaymentDTO.class, Payment.class)
            .addMappings(mapper -> {
                mapper.<Long>map(src -> src.getKey(), (dest, value) -> dest.setId(value));
        });


        // User
        modelMapper.createTypeMap(User.class, UserDTO.class)
            .addMappings(mapper -> {
                mapper.<Long>map(src -> src.getId(), (dest, value) -> dest.setKey(value));
        });
        modelMapper.createTypeMap(UserDTO.class, User.class)
            .addMappings(mapper -> {
                mapper.<Long>map(src -> src.getKey(), (dest, value) -> dest.setId(value));
        });

        return modelMapper;
    }
}