package ch.gibm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class EntityManagerHelper {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JSFAppPU");
	private static final ThreadLocal<EntityManager> tLocal = new ThreadLocal<EntityManager>();
	private static final Logger logger = Logger.getLogger(EntityManagerHelper.class);

	public static EntityManager getEntityManager() {
		EntityManager em = tLocal.get();

		if (em == null) {
			em = emf.createEntityManager();
			tLocal.set(em);
		}
		return em;
	}

	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public static void rollback() {
		logger.warn("Rollback ...");
		getEntityManager().getTransaction().rollback();
	}

	public static void commit() {	
		getEntityManager().getTransaction().commit();
	}
	
	public static void commitAndCloseTransaction() {
		commit();
		closeEntityManager();
	}

	public static void closeEntityManager() {
		EntityManager em = tLocal.get();
		if (em != null) {
			em.close();
			tLocal.set(null);
		}
	}

	public static void closeEntityManagerFactory() {
		emf.close();
	}
}
