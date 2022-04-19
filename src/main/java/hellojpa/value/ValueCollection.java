package hellojpa.value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class ValueCollection {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Player player = new Player();
            player.setPlayerName("Player1");
            player.setHomeAddress(new Address("homeCity", "street1", "10000"));

            player.getFavoriteFoods().add("치킨");
            player.getFavoriteFoods().add("족발");
            player.getFavoriteFoods().add("피자");

            player.getAddressHistory().add(new AddressEntity("old1", "oldStreet1", "200"));
            player.getAddressHistory().add(new AddressEntity("old2", "oldStreet2", "300"));

            em.persist(player);

            em.flush();
            em.clear();

            System.out.println("=================== START =================");
            Player findPlayer = em.find(Player.class, player.getId());

            //homeCity -> newCity / 값타입은 통으로 갈아끼워야한다.
//            Address oldAddress = findPlayer.getHomeAddress();
//            findPlayer.setHomeAddress(new Address("newCity", oldAddress.getStreet(), oldAddress.getZipcode()));

            // 값타입 컬렉션 업데이트
            // 치킨 -> 한식
//            findPlayer.getFavoriteFoods().remove("치킨");
//            findPlayer.getFavoriteFoods().add("한식");

//            findPlayer.getAddressHistory().remove(new Address("old1", "oldStreet1", "200"));
//            findPlayer.getAddressHistory().add(new Address("newCity1", "newStreet1", "200"));


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
