package javers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.javers.core.*;
import org.javers.core.commit.CommitId;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;

import org.junit.jupiter.api.*;

import dataObjects.GameCharacter;
import dataObjects.GameCharacters;
import jaVers.JaversUtilities;
import jaVers.Queries;

public class JaversMemoryRepositoryTest {
	private static final String AUTHOR_NAME = "Luther Lansfeld";
	private static Javers javersRepository;
	GameCharacter sylvia;
	GameCharacter bokay;
	GameCharacter cyrus;
	
	@BeforeEach
	public void setUpJaversRepository() {
		javersRepository = JaversBuilder.javers().build();
		sylvia = GameCharacters.sylvia();
		javersRepository.commit(AUTHOR_NAME, sylvia);
		sylvia.copyValuesFrom(GameCharacters.sylviaEdited());
		javersRepository.commit(AUTHOR_NAME, sylvia);
		sylvia.name = "Sylvia the Atomic";
		javersRepository.commit(AUTHOR_NAME, sylvia);
		bokay= GameCharacters.bokay();
		javersRepository.commit(AUTHOR_NAME, bokay);
		cyrus= GameCharacters.cyrus();
		javersRepository.commit(AUTHOR_NAME, cyrus);
	}
	
	@Test
	public void threeShadowsOfSylviaShouldExist() {
		List<Shadow<GameCharacter>> gameCharacterShadowList
			= Queries.shadowsQuery(javersRepository, sylvia._id, GameCharacter.class);
		
		assertEquals(3, gameCharacterShadowList.size());
	}
	
	@Test
	public void secondCommitShouldHaveCorrectNameForSylvia() {
		JqlQuery entryQuery = QueryBuilder
			.byInstanceId(sylvia._id, GameCharacter.class)
			.withCommitId(new CommitId(2, 0))
			.build();
		List<Shadow<GameCharacter>> entryShadowList
			= javersRepository.findShadows(entryQuery);
		Shadow<GameCharacter> sylviaTheHeroShadow = entryShadowList.get(0);
		
		assertEquals("Sylvia Zerin", sylviaTheHeroShadow.get().name);
	}
	
	@Test
	public void queryShouldFindLatestVersionOfEachCharacter() {
		JqlQuery entryQuery = QueryBuilder.byClass(GameCharacter.class).build();
		List<Shadow<GameCharacter>> allGameCharacterShadows
			= javersRepository.findShadows(entryQuery);
		List<Shadow<GameCharacter>> latestGameCharacterShadows
			= JaversUtilities.getLatestShadowsFrom(allGameCharacterShadows);
		assertEquals(3, latestGameCharacterShadows.size());
	}
}