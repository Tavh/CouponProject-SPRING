package com.tav.coupons.entitymanagerhelper;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tav.coupons.threads.CouponCleanupRunner;

public class EntityManagerHelper {

	/* EntityManagerFactory is an extremely heavy weight object, it's good practice to 
		avoid making many of them, however, we need a separate one for non-EJB situations */
    private static EntityManagerFactory entityManagerFactory;
    private static ThreadLocal<EntityManager> threadLocal;

    static { 
        entityManagerFactory = Persistence.createEntityManagerFactory("CouponSpringUnit");
        threadLocal = new ThreadLocal<EntityManager>();
    }
    
    public static EntityManager getEntityManager() {
    	
        EntityManager entityManager = threadLocal.get();

        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
            // set your flush mode here
            threadLocal.set(entityManager);
        }
        return entityManager;
    }

    public static void closeEntityManager() {
        EntityManager entityManager = threadLocal.get();
        if (entityManager != null) {
        	entityManager.close();
            threadLocal.set(null);
        }
    }

    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }

    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    public static void commit() {
        getEntityManager().getTransaction().commit();
    }
    

}