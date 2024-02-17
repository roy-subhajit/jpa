package standalone.jpa.demo.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionManager {
	private static EntityManagerFactory entityManagerFactory = null;
	private static EntityManager entityManager = null;

	public ConnectionManager() {
		createConnectionInstance();
	}
	
	public static void createConnectionInstance() {
		if(entityManagerFactory == null)
			entityManagerFactory = Persistence.createEntityManagerFactory("jpa.demo");
	    entityManager = entityManagerFactory.createEntityManager();

	}
	
	public EntityManagerFactory getEntityManagerFactory(){
	    return entityManagerFactory;
	}
	
	public EntityManager getEntityManager(){
	    return entityManager;
	}
}
