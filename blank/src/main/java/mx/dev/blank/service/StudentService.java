package mx.dev.blank.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.dev.blank.dao.StudentDAO;
import mx.dev.blank.entity.CourseTeacher;
import mx.dev.blank.entity.Student;
import mx.dev.blank.model.StudentDTO;
import mx.dev.blank.request.StudentSearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Log4j2
public class StudentService {

  /**
   * Package visible for testing
   */
  public final static String UNSPECIFIED_NAME = "--UNSPECIFIED--";

  private final StudentDAO studentDAO;

  @Transactional(readOnly = true)
  public List<StudentDTO> searchStudents(final StudentSearchRequest criteria) {
    final List<Student> found =
        studentDAO.getStudentsBySearchCriteria(
            criteria.getName(), criteria.getUuid(), criteria.getStart(), criteria.getEnd());

    log.debug("[STUDENT-SEARCH] Students found: {}", found.size());

    return found.stream().map(s -> buildStudentDTO(s)).collect(Collectors.toList());
  }

  private StudentDTO buildStudentDTO(final Student student) {
    final StudentDTO.Builder builder = StudentDTO.builder();

    builder
        .uuid(student.getUuid())
        .name(student.getName());

    if (StringUtils.isNotBlank(student.getFirstSurname())) {
      if (StringUtils.isNotBlank(student.getSecondSurname())) {
        builder.surnames(student.getFirstSurname() + " " + student.getSecondSurname());
      } else {
        builder.surnames(student.getFirstSurname());
      }
    } else {
      log.warn("[STUDENT-SEARCH] No surnames found for {}", student.getUuid());
      builder.surnames(UNSPECIFIED_NAME);
    }

    return builder.build();
  }

  @Transactional
  public void assignCourse(final CourseTeacher assignation, final String uuid) {
    final Student student = studentDAO.getByUuid(uuid);

    student.setAssignedCourse(assignation);

    studentDAO.update(student);
  }
}
