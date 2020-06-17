package dataObjects;

import org.javers.core.metamodel.annotation.TypeName;

@TypeName("Armor")
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
