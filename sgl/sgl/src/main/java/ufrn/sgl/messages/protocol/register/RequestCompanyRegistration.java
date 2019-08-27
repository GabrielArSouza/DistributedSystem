package ufrn.sgl.messages.protocol.register;

import ufrn.sgl.model.Company;

public class RequestCompanyRegistration extends RequestRegistration {

	private static final long serialVersionUID = 5812199282541282155L;

	private final Company company;
	
	public RequestCompanyRegistration( Company company )  {
		this.company = company;
	}
	
	@Override
	public String getMessage() {
		return "create a new company";
	}

	public Company getCompany() {
		return company;
	}
	
	
	
	

}
