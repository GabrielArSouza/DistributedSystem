package ufrn.sgl.messages;

import ufrn.sgl.model.Company;

public class CompanyRegistrationMessage extends Message {

	private static final long serialVersionUID = 2L;

	private final Company company;
	
	
	public CompanyRegistrationMessage( Company company ){
		this.company = company;
	}

	public Company getCompany () {
		return this.company;
	}
	
	@Override
	public String getMessage() {
		
		String msg = "{ \"type\": \"Company Registration\",\n"
			+ this.company.toString() + "\n}";
		
		return msg;
	}

	
	
	
	
}
