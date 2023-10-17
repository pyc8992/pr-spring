package domain.infra.es;

import domain.infra.es.annotation.EsDocument;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ElasticSearchSimpleRepository<T extends EsBaseEntity> {
    private final ElasticSearchSimpleClient elasticSearchSimpleClient;
    private final String index;
    private final String type;
    private final Class<T> clazz;

    public ElasticSearchSimpleRepository(RestHighLevelClient restHighLevelClient, Class<T> clazz) {
        this.elasticSearchSimpleClient = new ElasticSearchSimpleClient(restHighLevelClient);
        if (!clazz.isAnnotationPresent(EsDocument.class)) {
            throw new IllegalArgumentException("Unsupported Class : + " + clazz.getName());
        }

        EsDocument annotation = clazz.getAnnotation(EsDocument.class);
        this.index = annotation.index();
        this.type = annotation.type();
        this.clazz = clazz;
    }

    public void insert(T data) throws IOException {
        elasticSearchSimpleClient.insert(index, type, data);
    }

    public void update(T data) throws IOException {
        elasticSearchSimpleClient.update(index, type, data);
    }

    public List<T> findAll() throws IOException {
        return convert(elasticSearchSimpleClient.findAll(index, clazz));
    }

    public List<T> list(BoolQueryBuilder queryBuilder) throws IOException {
        return convert(elasticSearchSimpleClient.list(index, queryBuilder, clazz));
    }

    protected List<T> convert(List<ElasticResult<T>> results) {
        return results.stream()
                .map(it -> {
                    T source = it.getSource();
                    source.setId(it.getId());
                    return source;
                }).collect(Collectors.toList());
    }
}
