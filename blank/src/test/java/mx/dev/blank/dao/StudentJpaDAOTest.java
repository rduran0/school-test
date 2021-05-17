package mx.dev.blank.dao;

import static org.assertj.core.api.Assertions.assertThat;
import com.beust.jcommander.internal.Lists;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import mx.dev.blank.DAOTestConfig;
import mx.dev.blank.DBTestConfig;
import mx.dev.blank.entity.Student;
import mx.dev.blank.entity.Teacher;
import mx.dev.blank.entity.TeacherRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@ContextConfiguration(classes = {DAOTestConfig.class, DBTestConfig.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class StudentJpaDAOTest extends AbstractTransactionalTestNGSpringContextTests {

    private static final String DBUNIT_XML = "classpath:dbunit/dao/data.xml";

    @Autowired
    private StudentJpaDAO studentJpaDAO;

    @DataProvider
    public Object[][] getStudentsBySearchCriteria_dataProvider() {
        List<Integer> studentsIDs = Lists.newArrayList(1, 2);


        return new Object[][] {
                {null, null,  getFromValues(1990, 1, 1), getFromValues(1993, 1,1 ), studentsIDs},
                {"Santos", null,  getFromValues(1990, 1, 1), getFromValues(1995, 1,1 ), studentsIDs},
        };
    }

    @Test(dataProvider = "getStudentsBySearchCriteria_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    public void getById_should_returnTeacher(final String nameQuery,
                                             final String uuidQuery,
                                             final Date rangeStart,
                                             final Date rangeEnd,
                                             final List<Integer> expectedIDs) {
        final List<Student> students = studentJpaDAO.getStudentsBySearchCriteria(nameQuery, uuidQuery, rangeStart, rangeEnd);

        assertThat(students).extracting(Student::getId).hasSameElementsAs(expectedIDs);
    }


    private Date getFromValues(int year, int month, int dayOfMonth){
        return Date.from(LocalDate.of(year, month, dayOfMonth).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
