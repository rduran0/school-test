package mx.dev.blank.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.*;
import mx.dev.blank.entity.Student_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
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
  public List<Student> getCourseByStudent(String uuidQuery) {
    final CriteriaBuilder builder= em.getCriteriaBuilder();
    final CriteriaQuery<Student>query=builder.createQuery(Student.class);
    final Root<Student> root= query.from(Student.class);
    final Join<CourseTeacher, Student> joinTeacher = root.join(String.valueOf(Student_.course_teacher_id));
    System.out.println("prueba");
    query.select(root.get(Student_.course_teacher_id.getName()));
    query.where(builder.equal(joinTeacher.get(Student_.uuid), uuidQuery));
    return em.createQuery(query).getResultList();
  }

  @Override
  public List<Course> getCourseTeacherByStudent(String uuidQuery) {
    final CriteriaBuilder builder= em.getCriteriaBuilder();
    final CriteriaQuery<Course>query=builder.createQuery(Course.class);
    final Root<Course> root= query.from(Course.class);
    final Join<CourseTeacher, Student> joinTeacher = root.join(String.valueOf(Student_.course_teacher_id));
    final Join<CourseTeacher, Course> joinCourse = root.join(String.valueOf(CourseTeacher_.course));
    query.multiselect(
            joinCourse.get(Course_.name),
            joinCourse.get(Course_.keycode));
    query.where(builder.equal(joinTeacher.get(Student_.uuid), uuidQuery));
    return em.createQuery(query).getResultList();
  }

  @Override
  public List<CourseTeacher> getCourseByDate(Date startDate, Date endDate, String day) {

    return null;
  }

  @Override
  public List<Course> getCoursesWithoutGrade(String uuidQuery) {
    final CriteriaBuilder builder= em.getCriteriaBuilder();
    final CriteriaQuery<Grade>query=builder.createQuery(Grade.class);
    final Root<Grade> root= query.from(Grade.class);
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
