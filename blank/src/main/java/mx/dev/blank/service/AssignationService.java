package mx.dev.blank.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.dev.blank.dao.CourseDAO;
import mx.dev.blank.dao.TeacherDAO;
import mx.dev.blank.entity.CourseTeacher;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Log4j2
public class AssignationService {

  private final StudentService studentService;

  private final CourseDAO courseDAO;
  private final TeacherDAO teacherDAO;

  @Transactional
  public void assignCourseToStudent(final String keycode, final String teacherUuid, final String studentUuid) {
    final CourseTeacher assignation = courseDAO.getCourseAssignation(keycode, teacherUuid);

    if (assignation == null) {
      log.warn(
          "[OPERATION-FAILED] Assignation not found for Teacher {} and couse {}",
          teacherUuid,
          keycode);
      return;
    }

    studentService.assignCourse(assignation, studentUuid);

    log.info("[OPERATION-SUCCESS] Assignation complete");
  }
}
