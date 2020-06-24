package dataObjects;

import javax.persistence.*;


@Embeddable
public class Ether extends Item {
	
	public Ether() {
	}
	
	public Ether(String name, int amount) {
		super(name, amount, "ETHER");
	}
}
