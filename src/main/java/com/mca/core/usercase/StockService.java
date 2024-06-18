package com.mca.core.usercase;

import com.mca.infrastructure.model.VideoGameEvent;

public interface StockService {
    boolean updateStock(VideoGameEvent videoGameEvent);
}
