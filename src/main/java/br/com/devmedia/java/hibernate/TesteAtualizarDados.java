package br.com.devmedia.java.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteAtualizarDados {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Lembrete lembrete = entityManager.find(Lembrete.class, 1L);
        lembrete.setTitulo("Comprar Biscoitos");
        lembrete.setDescricao("Hoje, 18:00");

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(lembrete);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
