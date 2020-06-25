package dataObjects.legion;

import java.util.List;

import org.javers.core.metamodel.annotation.TypeName;

@TypeName("Company")
public class Company {
	public int legionId;
	public int companyId;
	public List<Legionnaire> legionnaires;
	
	public Company() {
		
	}
	
	public Company(int legionId, int companyId, List<Legionnaire> legionnaires) {
		this.legionId = legionId;
		this.companyId = companyId;
		this.legionnaires = legionnaires;
	}
}
