package br.com.devmedia.java.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Lembrete lembrete = new Lembrete("Compra leite", "Hoje, 10h30");

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(lembrete);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
