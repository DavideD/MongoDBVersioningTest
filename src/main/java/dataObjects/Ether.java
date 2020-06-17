package dataObjects;

import org.javers.core.metamodel.annotation.TypeName;

@TypeName("Ether")
public class Ether extends Item {
	
	public Ether() {
	}
	
	public Ether(String name, int amount) {
		super(name, amount);
	}
}
