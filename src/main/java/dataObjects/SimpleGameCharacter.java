package dataObjects;

import javax.persistence.*;

@Entity
public class SimpleGameCharacter {
	@Id
	public String _id;
	public String name;
	public Weapon weapon;
	public Armor armor;
	
	public SimpleGameCharacter() {
		
	}
	
	public SimpleGameCharacter(String name, Weapon weapon, Armor armor, String _id) {
		this._id = _id;
		this.name = name;
		this.weapon = weapon;
		this.armor = armor;
	}
	
	public String toString() {
		String returnString = "";
		returnString += "ID: " + _id + " ";
		returnString += "Name: " + name+ " ";
		returnString += "Weapon: " + weapon.name
			+ " (" + weapon.attack + " attack) ";
		returnString += "Armor: " + armor.name
				+ " (" + armor.defense + " defense) ";
		return returnString;
	}
	
	public void copyValuesFrom(SimpleGameCharacter otherCharacter) {
		_id = otherCharacter._id;
		name = otherCharacter.name;
		weapon = otherCharacter.weapon;
		armor = otherCharacter.armor;
	}
}
