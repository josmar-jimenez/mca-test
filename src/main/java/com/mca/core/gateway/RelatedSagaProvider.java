package com.mca.core.gateway;

import java.util.List;

public interface RelatedSagaProvider {
    List<String> getRelatedSagaFromGameId(String gameId);
}
