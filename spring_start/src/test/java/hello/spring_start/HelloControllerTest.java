package hello.spring_start;

import hello.spring_start.controller.HelloRestController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

    @Test
    public void helloController() {
        HelloRestController helloController = new HelloRestController(name -> name);

        String result = helloController.hello("Test");

        Assertions.assertThat(result).isEqualTo("Test");
    }

    @Test
    public void failsHelloController() {
        HelloRestController helloController = new HelloRestController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            String result = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            String result = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
