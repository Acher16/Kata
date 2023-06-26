package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
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
