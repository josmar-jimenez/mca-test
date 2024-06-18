package com.mca.infrastructure.db.repository;

import com.mca.infrastructure.db.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
}
