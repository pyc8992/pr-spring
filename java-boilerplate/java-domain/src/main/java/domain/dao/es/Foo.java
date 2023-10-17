package domain.dao.es;

import domain.infra.es.EsBaseEntity;
import domain.infra.es.annotation.EsDocument;

@EsDocument(index = "foo")
public class Foo extends EsBaseEntity {
    private Long bar1;
    private String bar2;
}
