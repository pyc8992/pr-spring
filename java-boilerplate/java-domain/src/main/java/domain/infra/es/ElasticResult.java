package domain.infra.es;

public class ElasticResult<T> {
    private String id;

    private T source;

    public ElasticResult(String id, T source) {
        this.id = id;
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }
}
