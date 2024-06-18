package com.mca.core.usercase.impl;

import com.mca.controller.dto.GameDto;
import com.mca.core.gateway.RelatedSagaProvider;
import com.mca.infrastructure.db.model.Stock;
import com.mca.infrastructure.db.model.VideoGame;
import com.mca.infrastructure.db.repository.StockRepository;
import com.mca.infrastructure.db.repository.VideoGameRepository;
import com.mca.infrastructure.model.VideoGameEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StockServiceImplTest {

    private static final Long STOCK_ID = 1L;

    @MockBean
    private final StockRepository stockRepository = mock(StockRepository.class);

    private final StockServiceImpl service = new StockServiceImpl(stockRepository);

    @Test
    void shouldReturnTrueWhenAllAreOk(){
        Stock stock = Stock.builder().id(STOCK_ID).build();
        VideoGameEvent videoGameEvent = VideoGameEvent.builder().stock_id(STOCK_ID)
                .time_update(Timestamp.from(ZonedDateTime.now().toInstant())).build();
        when(stockRepository.findById(STOCK_ID)).thenReturn(Optional.of(stock));
        when(stockRepository.save(any())).thenReturn(stock);
        Assertions.assertTrue(service.updateStock(videoGameEvent));
    }

    @Test
    void shouldReturnFalseWhenStockDoesNotExists(){
        VideoGameEvent videoGameEvent = VideoGameEvent.builder().stock_id(STOCK_ID).build();
        when(stockRepository.findById(STOCK_ID)).thenReturn(Optional.empty());
        Assertions.assertFalse(service.updateStock(videoGameEvent));
    }
}
