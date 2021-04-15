package mx.dev.blank.service;

import static mx.dev.blank.factory.FakeDataFactory.student;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import mx.dev.blank.dao.StudentDAO;
import mx.dev.blank.entity.Student;
import mx.dev.blank.model.StudentDTO;
import mx.dev.blank.request.StudentSearchRequest;
import org.assertj.core.groups.Tuple;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

public class StudentServiceTest {

  private StudentDAO studentDAO;

  private StudentService service;

  @BeforeTest
  public void beforeTest() {
    studentDAO = mock(StudentDAO.class);

    service = new StudentService(studentDAO);
  }

  @DataProvider
  public Object[][] searchStudents_dataProvider() {
    final List<Student> students = Lists.newArrayList(
        student("One", "My surname", "Your surname"),
        student("Two"),
        student("Three", "Another", ""));

    return new Object[][] {
        {
            Collections.emptyList(),
            Collections.emptyList()
        },
        {
          students,
            Lists.newArrayList("My surname Your surname", StudentService.UNSPECIFIED_NAME, "Another")
        }
    };
  }

  @Test(dataProvider = "searchStudents_dataProvider")
  public void searchStudents_builtCorrectly(
      final List<Student> daoResult,
      final List<String> surnames) {

    when(
        studentDAO.getStudentsBySearchCriteria(
            anyString(),
            anyString(),
            any(Date.class),
            any(Date.class)))
        .thenReturn(daoResult);

    final StudentSearchRequest request = new StudentSearchRequest();

    final List<StudentDTO> found = service.searchStudents(request);

    assertThat(found)
        .extracting(StudentDTO::getSurnames)
        .hasSameElementsAs(surnames);
  }
}
