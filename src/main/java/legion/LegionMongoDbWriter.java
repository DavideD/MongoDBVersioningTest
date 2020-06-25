package legion;


import org.bson.codecs.configuration.CodecRegistry;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.repository.mongo.MongoRepository;

import com.mongodb.MongoClient;
import com.mongodb.client.*;

import dataObjects.legion.Legion;
import exceptions.DatabaseNotConnectedException;
import mongoDB.MongoDB_Connection;
import mongoDB.MongoDB_Operations;

public class LegionMongoDbWriter {
	
	private static final String LEGION_DATABASE_NAME = "legion_database";
	private static final String LEGION_COLLECTION_NAME = "legion_collection";
	
	MongoCollection<Legion> legionCollection;
	public MongoDatabase legionDatabase;
	
	public LegionMongoDbWriter() throws DatabaseNotConnectedException {
		MongoDB_Connection mongoDB_connection = establishConnectionToMongoDB();
		MongoClient mongoDatabaseConnectionPool = mongoDB_connection.mongoDatabaseConnectionPool;
		
		MongoDB_Operations mongoDB_operations = new MongoDB_Operations(mongoDatabaseConnectionPool);
		CodecRegistry pojoCodecRegistry = mongoDB_operations.buildPOJO_codecRegistry();
		legionDatabase = mongoDatabaseConnectionPool.getDatabase(LEGION_DATABASE_NAME);
		legionDatabase = legionDatabase.withCodecRegistry(pojoCodecRegistry);
	
		legionCollection = legionDatabase.getCollection(LEGION_COLLECTION_NAME, Legion.class);
	}

	public void writeLegionToMongoDB(Legion legion) throws DatabaseNotConnectedException {
		legionCollection.insertOne(legion);
	}
	
	public MongoDB_Connection establishConnectionToMongoDB() throws DatabaseNotConnectedException {
		MongoDB_Connection mongoDB_connection = new MongoDB_Connection();
		return mongoDB_connection;
	}
	
	public void writeLegionToJavers(Legion legion) {
		MongoRepository legionRepository = new MongoRepository(legionDatabase);
		Javers javersRepository = JaversBuilder
			.javers()
			.registerJaversRepository(legionRepository)
			.build();
		javersRepository.commit("Test Legion Author", legion);
	}
}
