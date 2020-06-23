package jaVers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.javers.core.*;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.type.JaversType;
import org.javers.repository.mongo.MongoRepository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import dataObjects.GameCharacter;
import dataObjects.GameCharacters;
import exceptions.DatabaseNotConnectedException;
import mongoDB.MongoDB_Connection;

public class Experimentation {
	
	private static final String CHARACTER_DATABASE_NAME = "characters";
	private static final String FIRST_AUTHOR_NAME = "Eddie";
	private static final String SECOND_AUTHOR_NAME = "Liete";
	private static final Log LOGGER = LogFactory.getLog(Experimentation.class);
	
	private static Javers javersRepository;
	
	public static void main(String[] args) throws DatabaseNotConnectedException {
		//Call any of the below experimentation methods here to see how they work
		auditExperimentation();
	}
	
	@SuppressWarnings("unused")
	private static void typeExperimentation() {
		javersRepository = JaversBuilder.javers().build();
		JaversType gameCharacterTypeMapping
			= javersRepository.getTypeMapping(GameCharacter.class);
		LOGGER.info(
			"Type Mapping of GameCharacter:" + gameCharacterTypeMapping.prettyPrint()
		);
	}
	
	@SuppressWarnings("unused")
	private static void auditExperimentation() throws DatabaseNotConnectedException {
		
		MongoDB_Connection mongoDB_connection = new MongoDB_Connection();
		MongoClient mongoDatabaseConnectionPool
			= mongoDB_connection.mongoDatabaseConnectionPool;
		MongoDatabase characterDatabase
			= mongoDatabaseConnectionPool.getDatabase(CHARACTER_DATABASE_NAME);
		MongoRepository charactersRepository = new MongoRepository(characterDatabase);
		javersRepository = JaversBuilder
			.javers()
			.registerJaversRepository(charactersRepository)
			.build();
		
		GameCharacter sylvia = GameCharacters.sylvia();
		javersRepository.commit(FIRST_AUTHOR_NAME, sylvia);
		LOGGER.info("Committed Sylvia data");
		
		sylvia.copyValuesFrom(GameCharacters.sylviaEdited());
		javersRepository.commit(SECOND_AUTHOR_NAME, sylvia);
		LOGGER.info("Committed edited Sylvia data");
		
		Queries.shadowsQuery(javersRepository, sylvia._id, sylvia.getClass());
		Queries.snapshotsQuery(javersRepository, sylvia._id, sylvia.getClass());
		Queries.changesQuery(javersRepository, sylvia._id, sylvia.getClass());
	}
	
	@SuppressWarnings("unused")
	private static void diffExperimentation() {
		GameCharacter sylvia = GameCharacters.sylvia();
		GameCharacter sylviaEdited = GameCharacters.sylviaEdited();
		
		javersRepository = JaversBuilder.javers().build();
		Diff sylviaDelta = javersRepository.compare(sylvia, sylviaEdited);
		LOGGER.info(sylviaDelta);
	}
	
}
