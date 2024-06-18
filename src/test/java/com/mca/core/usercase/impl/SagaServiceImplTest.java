package com.mca.core.usercase.impl;

import com.mca.controller.dto.GameDto;
import com.mca.core.gateway.RelatedSagaProvider;
import com.mca.infrastructure.db.model.VideoGame;
import com.mca.infrastructure.db.repository.VideoGameRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SagaServiceImplTest {

    private static final String SAGA_ID ="1";
    @MockBean
    private final RelatedSagaProvider relatedSagaProvider = mock(RelatedSagaProvider.class);
    @MockBean
    private final VideoGameRepository videoGameRepository = mock(VideoGameRepository.class);
    @MockBean
    private final ModelMapper modelMapper = mock(ModelMapper.class);

    private final SagaServiceImpl service = new SagaServiceImpl(relatedSagaProvider,videoGameRepository,modelMapper);

    @Test
    void shouldReturnCorrectObject(){
        final String VIDEO_GAME_ID = "1";
        final String VIDEO_GAME_TITLE = "title";
        VideoGame videoGame = VideoGame.builder().id(1L).title(VIDEO_GAME_TITLE).build();
        when(relatedSagaProvider.getRelatedSagaFromGameId(SAGA_ID)).thenReturn(List.of(VIDEO_GAME_ID));
        when(videoGameRepository.findById(1L)).thenReturn(Optional.of(videoGame));
        when(modelMapper.map(any(), Mockito.eq(GameDto.class))).thenReturn(GameDto.builder().id(VIDEO_GAME_ID)
                .title(VIDEO_GAME_TITLE).build());
        List<GameDto> result = service.getSagaByGameId(SAGA_ID);
        Assertions.assertEquals(result.get(0).getId(),VIDEO_GAME_ID);
        Assertions.assertEquals(result.get(0).getTitle(),VIDEO_GAME_TITLE);
    }

    @Test
    void shouldReturnEmptyListWhenProviderReturnEmptyList(){
        when(relatedSagaProvider.getRelatedSagaFromGameId(SAGA_ID)).thenReturn(new ArrayList<>());
        List<GameDto> result = service.getSagaByGameId(SAGA_ID);
        Assertions.assertTrue(CollectionUtils.isEmpty(result));
    }

    @Test
    void shouldReturnEmptyListWhenGameDoesNotExists(){
        when(relatedSagaProvider.getRelatedSagaFromGameId(SAGA_ID)).thenReturn(List.of("1"));
        when(videoGameRepository.findById(1L)).thenReturn(Optional.empty());
        List<GameDto> result = service.getSagaByGameId(SAGA_ID);
        Assertions.assertTrue(CollectionUtils.isEmpty(result));
    }
}
