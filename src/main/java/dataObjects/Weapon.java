package dataObjects;

import org.javers.core.metamodel.annotation.TypeName;

@TypeName("Weapon")
public class Weapon {
	public String name;
	public int attack;
	
	public Weapon() {
		
	}
	
	public Weapon(String name, int attack) {
		this.name = name;
		this.attack = attack;
	}
}
