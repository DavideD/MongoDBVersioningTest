package dataObjects;

import javax.persistence.*;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
@Entity
@DiscriminatorColumn(name="ITEM_TYPE")
public abstract class Item {
	@Id
	public String name;
	public int amount;
	
	public Item() {
		
	}
	
	public Item(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
}
