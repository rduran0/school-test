package mx.dev.blank.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Course;
import mx.dev.blank.entity.Course_;

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
  public void delete(@NotNull Course course) {
    em.remove(course);
  }

  /**
   * DELETE FROM course WHERE keycode LIKE '${prefix}%'
   */
  @Override
  public void deleteByKeycodePrefix(@NotBlank String prefix) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaDelete<Course> delete = builder.createCriteriaDelete(Course.class);
    final Root<Course> root = delete.from(Course.class);

    delete.where(builder.like(root.get(Course_.keycode), prefix + "%"));

    em.createQuery(delete).executeUpdate();
  }
}
