package com.mca.config;

import com.mca.controller.dto.GameDto;
import com.mca.infrastructure.db.model.Promotion;
import com.mca.infrastructure.db.model.Stock;
import com.mca.infrastructure.db.model.VideoGame;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(convertVideoGameToGameDTO);
        return modelMapper;
    }

    private final AbstractConverter<VideoGame, GameDto> convertVideoGameToGameDTO =
            new AbstractConverter<>() {
                @Override
                protected GameDto convert(VideoGame videoGame) {
                    GameDto response =GameDto.builder().build();
                    if(Objects.nonNull(videoGame)){
                        return response.toBuilder()
                                .id(videoGame.getId().toString())
                                .price(getPrice(videoGame.getPromotion()))
                                .title(videoGame.getTitle())
                                .availability(isAvailable(videoGame.getStock())).build();
                    }
                    return response;
                }
            };

    private BigDecimal getPrice(List<Promotion> promotion) {
        if(CollectionUtils.isEmpty(promotion))
            return BigDecimal.ZERO;
        return promotion.stream().sorted(Comparator.comparing(Promotion::getValidFrom).reversed())
                .toList().get(0).getPrice();
    }

    private boolean isAvailable(Stock stock) {
        return !Objects.isNull(stock) && stock.isAvailability();
    }
}
