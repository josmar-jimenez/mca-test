package com.mca.infrastructure.db.repository;

import com.mca.infrastructure.db.model.Stock;
import com.mca.infrastructure.db.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
