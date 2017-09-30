package tk.burdukowsky.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by stanislav on 15.04.17.
 */
@SpringBootApplication
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
