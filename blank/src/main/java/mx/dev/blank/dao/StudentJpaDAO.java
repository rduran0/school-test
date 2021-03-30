package mx.dev.blank.dao;

import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Course;
import mx.dev.blank.entity.CourseTeacher;
import mx.dev.blank.entity.Student;
import mx.dev.blank.entity.Student_;
import mx.dev.blank.entity.Teacher;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class StudentJpaDAO implements StudentDAO {

  @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
  private EntityManager em;

  @Override
  public List<Student> getStudentsBySearchCriteria(
      final String nameQuery,
      final String uuidQuery,
      final Date rangeStart,
      final Date rangeEnd) {

    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Student> query = builder.createQuery(Student.class);
    final Root<Student> root = query.from(Student.class);

    query.select(root);

    final List<Predicate> predicates = new ArrayList<>();
    predicates.add(builder.between(root.get(Student_.birthday), rangeStart, rangeEnd));

    buildSearchCriteria(predicates, builder, root, nameQuery, uuidQuery);

    return em.createQuery(query).getResultList();
  }

  @Override
  public List<Course> getCourseByStudent(String uuidQuery) {
    final CriteriaBuilder builder= em.getCriteriaBuilder();
    final CriteriaQuery<Course>query=builder.createQuery(Course.class);
    final Root<Course> root= query.from(Course.class);
    query.select(root).where(root.get(Course_.course),
    builder.equal(root.get(Course_.studentUUID),uuidQuery));
    return em.createQuery(query).getResultList();
  }

  @Override
  public List<Course> getCourseTeacherByStudent(String uuidQuery) {
    final CriteriaBuilder builder= em.getCriteriaBuilder();
    final CriteriaQuery<Student>query=builder.createQuery(Student.class);
    final Root<CourseTeacher> root= query.from(CourseTeacher.class);
    final Join<Course,CourseTeacher> courseTeacherJoin=root.join(root.join(CourseTeacher.course_id));
    final Join<CourseTeacher, Teacher> teacherJoin=root.join(root.join(CourseTeacher_.courseT));
    query.multiselect(teacherJoin.get(Teacher_.name),
            courseTeacherJoin.get(Course_.name),courseTeacherJoin.get(Course_.keycode))
            .where(query.get());
    return null;
  }

  @Override
  public List<CourseTeacher> getCourseByDate(Date startDate, Date endDate, String day) {
    final CriteriaBuilder builder= em.getCriteriaBuilder();
    final CriteriaQuery<CourseTeacher> query=builder.createQuery(CourseTeacher.class);
    final Root<CourseTeacher> root= query.from(CourseTeacher.class);
    final List<Predicate> predicates = new ArrayList<>();
    predicates.add(builder.between(root.get(CourseTeacher_.birthday), startDate, endDate));
    return null;
  }

  @Override
  public List<CourseTeacher> getCourseByDate(Date startDate, Date endDate) {
    return null;
  }

  @Override
  public List<Course> getCoursesWithoutGrade(String uuidQuery) {
    return null;
  }

  private void buildSearchCriteria(
      final List<Predicate> predicates,
      final CriteriaBuilder builder,
      final Root<Student> root,
      final String nameQuery,
      final String uuidQuery) {

    if (StringUtils.isNotBlank(nameQuery)) {
      final String queryFormat = "%" + nameQuery + "%";

      predicates.add(builder.like(root.get(Student_.name), queryFormat));
      predicates.add(builder.like(root.get(Student_.firstSurname), queryFormat));
      predicates.add(builder.like(root.get(Student_.secondSurname), queryFormat));
    }

    if (uuidQuery != null && uuidQuery.trim().isEmpty()) {
      final String queryFormat = "%" + uuidQuery + "%";

      predicates.add(builder.like(root.get(Student_.uuid), queryFormat));
    }
  }
}
