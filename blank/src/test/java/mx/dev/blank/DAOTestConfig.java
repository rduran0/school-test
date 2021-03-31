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
    private static final String DBUNIT_XML = "classpath:dbunit/dao/teacher.xml";

    @Autowired  private StudentDAO studentDAO;

    @DataProvider
    public Object[][] findByID_dataProvider() {
        return new Object[][] {
                {"U-1"},
                {"U-2"},
                {null}
        };
    }

    @Test(dataProvider = "findByID_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    public void getCourseByStudent(final String uuidQuery) {
        final List<Student> course = studentDAO.getCourseByStudent(uuidQuery);
        assertThat(course)
                .extracting(Student::getUuid)
                .containsExactlyInAnyOrderElementsOf(Collections.singleton(uuidQuery));
    }

}
