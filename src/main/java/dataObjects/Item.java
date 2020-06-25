package dataObjects;

import javax.persistence.*;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
@Embeddable
public class Item {
	public String type;
	public String name;
	public int amount;

	public Item() {
	}

	public Item(String name, int amount, String type) {
		this.name = name;
		this.amount = amount;
		this.type = type;
	}
}
