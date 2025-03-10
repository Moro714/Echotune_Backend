package echotune_server.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
 
import java.util.function.Function;

public class JPAService {
	private static JPAService instance;
	private EntityManagerFactory factory;
	
	private JPAService() {
		this.factory = Persistence.createEntityManagerFactory("echotune");
	}
	
	public static synchronized JPAService getInstance() {
		if(JPAService.instance == null) {
			JPAService.instance = new JPAService();
		}
		return JPAService.instance;
	}
	
	// Clean up at end of lifecycle
	public void unset() {
		if(this.factory != null) {
			this.factory.close();
			JPAService.instance = null;
		}
	}
	
	public <T> T runTransaction(Function<EntityManager, T> func) {
		EntityManager manager = this.factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		boolean success = false;
		
		try{
			T ret = func.apply(manager);
			success = true;
			return ret;
		} finally {
			if(success) {
				transaction.commit();
			} else {
				transaction.rollback();
			}
		}
	}
}
