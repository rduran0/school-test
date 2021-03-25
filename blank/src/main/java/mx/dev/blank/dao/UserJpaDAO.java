package mx.dev.blank.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.User;
import mx.dev.blank.entity.User_;

@RequiredArgsConstructor
public class UserJpaDAO implements UserDAO {

  @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
  private EntityManager em;

  /**
   * SELECT * FROM user WHERE id = '${id}'
   */
  @Override
  public User getById(final String id) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<User> query = builder.createQuery(User.class);
    final Root<User> root = query.from(User.class);

    final Path<String> path = root.get(User_.id);
    final Predicate predicate = builder.equal(path, id);

    query.select(root).where(predicate);

    final TypedQuery<User> typedQuery = em.createQuery(query);
    return typedQuery.getSingleResult();
  }

  /**
   * SELECT * FROM user WHERE name = '${name}'
   */
  @Override
  public User getByName(final String name) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<User> query = builder.createQuery(User.class);
    final Root<User> root = query.from(User.class);

    query.select(root).where(builder.equal(root.get(User_.name), name));

    return em.createQuery(query).getSingleResult();
  }

  /**
   * SELECT * FROM user LIMIT ${currentPos} ${size}
   */
  @Override
  public List<User> getPaginated(final int currentPos, final int size) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<User> query = builder.createQuery(User.class);
    final Root<User> root = query.from(User.class);

    query.select(root);

    return em.createQuery(query).setFirstResult(currentPos).setMaxResults(size).getResultList();
  }

  /**
   * SELECT * FROM user WHERE name LIKE '%${coinciden}%'
   */
  @Override
  public List<User> getByNameCoincidence(final String coincidence) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<User> query = builder.createQuery(User.class);
    final Root<User> root = query.from(User.class);

    query.select(root).where(builder.like(root.get(User_.name), "%" + coincidence + "%"));

    return HibernateHelper.getResults(em, query);
  }

  /**
   * SELECT name FROM user ORDER BY name ASC
   */
  @Override
  public List<String> getSortedNames() {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<String> query = builder.createQuery(String.class);
    final Root<User> root = query.from(User.class);

    final Path<String> namePath = root.get(User_.name);

    query.select(namePath).orderBy(builder.asc(namePath));

    return HibernateHelper.getResults(em, query);
  }

  /**
   * UPDATE user SET name=${name} WHERE id=${id}
   */
  @Override
  public void update(final User user) {
    em.merge(user);
  }

  @Override
  public void updateNameById(final String name, final String id) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaUpdate<User> update = builder.createCriteriaUpdate(User.class);
    final Root<User> root = update.from(User.class);

    update.set(root.get(User_.name), name);
    update.where(builder.equal(root.get(User_.id), id));

    em.createQuery(update).executeUpdate();
  }
}
