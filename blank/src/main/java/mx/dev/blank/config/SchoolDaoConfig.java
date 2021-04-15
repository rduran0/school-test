package mx.dev.blank.config;

import mx.dev.blank.dao.CourseDAO;
import mx.dev.blank.dao.CourseJpaDAO;
import mx.dev.blank.dao.StudentDAO;
import mx.dev.blank.dao.StudentJpaDAO;
import mx.dev.blank.dao.TeacherDAO;
import mx.dev.blank.dao.TeacherJpaDAO;
import mx.dev.blank.dao.UserDAO;
import mx.dev.blank.dao.UserJpaDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolDaoConfig {

  @Bean
  public CourseDAO courseDAO() {
    return new CourseJpaDAO();
  }

  @Bean
  public StudentDAO studentDAO() {
    return new StudentJpaDAO();
  }

  @Bean
  public TeacherDAO teacherDAO() {
    return new TeacherJpaDAO();
  }

  @Bean
  public UserDAO userDAO() {
    return new UserJpaDAO();
  }
}
