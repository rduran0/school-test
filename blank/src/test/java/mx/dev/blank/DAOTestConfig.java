package mx.dev.blank;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import mx.dev.blank.dao.StudentDAO;
import mx.dev.blank.dao.StudentJpaDAO;
import mx.dev.blank.entity.Course;
import mx.dev.blank.entity.Student;
import mx.dev.blank.entity.Student_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Configuration
@ComponentScan(basePackages = "mx.dev.blank.dao", scopedProxy = ScopedProxyMode.INTERFACES)
public class DAOTestConfig {

}
