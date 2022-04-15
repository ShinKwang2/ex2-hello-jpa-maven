package hellojpa.value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ValueMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Address address = new Address("city", "street", "10000");

            Player playerA = new Player();
            playerA.setPlayerName("PlayerA");
            playerA.setHomeAddress(address);
            em.persist(playerA);

            Address copyAddress = new Address((address.getCity()), address.getStreet(), address.getZipcode());

            Player playerB = new Player();
            playerB.setPlayerName("PlayerB");
            playerB.setHomeAddress(copyAddress);
            em.persist(playerB);

            //set 을 없애면 값을 바꾸고 싶을 땐 어떻게 해야할까? - 통으로 바꾸는 것이 사이드이펙트 제어에 유용
            Address newAddress = new Address("NewCity", address.getStreet(), address.getZipcode());
            playerA.setHomeAddress(newAddress);

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
