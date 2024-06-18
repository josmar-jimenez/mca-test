package com.mca.core.usercase.impl;

import com.mca.core.usercase.StockService;
import com.mca.infrastructure.db.model.Stock;
import com.mca.infrastructure.db.repository.StockRepository;
import com.mca.infrastructure.model.VideoGameEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private static final ZoneId DEFAULT_UTC = ZoneId.of("UTC");
    private final StockRepository stockRepository;

    @Override
    public boolean updateStock(VideoGameEvent videoGameEvent) {
        Optional<Stock> stockFound = stockRepository.findById(videoGameEvent.getStock_id());
        if(stockFound.isPresent()){
            Stock stock = stockFound.get().toBuilder()
                    .availability(videoGameEvent.isAvailability())
                    .lastUpdated(getZoneId(videoGameEvent.getTime_update())).build();
            stock = stockRepository.save(stock);
            log.info("Stock {} Updated Successful",stock.getId());
            return true;
        }
        return false;
    }

    private ZonedDateTime getZoneId(Timestamp timeUpdate) {
        return ZonedDateTime.ofInstant(timeUpdate.toInstant(), DEFAULT_UTC);
    }
}
