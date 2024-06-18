package com.mca.infrastructure.db.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Comparator;

@Entity
@Table(name="PROMOTION")
@Getter
public class Promotion implements Comparable {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "VALID_FROM")
    private ZonedDateTime validFrom;
    @Column(name = "PRICE")
    private BigDecimal price;
    @ManyToOne
    private VideoGame videogame;

    @Override
    public int compareTo(Object p) {
        if(p instanceof Promotion promotion)
        {
            return (this.validFrom.compareTo(promotion.validFrom));
        }
        else
            return -1;
    }
}
