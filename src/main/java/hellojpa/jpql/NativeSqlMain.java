package hellojpa.jpql;

import hellojpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class NativeSqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //NativeSql
        try {

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            //flush -> commit, query

            List<Member> resultList = em.createNativeQuery("select MEMBER_ID, TEAM_ID, USERNAME from MEMBER", Member.class)
                    .getResultList();

//            String sql = "select MEMBER_ID, TEAM_ID, USERNAME from MEMBER where USERNAME = 'kim'";
//            List<Member> resultList = em.createNativeQuery(sql, Member.class).getResultList();

            for (Member member : resultList) {
                System.out.println("member = " + member);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
