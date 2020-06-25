package dataObjects.legion;

import java.util.List;

import org.javers.core.metamodel.annotation.Id;
import org.javers.core.metamodel.annotation.TypeName;

@TypeName("Legion")
public class Legion {
	@Id
	public int _id;
	public List<Company> companies;
	
	public Legion() {
		
	}
	
	public Legion(int legionId, List<Company> companies) {
		this._id = legionId;
		this.companies = companies;
	}
}
