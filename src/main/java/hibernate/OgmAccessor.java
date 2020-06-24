package hibernate;

import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;

import dataObjects.GameCharacter;
import dataObjects.SimpleGameCharacter;

public class OgmAccessor {
	public static void write(
		GameCharacter gameCharacter,
		EntityManagerFactory entityManagerFactory
	) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		TransactionManager transactionManager
			= com.arjuna.ats.jta.TransactionManager.transactionManager();
		transactionManager.begin();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.persist(gameCharacter);
		entityManager.close();
		transactionManager.commit();
	}
	
	public static void write(
		SimpleGameCharacter simpleGameCharacter,
		EntityManagerFactory entityManagerFactory
	) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		TransactionManager transactionManager
			= com.arjuna.ats.jta.TransactionManager.transactionManager();
		transactionManager.begin();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.persist(simpleGameCharacter);
		entityManager.close();
		transactionManager.commit();
	}
}
