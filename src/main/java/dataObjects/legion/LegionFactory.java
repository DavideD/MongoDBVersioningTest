package dataObjects.legion;

import java.util.*;

public class LegionFactory {
	public static Legion createLegion(
		int legionId,
		int companyCount,
		int legionnairesPerCompany
	) {
		List<Company> companies = new ArrayList<>();
		for(int companyIndex = 0; companyIndex < companyCount; companyIndex++) {
			List<Legionnaire> legionnaires = new ArrayList<>();
			for(
				int legionnaireIndex = 0;
				legionnaireIndex < legionnairesPerCompany;
				legionnaireIndex++
			) {
				Legionnaire legionnaire
					= new Legionnaire(legionId, companyIndex, legionnaireIndex);
				legionnaires.add(legionnaire);
				System.out.println("Added Legionnaire " + legionnaireIndex + " of company " + companyIndex);
			}
			Company company = new Company(legionId, companyIndex, legionnaires);
			companies.add(company);
		}
		return new Legion(legionId, companies);
	}
}
