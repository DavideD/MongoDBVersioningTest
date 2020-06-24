package hibernate;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;

import org.junit.jupiter.api.*;

import dataObjects.SimpleGameCharacter;
import dataObjects.SimpleGameCharacters;

class OgmAccessorSimpleTest {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static TransactionManager transactionManager;
	
	@BeforeAll
	static void initialize() {
		entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongodb");
		entityManager = entityManagerFactory.createEntityManager();
		transactionManager = com.arjuna.ats.jta.TransactionManager.transactionManager();
	}
	
	@BeforeEach
	void clearDatabase() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		transactionManager.begin();
		entityManager.createNativeQuery("db.SimpleGameCharacter.drop()").executeUpdate();
		transactionManager.commit();
	}
	
	@Test
	void writeShouldBeAbleToWriteRetreivableGameCharacterToMongoDB() throws SecurityException, IllegalStateException, NotSupportedException, SystemException, HeuristicMixedException, HeuristicRollbackException, RollbackException {
		
		SimpleGameCharacter sylvia = SimpleGameCharacters.sylvia();
		
		OgmAccessor.write(sylvia, entityManagerFactory);
		
		transactionManager.begin();
		SimpleGameCharacter loadedGameCharacter
			= entityManager.find(SimpleGameCharacter.class, sylvia._id);
		transactionManager.commit();
		
		assertNotNull(loadedGameCharacter);
	}
	
	@Test
	void writeAndReadShouldSaveNameCorrectly() throws SecurityException, IllegalStateException, NotSupportedException, SystemException, HeuristicMixedException, HeuristicRollbackException, RollbackException {
		
		SimpleGameCharacter sylvia = SimpleGameCharacters.sylvia();
		
		OgmAccessor.write(sylvia, entityManagerFactory);
		
		transactionManager.begin();
		SimpleGameCharacter loadedGameCharacter
			= entityManager.find(SimpleGameCharacter.class, sylvia._id);
		transactionManager.commit();
		
		String expectedName = "Sylvia the Hero";
		String actualName = loadedGameCharacter.name;
		assertEquals(expectedName, actualName);
	}
}
