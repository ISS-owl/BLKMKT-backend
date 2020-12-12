package io.github.issowl.search.service;

import java.io.IOException;
import java.util.List;

public interface ElasticDeleteService {
    boolean deleteGoodAsIndices(List<Integer> ids) throws IOException;
}
