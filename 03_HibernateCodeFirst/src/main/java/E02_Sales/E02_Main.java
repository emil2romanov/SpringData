package E02_Sales;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class E02_Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Code-first");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();



        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
