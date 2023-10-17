package domain.infra.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ElasticSearchSimpleClient {
    private final RestHighLevelClient restHighLevelClient;

    public ElasticSearchSimpleClient(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public <T> void insert(IndexRequest indexRequest, T data) throws IOException {
        XContentParser parser = XContentFactory.xContent(XContentType.JSON)
                .createParser(NamedXContentRegistry.EMPTY,
                        DeprecationHandler.THROW_UNSUPPORTED_OPERATION,
                        OBJECT_MAPPER.writeValueAsString(data));
        XContentBuilder builder = XContentFactory.jsonBuilder().copyCurrentStructure(parser);
        indexRequest.source(builder);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    public <T> void insert(String index, T data) throws IOException {
        insert(index, "_doc", data);
    }

    public <T> void insert(String index, String type, T data) throws IOException {
        insert(new IndexRequest(index, type), data);
    }

    public <T> void update(UpdateRequest updateRequest, T data) throws IOException {
        XContentParser parser = XContentFactory.xContent(XContentType.JSON)
                .createParser(NamedXContentRegistry.EMPTY,
                        DeprecationHandler.THROW_UNSUPPORTED_OPERATION,
                        OBJECT_MAPPER.writeValueAsString(data));
        XContentBuilder builder = XContentFactory.jsonBuilder().copyCurrentStructure(parser);
        updateRequest.doc(builder);
        restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    public <T> void update(String index, String id, T data) throws IOException {
        update(new UpdateRequest(index, "_doc", id), data);
    }

    public <T> void update(String index, String type, String id, T data) throws IOException {
        update(new UpdateRequest(index, type, id), data);
    }

    // Select
    public <T> List<ElasticResult<T>> list(SearchRequest searchRequest, Class<T> clazz) throws IOException {
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        return Arrays.stream(response.getHits().getHits())
                .map(it -> {
                    try {
                        return new ElasticResult<>(it.getId(), OBJECT_MAPPER.readValue(it.getSourceAsString(), clazz));
                    } catch (JsonProcessingException e) {
                        return null;
                    }
                }).collect(Collectors.toList());
    }

    public <T> List<ElasticResult<T>> list(String index, BoolQueryBuilder queryBuilder, Class<T> clazz) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);

        if (Objects.nonNull(queryBuilder)) {
            searchRequest.source(new SearchSourceBuilder().query(queryBuilder));
        }

        return list(searchRequest, clazz);
    }

    public <T> List<ElasticResult<T>> findAll(String index, Class<T> clazz) throws IOException {
        return list(index, null, clazz);
    }
}
