package ufrn.sgl.server;

import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.connection.ConfirmConnection;
import ufrn.sgl.messages.protocol.register.RegistrationFailed;
import ufrn.sgl.messages.protocol.register.RegistrationSuccessfully;
import ufrn.sgl.messages.protocol.register.RequestRegistration;
import ufrn.sgl.messages.protocol.register.RequestUserRegistration;
import ufrn.sgl.messages.protocol.session.FailLogin;
import ufrn.sgl.messages.protocol.session.RequestSession;
import ufrn.sgl.messages.protocol.session.SuccessfullyLogin;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.User;
import ufrn.sgl.service.BiddingService;
import ufrn.sgl.service.CompanyService;
import ufrn.sgl.service.UserService;
import ufrn.sgl.service.interfaces.BiddingServiceInterface;
import ufrn.sgl.service.interfaces.CompanyServiceInterface;
import ufrn.sgl.service.interfaces.UserServiceInterface;
import ufrn.sgl.util.TokenGenerator;

public class UDPProtocolServer {

	private static final UserServiceInterface userService = new UserService();
	private static final BiddingServiceInterface biddingService = new BiddingService();
	private static final CompanyServiceInterface companyService = new CompanyService();
	
	public static Message connection () {
		return new ConfirmConnection();
	}
	
	public static Message register (Message msg) {
		
		if (msg.getClass().equals(RequestUserRegistration.class)) {
			RequestUserRegistration msgUser = (RequestUserRegistration) msg;
			userService.create(msgUser.getUser());
			return new RegistrationSuccessfully();
		} else { return new RegistrationFailed(); }

	}
	
	public static Message remove (Message msg) {
		return null;
	}
	
	public static Message session (RequestSession msg) { 
		User user = userService.read(msg.getUser().getId());
		
		if (user != null) {
			String token = TokenGenerator.getToken();
			return new SuccessfullyLogin(token);
		}
		
		return new FailLogin();
	}
	
}
