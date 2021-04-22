package mx.dev.blank;

import mx.dev.blank.service.CourseServiceManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableCaching
@Import(CourseServiceManager.Config.class)
public class BlankApplication {

  public static void main(final String[] args) throws Exception {
    SpringApplication.run(BlankApplication.class, args);
  }
}
