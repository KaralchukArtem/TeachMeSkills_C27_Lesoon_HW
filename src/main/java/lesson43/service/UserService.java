package lesson43.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lesson43.model.GrooupModel;
import lesson43.model.StudentModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public static void main(String[] args) {
        // get
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StudentModel> criteriaQuery = criteriaBuilder.createQuery(StudentModel.class);
        Root<StudentModel> root = criteriaQuery.from(StudentModel.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("grooupModel").get("title"), "tm-26"));

        List<StudentModel> grooups = session.createQuery(criteriaQuery).getResultList();
        for (StudentModel studentModel: grooups){
            System.out.println(studentModel.getName());
        }
        //

    }
//    public void deleteUser(int id) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        UserModel userModel = session.getReference(UserModel.class, id);
//        Transaction t = session.beginTransaction();
//        session.remove(userModel);
//        t.commit();
//    }
//
//    public UserModel getUser(int id) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        return session.getReference(UserModel.class, id);
//    }
//
//    public UserModel createUser(UserModel userModel) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction t = session.beginTransaction();
//        session.persist(userModel);
//        t.commit();
//        return userModel;
//    }
//
//    public void changeLogin(String name, int id) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        UserModel userModel = session.getReference(UserModel.class, id);
//        Transaction t = session.beginTransaction();
//        userModel.setName(name);
//        t.commit();
//    }
}
