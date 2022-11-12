package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }


    private static void updateRelation(EntityManager em) {

        // 새로운 팀2
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        // 회원 1에 새로운 팀 2 설정
        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);

    }


    private static void deleteRelation(EntityManager em) {

        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);

    }





}