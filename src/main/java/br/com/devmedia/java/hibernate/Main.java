package br.com.devmedia.java.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Lembrete lembrete = new Lembrete("Compra leite", "Hoje, 10h30");
//        Lembrete lembrete1 = new Lembrete("Compra pão", "Amanhã, 11h30");
//        Lembrete lembrete2= new Lembrete("Busca filho ma escola", "Hoje, 17h30");

        try {
            entityManager.getTransaction().begin();
//            System.out.println(entityManager.find(Lembrete.class, 1L));
            List<Lembrete> lembretes = entityManager.createQuery("SELECT m FROM Lembrete m WHERE titulo LIKE '%compra%'", Lembrete.class).getResultList();
            if (lembretes != null) {
                lembretes.forEach(System.out::println);
            }
//            entityManager.persist(lembrete);
//            entityManager.persist(lembrete1);
//            entityManager.persist(lembrete2);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
