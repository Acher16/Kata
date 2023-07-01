package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getListUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserOwnsCar(String model, int series) {
      List<Car> list = sessionFactory.getCurrentSession().createQuery("from Car where model = :model and series = :series")
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();
      return list.get(0).getUser();
   }

   @Override
   public void deleteAllUsers() {
      sessionFactory.getCurrentSession().createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
      sessionFactory.getCurrentSession().createSQLQuery("DROP TABLE IF EXISTS cars").executeUpdate();
   }
}
