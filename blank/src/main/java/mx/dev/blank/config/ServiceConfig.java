package mx.dev.blank.config;

import mx.dev.blank.dao.StudentDAO;
import mx.dev.blank.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

  @Bean
  public StudentService studentService(final StudentDAO studentDAO) {
    return new StudentService(studentDAO);
  }
}
