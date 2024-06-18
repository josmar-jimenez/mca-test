package com.mca.core.usercase.impl;

import com.mca.controller.dto.GameDto;
import com.mca.core.gateway.RelatedSagaProvider;
import com.mca.core.usercase.SagaService;
import com.mca.infrastructure.db.model.VideoGame;
import com.mca.infrastructure.db.repository.VideoGameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SagaServiceImpl implements SagaService {

    private final RelatedSagaProvider relatedSagaProvider;
    private final VideoGameRepository videoGameRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GameDto> getSagaByGameId(String gameId) {
        List<String> relatedSagaFromProvider = relatedSagaProvider.getRelatedSagaFromGameId(gameId);
        return CollectionUtils.isEmpty(relatedSagaFromProvider)?new ArrayList<>():getGamesListFromIds(relatedSagaFromProvider);
    }

    private List<GameDto> getGamesListFromIds(List<String> relatedSagaFromProvider) {
        final List<GameDto> response =  new ArrayList<>();
        relatedSagaFromProvider.forEach(gameId -> {
            Optional<VideoGame> videoGame = videoGameRepository.findById(Long.parseLong(gameId));
            videoGame.ifPresent(game -> response.add(modelMapper.map(game, GameDto.class)));
        });
        return response;
    }
}
