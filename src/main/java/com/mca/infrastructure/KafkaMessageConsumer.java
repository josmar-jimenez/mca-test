package com.mca.infrastructure;

import com.google.gson.Gson;
import com.mca.core.usercase.StockService;
import com.mca.infrastructure.db.model.Stock;
import com.mca.infrastructure.model.VideoGameEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageConsumer {

    private final StockService stockService;

    @KafkaListener(topics = "#{'${topic}'.split(',')}")
    public void listen(ConsumerRecord<String, String> record)
    {
        log.info("Received messages on topic [{}]: [{}]", record.topic(), record.value());
        VideoGameEvent videoGameEvent = getVideoGameEvent(record.value());
        stockService.updateStock(videoGameEvent);
    }

    private VideoGameEvent getVideoGameEvent(String json){
        try{
            return new Gson().fromJson(json, VideoGameEvent.class);
        }catch (Exception e){
            return null;
        }
    }
}
