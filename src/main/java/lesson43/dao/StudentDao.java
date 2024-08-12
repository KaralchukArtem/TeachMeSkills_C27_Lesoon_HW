package lesson43.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lesson43.model.GrooupModel;
import lesson43.model.StudentModel;
import lesson43.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentDao {

    public List<StudentModel> getGroups(String title) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StudentModel> criteriaQuery = criteriaBuilder.createQuery(StudentModel.class);
        Root<StudentModel> root = criteriaQuery.from(StudentModel.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("grooupModel").get("title"), title));

        List<StudentModel> grooups = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return grooups;
    }

    public List<StudentModel> getAllStudentsDesc() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StudentModel> criteriaQuery = criteriaBuilder.createQuery(StudentModel.class);
        Root<StudentModel> root = criteriaQuery.from(StudentModel.class);
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("recordBookModel").get("rating")));

        List<StudentModel> student = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return student;
    }

    public Map<String, List<StudentModel>> getTopStudents() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<String> queryTitleGroup = cb.createQuery(String.class);
        Root<GrooupModel> groupRoot = queryTitleGroup.from(GrooupModel.class);
        queryTitleGroup.select(groupRoot.get("title")).distinct(true);

        List<String> groupTitle = session.createQuery(queryTitleGroup).getResultList();
        Map<String, List<StudentModel>> topStudentsByGroup = new HashMap<>();

        for (String title : groupTitle) {
            CriteriaQuery<StudentModel> studentModelCriteriaQuery = cb.createQuery(StudentModel.class);
            Root<StudentModel> rootStudent = studentModelCriteriaQuery.from(StudentModel.class);
            studentModelCriteriaQuery.where(cb.equal(rootStudent.get("grooupModel").get("title"), title));
            studentModelCriteriaQuery.orderBy(cb.desc(rootStudent.get("recordBookModel").get("rating")));

            List<StudentModel> topStudent = session.createQuery(studentModelCriteriaQuery)
                    .setMaxResults(3)
                    .getResultList();
            topStudentsByGroup.put(title, topStudent);
        }
        session.close();
        return topStudentsByGroup;
    }

    public Map<String, List<StudentModel>> getAVGStudentsByRating() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<String> queryTitleGroup = cb.createQuery(String.class);
        Root<GrooupModel> groupRoot = queryTitleGroup.from(GrooupModel.class);
        queryTitleGroup.select(groupRoot.get("title")).distinct(true);

        List<String> groupTitle = session.createQuery(queryTitleGroup).getResultList();
        Map<String, List<StudentModel>> topStudentsByGroup = new HashMap<>();

        for (String title : groupTitle) {
            CriteriaQuery<Double> avarageCriteriaQuery = cb.createQuery(Double.class);
            Root<StudentModel> recordBookModelRoot = avarageCriteriaQuery.from(StudentModel.class);
            avarageCriteriaQuery.where(cb.equal(recordBookModelRoot.get("grooupModel").get("title"), title));
            avarageCriteriaQuery.select(cb.avg(recordBookModelRoot.get("recordBookModel").get("rating")));
            Query<Double> doubleQuery = session.createQuery(avarageCriteriaQuery);
            Double avg = doubleQuery.getSingleResult();

            CriteriaQuery<StudentModel> studentModelCriteriaQuery = cb.createQuery(StudentModel.class);
            Root<StudentModel> rootStudent = studentModelCriteriaQuery.from(StudentModel.class);
            studentModelCriteriaQuery.select(rootStudent).where(
                    cb.and(
                            cb.equal(rootStudent.get("grooupModel").get("title"), title),
                            cb.gt(rootStudent.get("recordBookModel").get("rating"), avg)
                    ));
            studentModelCriteriaQuery.orderBy(cb.desc(rootStudent.get("recordBookModel").get("rating")));

            List<StudentModel> topStudent = session.createQuery(studentModelCriteriaQuery)
                    .getResultList();
            topStudentsByGroup.put(title, topStudent);
        }
        session.close();
        return topStudentsByGroup;
    }
}
