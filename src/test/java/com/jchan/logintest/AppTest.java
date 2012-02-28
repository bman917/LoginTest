package com.jchan.logintest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Test using Eclipse link as the persistence provider
     */
    public void testEclipseLink() {
        basicTest("Eclipselink");
    }

    /**
     * Test using Hibernate as the persistence provider
     */
    public void testHibernate() {
        basicTest("Hibernate");
    }

    private void basicTest(String persistenceUnit) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
        EntityManager em = emf.createEntityManager();

        Credential c = new Credential();
        c.setUserName("bman917");
        c.setPassword("password");

        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();

        CriteriaQuery<Credential> cq = em.getCriteriaBuilder().createQuery(Credential.class);
        Root<Credential> root = cq.from(Credential.class);
        List<Credential> list = em.createQuery(cq).getResultList();

        System.out.println("Number of credentials: " + list.size() + ": " + list);

        Credential c2 = new Credential();
        c2.setUserName("bman917");
        c2.setPassword("password");

        try {
            em.getTransaction().begin();
            em.persist(c2);
            em.getTransaction().commit();

            fail("When a column is annotated with @Column(unique=true), it should disallow duplicate entries.");

        } catch (RollbackException e) {

            if (e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("This is an expected Exception: " + e.getCause().getCause());
            } else {
                throw e;
            }
        }
    }
}
