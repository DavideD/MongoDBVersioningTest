package dataObjects;

import java.util.ArrayList;
import java.util.List;

public class GameCharacters {
	
	public static GameCharacter sylvia() {
		
		String characterName = "Sylvia the Hero";
		
		String weaponName = "Gryphclaw";
		int attack = 255;
		Weapon weapon = new Weapon(weaponName, attack);
		
		String armorName = "Starscale Armor";
		int defense = 231;
		Armor armor = new Armor(armorName, defense);
		
		String _id = "sylv01";

		List<Item> inventory = new ArrayList<Item>();
		inventory.add( new Item("Hi-Potion", 3, "Potion" ) );
		inventory.add( new Item("Mega Ether", 5, "Ether" ) );

		GameCharacter sylvia = new dataObjects.GameCharacter(characterName, weapon, armor, _id, inventory);
		return sylvia;
	}
	
	public static GameCharacter sylviaEdited() {
		
		String characterName = "Sylvia Zerin";
		
		String weaponName = "Luberjack's Axe";
		int attack = 7;
		Weapon weapon = new Weapon(weaponName, attack);
		
		String armorName = "Crude Mazoiscale Armor";
		int defense = 13;
		Armor armor = new Armor(armorName, defense);
		
		String _id = "sylv01";
		
		List<Item> inventory = new ArrayList<Item>();
		inventory.add( new Item("Hi-Potion", 2, "Potion" ) );
		inventory.add( new Item("Mega Ether", 4, "Ether" ) );
		
		GameCharacter sylvia = new dataObjects.GameCharacter(characterName, weapon, armor, _id, inventory);
		return sylvia;
	}
}
