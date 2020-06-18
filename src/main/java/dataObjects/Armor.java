package dataObjects;

import javax.persistence.Embeddable;

@Embeddable
public class Armor {
	public String name;
	public int defense;
	
	public Armor() {
		
	}
	
	public Armor(String name, int defense) {
		this.name = name;
		this.defense = defense;
	}
}
