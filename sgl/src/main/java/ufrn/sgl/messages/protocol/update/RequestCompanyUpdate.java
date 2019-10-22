package ufrn.sgl.messages.protocol.update;

import ufrn.sgl.model.Company;

public class RequestCompanyUpdate extends RequestUpdate{

	private static final long serialVersionUID = -1214518292422328518L;

	private final Company company;
	
	public RequestCompanyUpdate(Company company) {
		super();
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
