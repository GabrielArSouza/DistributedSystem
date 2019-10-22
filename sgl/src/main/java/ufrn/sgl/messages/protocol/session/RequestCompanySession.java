package ufrn.sgl.messages.protocol.session;

import ufrn.sgl.model.Company;

public class RequestCompanySession extends RequestSession{

	private static final long serialVersionUID = 3352819420784329308L;
	private final Company company;
	
	public RequestCompanySession(Company company) {
		super();
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
