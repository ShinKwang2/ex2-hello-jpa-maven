package hellojpa.proxy;

import hellojpa.Member;
import hellojpa.Team;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

public class proxyMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(teamB);
            em.persist(member2);


            em.flush();
            em.clear();

            //프록시 관련
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass());  //Proxy 클래스 확인방법
//            refMember.getUsername(); // 호출을 통한 강제 초기화
//            Hibernate.initialize(refMember);    //강제 초기화
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
//            System.out.println("refMember = " + refMember.getUsername());
//            Member findMember = em.find(Member.class, member1.getId());
//            System.out.println("findMember = " + findMember.getClass()); //Member
//            System.out.println("refMember == findMember : " + (refMember == findMember));
//            Member m2 = em.getReference(Member.class, member2.getId());
//            falseLogic(refMember, m2);
//            rightLogic(refMember, m2)

//            //지연 로딩 Lazy를 이용해 프록시로 조회
//            Member m = em.find(Member.class, member1.getId());
//            System.out.println("m = " + m.getTeam().getClass());
//            System.out.println("===============");
//            System.out.println("teamName = " + m.getTeam().getName()); // 프록시 초기화
//            System.out.println("===============");

            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();
            //SQL: select * from Member
            //SQL: select * from Team where TEAM_ID = xxx


            //LazyInitializationException: could not initialize proxy
//            em.detach(refMember);
//            em.close();
//            em.clear();



            //
//            Member findMember = em.find(Member.class, member.getId());
//            Member findMember = em.getReference(Member.class, member1.getId());
//            System.out.println("findMember = " + findMember.getClass()); //Proxy Class
//
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.username() = " + findMember.getUsername());
//            System.out.println("findMember.username() = " + findMember.getUsername());



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void falseLogic(Member m1, Member m2) {
        System.out.println("m1 == m2: " + (m1.getClass() == m2.getClass()));
    }

    private static void rightLogic(Member m1, Member m2) {
        System.out.println("m1 instacneof Member: " + (m1 instanceof Member));
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }
}
