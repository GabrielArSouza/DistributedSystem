package ufrn.sgl.messages.protocol.read;

import ufrn.sgl.model.Company;

public class ReadCompanySuccessfully extends ReadSuccessfully {

	private static final long serialVersionUID = -4077898470538068547L;

	private final Company company;
	
	public ReadCompanySuccessfully(Company company) {
		this.company = company;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Company getCompany() {
		return company;
	}
	
	

}
