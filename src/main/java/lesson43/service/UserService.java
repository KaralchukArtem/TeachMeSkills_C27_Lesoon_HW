package lesson43.service;

import lesson43.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void deleteUser(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        UserModel userModel = session.getReference(UserModel.class, id);
        Transaction t = session.beginTransaction();
        session.remove(userModel);
        t.commit();
    }

    public UserModel getUser(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session.getReference(UserModel.class, id);
    }

    public UserModel createUser(UserModel userModel) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.persist(userModel);
        t.commit();
        return userModel;
    }

    public void changeLogin(String name, int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        UserModel userModel = session.getReference(UserModel.class, id);
        Transaction t = session.beginTransaction();
        userModel.setName(name);
        t.commit();
    }
}
