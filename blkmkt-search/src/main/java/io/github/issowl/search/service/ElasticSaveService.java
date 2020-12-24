package io.github.issowl.search.service;

import io.github.common.to.es.model.GoodModel;

import java.io.IOException;
import java.util.List;

public interface ElasticSaveService {
    boolean saveGoodAsIndices(List<GoodModel> goodModelList) throws IOException;
}
