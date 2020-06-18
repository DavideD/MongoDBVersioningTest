package hibernate;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;

import org.junit.jupiter.api.*;

import dataObjects.GameCharacter;
import dataObjects.GameCharacters;

class OgmAccessorTest {
	
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
		entityManager.createNativeQuery("db.GameCharacter.drop()").executeUpdate();
		transactionManager.commit();
	}
	
	@Test
	void writeShouldBeAbleToWriteRetreivableGameCharacterToMongoDB() throws SecurityException, IllegalStateException, NotSupportedException, SystemException, HeuristicMixedException, HeuristicRollbackException, RollbackException {
		
		GameCharacter sylvia = GameCharacters.sylvia();
		
		OgmAccessor.write(sylvia, entityManagerFactory);
		
		transactionManager.begin();
		GameCharacter loadedGameCharacter
			= entityManager.find(GameCharacter.class, sylvia._id);
		transactionManager.commit();
		
		assertNotNull(loadedGameCharacter);
	}
	
	@Test
	void writeAndReadShouldSaveInventoryCorrectly() throws SecurityException, IllegalStateException, NotSupportedException, SystemException, HeuristicMixedException, HeuristicRollbackException, RollbackException {
		
		GameCharacter sylvia = GameCharacters.sylvia();
		
		OgmAccessor.write(sylvia, entityManagerFactory);
		
		transactionManager.begin();
		GameCharacter loadedGameCharacter
			= entityManager.find(GameCharacter.class, sylvia._id);
		transactionManager.commit();
		
		int expectedInventorySize = sylvia.inventory.size();
		int actualInventorySize = loadedGameCharacter.inventory.size();
		assertEquals(expectedInventorySize, actualInventorySize);
	}
}
