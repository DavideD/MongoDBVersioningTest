package legion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mongodb.MongoClient;

import dataObjects.legion.Company;
import dataObjects.legion.Legion;
import dataObjects.legion.LegionFactory;
import exceptions.DatabaseNotConnectedException;
import mongoDB.MongoDB_Connection;

class LegionMongoDbReaderTest {
	
	private static final int TEST_LEGION_ID = 0;
	private static final int COMPANY_COUNT = 100;
	private static final int LEGIONNAIRES_PER_COMPANY = 100;
	private static LegionMongoDbReader legionReader;
	private static LegionMongoDbWriter legionWriter;
	
	@BeforeAll
	static void setUpLegionReader() throws DatabaseNotConnectedException {
		MongoDB_Connection mongoDB_connection = new MongoDB_Connection();
		MongoClient mongoDatabaseConnectionPool = mongoDB_connection.mongoDatabaseConnectionPool;
		legionReader = new LegionMongoDbReader(mongoDatabaseConnectionPool);
		legionWriter = new LegionMongoDbWriter();
		legionWriter.legionDatabase.drop();
		Legion testLegion = LegionFactory.createLegion(
			TEST_LEGION_ID,
			COMPANY_COUNT,
			LEGIONNAIRES_PER_COMPANY
		);
		legionWriter.writeLegionToMongoDB(testLegion);
		legionWriter.writeLegionToJavers(testLegion);
	}

	@Test
	void readLegionFromMongoDbByIdShouldReturnLegionWithCorrectCompanyCount() {
		Legion testLegion = legionReader.readLegionFromMongoDbById(TEST_LEGION_ID);
		
		assertEquals(COMPANY_COUNT, testLegion.companies.size());
	}
	
	@Test
	void readLegionFromMongoDbByIdShouldReturnLegionWithCorrectNumberOfLegionnaires() {
		Legion testLegion = legionReader.readLegionFromMongoDbById(TEST_LEGION_ID);
		
		int actualLegionnaireCount = 0;
		for(Company company : testLegion.companies) {
			actualLegionnaireCount += company.legionnaires.size();
		}
		
		assertEquals(COMPANY_COUNT * LEGIONNAIRES_PER_COMPANY, actualLegionnaireCount);
	}
	
	@Test
	void readLegionFromJaversByIdShouldReturnLegionWithCorrectCompanyCount() {
		Legion testLegion = legionReader.readLegionFromJaversById(TEST_LEGION_ID);
		
		assertEquals(COMPANY_COUNT, testLegion.companies.size());
	}
	
	@Test
	void readLegionFromJaversByIdShouldReturnLegionWithCorrectNumberOfLegionnaires() {
		Legion testLegion = legionReader.readLegionFromJaversById(TEST_LEGION_ID);
		
		int actualLegionnaireCount = 0;
		for(Company company : testLegion.companies) {
			actualLegionnaireCount += company.legionnaires.size();
		}
		
		assertEquals(COMPANY_COUNT * LEGIONNAIRES_PER_COMPANY, actualLegionnaireCount);
	}
}
