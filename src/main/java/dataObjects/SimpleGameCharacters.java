package dataObjects;

public class SimpleGameCharacters {
	
	public static SimpleGameCharacter sylvia() {
		
		String characterName = "Sylvia the Hero";
		
		String weaponName = "Gryphclaw";
		int attack = 255;
		Weapon weapon = new Weapon(weaponName, attack);
		
		String armorName = "Starscale Armor";
		int defense = 231;
		Armor armor = new Armor(armorName, defense);
		
		String _id = "sylv01";
		
		SimpleGameCharacter sylvia = new dataObjects.SimpleGameCharacter(characterName, weapon, armor, _id);
		return sylvia;
	}
	
	public static SimpleGameCharacter sylviaEdited() {
		
		String characterName = "Sylvia Zerin";
		
		String weaponName = "Luberjack's Axe";
		int attack = 7;
		Weapon weapon = new Weapon(weaponName, attack);
		
		String armorName = "Crude Mazoiscale Armor";
		int defense = 13;
		Armor armor = new Armor(armorName, defense);
		
		String _id = "sylv01";
		
		SimpleGameCharacter sylvia = new dataObjects.SimpleGameCharacter(characterName, weapon, armor, _id);
		return sylvia;
	}
}
