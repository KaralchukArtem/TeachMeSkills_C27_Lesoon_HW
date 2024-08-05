package lesson43.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lesson43.model.GrooupModel;
import lesson43.model.StudentModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        for (StudentModel studentModel : grooups) {
            System.out.println(studentModel.getName());
        }
        //
//        sort();
        sort1();
    }

    private static void sort() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StudentModel> criteriaQuery = criteriaBuilder.createQuery(StudentModel.class);
        Root<StudentModel> root = criteriaQuery.from(StudentModel.class);
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("recordBookModel").get("rating")));

        List<StudentModel> student = session.createQuery(criteriaQuery).getResultList();
        for (StudentModel studentModel : student) {
            System.out.println(studentModel);
        }
    }

    private static void sort1()
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<String> queryTitleGroup = cb.createQuery(String.class);
        Root<GrooupModel> groupRoot = queryTitleGroup.from(GrooupModel.class);
        queryTitleGroup.select(groupRoot.get("title")).distinct(true);

        List<String> groupTitle = session.createQuery(queryTitleGroup).getResultList();
        Map<String, List<StudentModel>> topStudentsByGroup = new HashMap<>();

        for (String title : groupTitle)
        {
            CriteriaQuery<StudentModel> studentModelCriteriaQuery = cb.createQuery(StudentModel.class);
            Root<StudentModel> rootStudent = studentModelCriteriaQuery.from(StudentModel.class);
            studentModelCriteriaQuery.where(cb.equal(rootStudent.get("grooupModel").get("title"), title));
            studentModelCriteriaQuery.orderBy(cb.desc(rootStudent.get("recordBookModel").get("rating")));

            List<StudentModel> topStudent = session.createQuery(studentModelCriteriaQuery)
                    .setMaxResults(3)
                    .getResultList();
            topStudentsByGroup.put(title, topStudent);
        }

        for (Map.Entry<String, List<StudentModel>> entry : topStudentsByGroup.entrySet()) {
            System.out.println(entry);
        }
    }

    private static void sort2(){

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
