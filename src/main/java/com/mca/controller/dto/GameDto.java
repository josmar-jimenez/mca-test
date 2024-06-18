package com.mca.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
public class GameDto {
    private String id;
    private String title;
    private BigDecimal price;
    private boolean availability;
}
