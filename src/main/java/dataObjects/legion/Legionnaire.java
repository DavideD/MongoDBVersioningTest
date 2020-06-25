package dataObjects.legion;

import org.javers.core.metamodel.annotation.TypeName;

@TypeName("Legionnaire")
public class Legionnaire {
	public int legionnaireId;
	public int legionId;
	public int companyId;
	
	public Legionnaire() {
		
	}
	
	public Legionnaire(int legionId, int companyId, int legionnaireId) {
		this.legionId = legionId;
		this.companyId = companyId;
		this.legionnaireId = legionnaireId;
	}
}
