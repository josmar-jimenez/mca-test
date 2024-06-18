package com.mca.infrastructure.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name="STOCK")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "LAST_UPDATED")
    private ZonedDateTime lastUpdated;
    @Column(name = "AVAILABILITY")
    private boolean availability;
    @OneToOne
    private VideoGame videogame;
}
