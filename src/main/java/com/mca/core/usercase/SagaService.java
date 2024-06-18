package com.mca.core.usercase;

import com.mca.controller.dto.GameDto;

import java.util.List;

public interface SagaService {
    List<GameDto> getSagaByGameId(String gameId);
}
