package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import org.hibernate.annotations.Parent;

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

            List<Object[]> resultList =
                    em.createQuery("SELECT o.member, o.product, o.orderAmount FROM order o").getResultList();

            for (Object[] row : resultList) {
                Member member = (Member) row[0];
            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }



}