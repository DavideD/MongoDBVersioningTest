package dataObjects;

import org.javers.core.metamodel.annotation.TypeName;

@TypeName("Potion")
public class Potion extends Item {
	
	public Potion() {
	}
	
	public Potion(String name, int amount) {
		super(name, amount);
	}
}
