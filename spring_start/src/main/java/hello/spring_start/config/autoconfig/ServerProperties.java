package hello.spring_start.config.autoconfig;

import hello.spring_start.config.MyConfigurationProperties;
import org.springframework.stereotype.Component;

@MyConfigurationProperties(prefix = "server")
public class ServerProperties {
    private String contextPath;
    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
