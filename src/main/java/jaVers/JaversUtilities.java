package jaVers;

import java.util.ArrayList;
import java.util.List;

import org.javers.shadow.Shadow;

import dataObjects.GameCharacter;

public class JaversUtilities {
	
	public static List<Shadow<GameCharacter>> getLatestShadowsFrom(
		List<Shadow<GameCharacter>> gameCharacterShadowList
	) {
		List<Shadow<GameCharacter>> latestGameCharacterShadows
			= new ArrayList<>();
		List<String> registeredGameCharacterShadowIds
			= new ArrayList<>(); 
		for(Shadow<GameCharacter> gameCharacterShadow : gameCharacterShadowList) {
			String gameCharacterId
				= gameCharacterShadow.get()._id;
			if(!registeredGameCharacterShadowIds.contains(gameCharacterId)) {
				registeredGameCharacterShadowIds.add(gameCharacterId);
				latestGameCharacterShadows.add(gameCharacterShadow);
			}
		}
		return latestGameCharacterShadows;
	}
}
