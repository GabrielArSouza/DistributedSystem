package ufrn.sgl.messages.protocol.logout;

import ufrn.sgl.model.CompanySession;

public class RequestCompanyLogout extends RequestLogout{

	private static final long serialVersionUID = -7201561474696139018L;

	private final CompanySession session;
	
	public RequestCompanyLogout(CompanySession session) {
		this.session = session;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public CompanySession getSession() {
		return session;
	}
	
	

}
