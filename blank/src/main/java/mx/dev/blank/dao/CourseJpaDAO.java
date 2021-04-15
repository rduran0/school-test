package mx.dev.blank.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
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
import mx.dev.blank.entity.Teacher;
import mx.dev.blank.entity.Teacher_;

@RequiredArgsConstructor
public class CourseJpaDAO implements CourseDAO {

  @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
  private EntityManager em;

  /**
   * INSERT INTO course (name, keycode) VALUES (${name}, ${keycode})
   */
  @Override
  public void create(final Course course) {
    em.persist(course);
  }

  /**
   * UPDATE course SET name=${name},keycode=${keycode} WHERE id = ${id}
   */
  @Override
  public void update(final Course course) {
    em.merge(course);
  }

  /**
   * DELETE FROM course WHERE id = ${id}
   */
  @Override
  public void delete(final Course course) {
    em.remove(course);
  }

  /**
   * DELETE FROM course WHERE keycode LIKE '${prefix}%'
   */
  @Override
  public void deleteByKeycodePrefix(final String prefix) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaDelete<Course> delete = builder.createCriteriaDelete(Course.class);
    final Root<Course> root = delete.from(Course.class);

    delete.where(builder.like(root.get(Course_.keycode), prefix + "%"));

    em.createQuery(delete).executeUpdate();
  }

  @Override
  public Course getByKeycode(final String keycode) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Course> query = builder.createQuery(Course.class);
    final Root<Course> root = query.from(Course.class);

    query.select(root).where(builder.equal(root.get(Course_.keycode), keycode));

    return HibernateHelper.getSingleResult(em, query);
  }

  @Override
  public CourseTeacher getCourseAssignation(final String courseKeycode, final String teacherUuid) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<CourseTeacher> query = builder.createQuery(CourseTeacher.class);
    final Root<CourseTeacher> root = query.from(CourseTeacher.class);

    final Join<CourseTeacher, Course> courseJoin = root.join(CourseTeacher_.course);
    final Join<CourseTeacher, Teacher> teacherJoin = root.join(CourseTeacher_.teacher);

    query.select(root).where(builder.and(
        builder.equal(courseJoin.get(Course_.keycode), courseKeycode),
        builder.equal(teacherJoin.get(Teacher_.uuid), teacherUuid)
    ));

    return HibernateHelper.getSingleResult(em, query);
  }
}
