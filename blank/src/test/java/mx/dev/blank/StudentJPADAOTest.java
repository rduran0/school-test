package mx.dev.blank;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import mx.dev.blank.dao.StudentDAO;
import mx.dev.blank.entity.Course;
import mx.dev.blank.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.assertThat;

@Test(groups = "db")
@ContextConfiguration(classes = {DAOTestConfig.class, DBTestConfig.class})

public class StudentJPADAOTest {
    private static final String DBUNIT_XML = "classpath:dbunit/dao/teacher.xml";

    @Autowired
    private StudentDAO studentDAO;

    @DataProvider
    public Object[][] findByID_dataProvider() {
        return new Object[][] {
                {"U-1"}
        };
    }

    @Test(dataProvider = "findByID_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    public void getCourseByStudent(final String uuidQuery) {
        System.out.println("prueba"+ uuidQuery);

        System.out.println("prueba"+ studentDAO.getCourseByStudent(uuidQuery));
        final List<Course> course = studentDAO.getCourseByStudent(uuidQuery);

        assertThat(course)
                .extracting(Course::getId)
                .containsExactlyInAnyOrderElementsOf(EMPTY_LIST);
    }
}
