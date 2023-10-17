package domain.infra.es.configuration;

import java.util.List;

public class ElasticSearchDataSource {
    private List<ElasticSearchHost> hosts;
    private String clusterName;

    static public class ElasticSearchHost {
        private String host;
        private int port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

    public List<ElasticSearchHost> getHosts() {
        return hosts;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setHosts(List<ElasticSearchHost> hosts) {
        this.hosts = hosts;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
}
