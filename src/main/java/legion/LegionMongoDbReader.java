package legion;

import java.util.*;

import org.bson.codecs.configuration.CodecRegistry;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.repository.mongo.MongoRepository;
import org.javers.shadow.Shadow;

import com.mongodb.MongoClient;
import com.mongodb.client.*;

import dataObjects.legion.Legion;
import jaVers.Queries;
import mongoDB.MongoDB_Operations;

public class LegionMongoDbReader {
	
	private static final int MOST_RECENT_SHADOW_INDEX = 0;
	private static final String LEGION_DATABASE_NAME = "legion_database";
	private static final String LEGION_COLLECTION_NAME = "legion_collection";
	public MongoDatabase legionDatabase;
	
	public LegionMongoDbReader(MongoClient mongoDatabaseConnectionPool) {
		legionDatabase = mongoDatabaseConnectionPool.getDatabase(LEGION_DATABASE_NAME);
		MongoDB_Operations mongoDB_operations = new MongoDB_Operations(mongoDatabaseConnectionPool);
		CodecRegistry pojoCodecRegistry = mongoDB_operations.buildPOJO_codecRegistry();

		legionDatabase = legionDatabase.withCodecRegistry(pojoCodecRegistry);
	}
	
	public Legion readLegionFromMongoDB_byIndex(int legionIndex) {
		MongoCollection<Legion> legionCollection = getLegionCollection();
		FindIterable<Legion> legionIterables = legionCollection.find();
		List<Legion> legionList = new ArrayList<Legion>();
		for(Legion legion : legionIterables) {
			legionList.add(legion);
		}
		return legionList.get(legionIndex);
	}
	
	public Legion readLegionFromMongoDbById(int _id) {
		MongoCollection<Legion> legionCollection = getLegionCollection();
		FindIterable<Legion> legionIterables = legionCollection.find();
		return findByID(legionIterables, _id);
	}

	private Legion findByID(FindIterable<Legion> legionIterables, int _id) {
		for(Legion legion : legionIterables ) {
			if(legion._id == _id) {
				return legion;
			}
		}
		return null;
	}

	private MongoCollection<Legion> getLegionCollection() {
		
		
		MongoCollection<Legion> legionCollection = legionDatabase.getCollection(LEGION_COLLECTION_NAME, Legion.class);
		return legionCollection;
	}
	
	public Legion readLegionFromJaversById(int _id) {
		MongoRepository legionRepository = new MongoRepository(legionDatabase);
		Javers javersRepository = JaversBuilder
			.javers()
			.registerJaversRepository(legionRepository)
			.build();
		List<Shadow<Legion>> legionShadows = Queries.shadowsQuery(javersRepository, _id, Legion.class);
		return legionShadows.get(MOST_RECENT_SHADOW_INDEX).get();
	}

}
