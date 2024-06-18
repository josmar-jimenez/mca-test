package com.mca.infrastructure.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="VIDEOGAME")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoGame {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "videogame")
    private Stock stock;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "videogame")
    private List<Promotion> promotion;
}
