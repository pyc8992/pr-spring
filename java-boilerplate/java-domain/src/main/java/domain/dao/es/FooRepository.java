package domain.dao.es;

import domain.infra.es.ElasticSearchSimpleRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class FooRepository extends ElasticSearchSimpleRepository<Foo> {
    public FooRepository(RestHighLevelClient restHighLevelClient) {
        super(restHighLevelClient, Foo.class);
    }

    public List<Foo> list(BoolQueryBuilder queryBuilder) throws IOException {
        return super.list(queryBuilder);
    }
}
