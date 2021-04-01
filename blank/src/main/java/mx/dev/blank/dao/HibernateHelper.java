package mx.dev.blank.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaQuery;

public class HibernateHelper {

  public static <T> T getSingleResult(final EntityManager em, final CriteriaQuery<T> criteriaQuery) {
    try {
      return em.createQuery(criteriaQuery).getSingleResult();
    } catch (final NonUniqueResultException | NoResultException e) {
      return null;
    }
  }

  public static <T> List<T> getResults(final EntityManager em, final CriteriaQuery<T> criteriaQuery) {
    return em.createQuery(criteriaQuery).getResultList();
  }
}
