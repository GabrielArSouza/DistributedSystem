package ufrn.sgl.client.udp;

import java.net.SocketException;
import java.net.UnknownHostException;

import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.logout.RequestCompanyLogout;
import ufrn.sgl.messages.protocol.logout.RequestUserLogout;
import ufrn.sgl.messages.protocol.logout.SuccessfullyLogout;
import ufrn.sgl.messages.protocol.register.RegistrationSuccessfully;
import ufrn.sgl.messages.protocol.register.RequestUserRegistration;
import ufrn.sgl.messages.protocol.session.RequestCompanySession;
import ufrn.sgl.messages.protocol.session.RequestUserSession;
import ufrn.sgl.messages.protocol.session.SuccessfullySession;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.User;

public class UDPClient {
	
	private final UDPProtocolClient protocol;
	private final String successMessage =  "operation successfully performed";
	
	public UDPClient() throws SocketException, UnknownHostException {
		this.protocol = new UDPProtocolClient();
	}
	
	public void createUser ( User user ) {
		Message response = protocol.requestOperation(new RequestUserRegistration(user));
		if (response.getClass().equals( RegistrationSuccessfully.class ))
			System.out.println(successMessage);
		else System.out.println(response.getMessage());
	}
	
	public String userLogin ( User user ) {
		Message response = protocol.requestOperation(new RequestUserSession(user));
		if (response.getClass().equals( SuccessfullySession.class )) {
			SuccessfullySession session = (SuccessfullySession) response;
			System.out.println("Successfully Session - Token: " + session.getMessage());
			return session.getMessage();
		} else return response.getMessage();
	}
	
	public void userLogout ( String token ) {
		Message response = protocol.requestOperation(new RequestUserLogout(token));
		if (response.getClass().equals( SuccessfullyLogout.class ))
			System.out.println("Logout Successfully");
		else System.out.println(response.getMessage());
	}
	
	public String companyLogin ( Company company ) {
		Message response = protocol.requestOperation(new RequestCompanySession(company));
		if (response.getClass().equals( SuccessfullySession.class )) {
			SuccessfullySession session = (SuccessfullySession) response;
			System.out.println("Successfully Session - Token: " + session.getMessage());
			return session.getMessage();
		} else return response.getMessage();
	}
	
	public void companyLogout ( String token ) {
		Message response = protocol.requestOperation(new RequestCompanyLogout(token));
		if (response.getClass().equals( SuccessfullyLogout.class ))
			System.out.println("Logout Successfully");
		else System.out.println(response.getMessage());
	}
	
	
}
