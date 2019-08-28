package ufrn.sgl.messages.protocol.read;

import ufrn.sgl.model.Company;

public class RequestCompanyRead extends RequestRead{

	private static final long serialVersionUID = 602235544075181255L;
	
	private final Company company;
	
	public RequestCompanyRead(Company company) {
		super(company.getId());
		this.company = company;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
		
}
