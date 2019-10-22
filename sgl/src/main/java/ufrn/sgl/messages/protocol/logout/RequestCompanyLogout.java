package ufrn.sgl.messages.protocol.logout;

import ufrn.sgl.model.Company;

public class RequestCompanyLogout extends RequestLogout{

	private static final long serialVersionUID = -7201561474696139018L;

	private String token;
	private Company company;
	
	public RequestCompanyLogout(String token) {
		this.token = token;
		this.company = new Company();
	}
	
	public RequestCompanyLogout(Company company) {
		this.company = company;
	}
	
	public Company getCompany () {return this.company;}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSession() {
		return token;
	}
	
	

}
