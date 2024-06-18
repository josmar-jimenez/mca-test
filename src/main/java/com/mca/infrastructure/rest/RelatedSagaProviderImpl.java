package com.mca.infrastructure.rest;

import com.mca.core.gateway.RelatedSagaProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RelatedSagaProviderImpl implements RelatedSagaProvider {

    private static final String GET_RELATE_SAGA_PATCH = "/game-saga/%s/related-sagas";
    private final WebClient webClient;

    @Override
    public List<String> getRelatedSagaFromGameId(String gameId) {
        try {
            return webClient.get().uri(String.format(GET_RELATE_SAGA_PATCH,gameId))
                    .retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                    .block();
        }catch (Exception e){
            return new ArrayList<>();
        }

    }
}
