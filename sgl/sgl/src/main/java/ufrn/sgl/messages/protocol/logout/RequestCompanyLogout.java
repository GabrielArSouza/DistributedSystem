package ufrn.sgl.messages.protocol.logout;

import ufrn.sgl.messages.Message;
import ufrn.sgl.model.Company;

public class RequestCompanyLogout extends Message{

	private static final long serialVersionUID = -5141226179223439120L;

	private final Company company;
	private final String token;
	
	public RequestCompanyLogout(Company company, String token) {
		super();
		this.company = company;
		this.token = token;
	}	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Company getCompany() {
		return company;
	}

	public String getToken() {
		return token;
	}
	
	

}
