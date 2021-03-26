package mx.dev.blank.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Course;
import mx.dev.blank.entity.CourseTeacher;
import mx.dev.blank.entity.CourseTeacher_;
import mx.dev.blank.entity.Course_;
import mx.dev.blank.entity.Room;
import mx.dev.blank.entity.Room_;
import mx.dev.blank.entity.Teacher;
import mx.dev.blank.entity.Teacher_;
import mx.dev.blank.model.CourseRoomDTO;

@RequiredArgsConstructor
public class TeacherJpaDAO implements TeacherDAO {

  @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
  private EntityManager em;

  /**
   * SELECT c.name, c.keycode, r.name
   * FROM course_teacher ct
   *   JOIN teacher t ON ct.teacher_id = t.id
   *   JOIN course c ON ct.course_id = c.id
   *   JOIN room r ON ct.room_id = r.id
   * WHERE t.id = ${teacherId};
   */
  @Override
  public List<CourseRoomDTO> getAssignedCourseRoomsByTeacherId(final int teacherId) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<CourseRoomDTO> query = builder.createQuery(CourseRoomDTO.class);
    final Root<CourseTeacher> root = query.from(CourseTeacher.class);

    final Join<CourseTeacher, Teacher> joinTeacher = root.join(CourseTeacher_.teacher);
    final Join<CourseTeacher, Course> joinCourse = root.join(CourseTeacher_.course);
    final Join<CourseTeacher, Room> joinRoom = root.join(CourseTeacher_.room);

    query.multiselect(
        joinCourse.get(Course_.name),
        joinCourse.get(Course_.keycode),
        joinRoom.get(Room_.name));

    query.where(builder.equal(joinTeacher.get(Teacher_.id), teacherId));

    return em.createQuery(query).getResultList();
  }
}
