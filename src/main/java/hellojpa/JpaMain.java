package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {



//            //일대다 단방향
//            Member member = new Member();
//            member.setUsername("member1");
//            em.persist(member);
//            Team team = new Team();
//            team.setName("teamA");
//            // 아래부분 때문에 update Member 쿼리가 나간다.
//            team.getMembers().add(member);
//            em.persist(team);

//            //양방향 미팽시 가장 많이 하는 실수 - 연관관계의 주인에 값을 입력하지 않음
//            //저장
//            Team team = new Team();
//            team.setName("TeamA");
////            team.getMembers().add(member);
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            em.persist(member);
//
//            team.addMember(member);
//
////            team.getMembers().add(member); //연관관계 편의메소드를 생성하면 생략해도 됨
//
//            em.flush();
//            em.clear();
//
//            Team findTeam = em.find(Team.class, team.getId());
//            List<Member> members = findTeam.getMembers();
//
//            System.out.println("=============");
//            for (Member m : members) {
//                System.out.println("m = " + m.getUsername());
//            }
//            System.out.println("=============");

//            //조회 - 연관관계가 없음
//            Member findMember = em.find(Member.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

//            //조회 - 연관관계 저장
//            Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam = " + findTeam.getName());

//            //양방향 연관관계 설정
//            Member findMember = em.find(Member.class, member.getId());
//            List<Member> members = findMember.getTeam().getMembers();
//
//            for (Member m : members) {
//                System.out.println("m = " + m.getUsername());
//            }

//            //찾은 회원에 새로운 팀 설정
//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam);



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
