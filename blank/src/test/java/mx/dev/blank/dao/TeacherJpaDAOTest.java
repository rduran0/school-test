package mx.dev.blank.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.beust.jcommander.internal.Lists;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import mx.dev.blank.DAOTestConfig;
import mx.dev.blank.DBTestConfig;
import mx.dev.blank.entity.Teacher;
import mx.dev.blank.entity.TeacherRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = {DAOTestConfig.class, DBTestConfig.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class TeacherJpaDAOTest extends AbstractTransactionalTestNGSpringContextTests {

    private static final String DBUNIT_XML = "classpath:dbunit/dao/data.xml";

    @Autowired
    private TeacherJpaDAO teacherJpaDAO;

    @DataProvider
    public Object[][] getById_dataProvider() {
        List<String> roleNames = Lists.newArrayList("SECRETARIO", "DIRECTOR", "PROFESOR");


        return new Object[][] {
                {1, "Oscar", roleNames},
        };
    }

    @Test(dataProvider = "getById_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    public void getById_should_returnTeacher(final int teacherID, final String teacherName, List<String> expectedRoles) {
        final Teacher teacher = teacherJpaDAO.getById(teacherID);

        assertThat(teacher).isNotNull();
        assertThat(teacher.getName()).isEqualTo(teacherName);

        assertThat(teacher.getRoles()).hasSize(expectedRoles.size());
        assertThat(teacher.getRoles()).extracting(TeacherRole::getName).hasSameElementsAs(expectedRoles);
        // assertThat(teacher.getRoles()).extracting(TeacherRole::getName).contains(expectedRoles);
    }
}
