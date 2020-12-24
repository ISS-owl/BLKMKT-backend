package io.github.issowl.search.service;


import io.github.common.to.es.model.GoodModel;

import java.io.IOException;
import java.util.List;

public interface ElasticUpdateService {
    boolean updateGoodAsIndices(List<GoodModel> goodModelList) throws IOException;
}
