package hello.spring_start;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloApiTest {
    @Test
    void helloApi() {
        // http localhost:8080/hello?name=Spring
        // HTTPie
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        String name = "Spring";
        ResponseEntity<String> result = testRestTemplate.getForEntity("http://localhost:8080/app/hello?name={name}", String.class, name);

        // status code 200
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header(content-type) text/plain
        assertThat(result.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body Hello Spring
        assertThat(result.getBody()).isEqualTo("*Hello " + name + "*");
    }

    @Test
    void failsHelloApi() {
        // http localhost:8080/hello?name=Spring
        // HTTPie
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        String name = "Spring";
        ResponseEntity<String> result = testRestTemplate.getForEntity("http://localhost:8080/app/hello?name=", String.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
