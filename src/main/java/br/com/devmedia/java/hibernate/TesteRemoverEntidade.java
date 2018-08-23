package br.com.devmedia.java.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteRemoverEntidade {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Lembrete lembrete = new Lembrete(1L);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(lembrete);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}