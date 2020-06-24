package dataObjects;

import javax.persistence.*;


@Embeddable
public class Potion extends Item {
	
	public Potion() {
	} 
	
	public Potion(String name, int amount) {
		super(name, amount, "POTION");
	}
}
