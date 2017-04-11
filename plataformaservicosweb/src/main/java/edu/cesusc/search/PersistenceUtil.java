package edu.cesusc.search;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

	 private static EntityManagerFactory emFactory;

	    private PersistenceUtil() {
	    }

	    public static EntityManagerFactory getSessionFactory() {
	        if (emFactory == null) {
	            try {
	            	emFactory = Persistence.createEntityManagerFactory("intrascSite");
	            } catch (Throwable ex) {
	                System.err.println("Criação EntityManagerFactory falhou." + ex);
	                throw new ExceptionInInitializerError(ex);
	            }
	            return emFactory;
	        } else {
	            return emFactory;
	        }
	    }
}
