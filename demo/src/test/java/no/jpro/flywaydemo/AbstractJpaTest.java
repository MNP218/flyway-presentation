package no.jpro.flywaydemo;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AbstractJpaTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private EntityManagerFactory entityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("no.jpro.flywaydemo");
        }
        return entityManagerFactory;
    }

    protected EntityManager entityManager() {
        if (entityManager == null) {
            entityManager = entityManagerFactory().createEntityManager();
        }
        return entityManager;
    }

    @Before
    public void startTransaction() {
        entityManager().getTransaction().begin();
    }

    @After
    public void rollbackTransaction() {
        entityManager().getTransaction().rollback();
        entityManager().close();
        entityManager = null;
    }
}
