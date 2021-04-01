package mx.dev.blank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Course;
import mx.dev.blank.entity.CourseTeacher;
import mx.dev.blank.entity.CourseTeacher_;
import mx.dev.blank.entity.Course_;

@RequiredArgsConstructor
@Repository
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
  public Course findById(final long courseId) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Course> query = builder.createQuery(Course.class);
    final Root<Course> root = query.from(Course.class);

    query.where(builder.equal(root.get(Course_.id), courseId));

    return HibernateHelper.getSingleResult(em, query);
  }
  
  @Override
  public List<Course> getCourses() {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Course> query = builder.createQuery(Course.class);
    query.from(Course.class);

    return HibernateHelper.getResults(em, query);
  }
  
  @Override
  public int countTeachersById(final long courseId) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    final Root<CourseTeacher> root = query.from(CourseTeacher.class);
    
    
    query
    .select(builder.countDistinct(root.get(CourseTeacher_.teacher)))
    .where(builder.equal(root.get(CourseTeacher_.id), courseId));

    return HibernateHelper.getSingleResult(em, query).intValue();
  }
}
