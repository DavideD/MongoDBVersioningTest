package dataObjects;

import javax.persistence.*;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
public abstract class Item {
	public String name;
	public int amount;
    @Column(name="ITEM_TYPE")
    public String itemType;
	
	public Item() {
		
	}
	
	public Item(String name, int amount, String itemType) {
		this.name = name;
		this.amount = amount;
		this.itemType = itemType;
	}
}
