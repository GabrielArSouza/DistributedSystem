package ufrn.sgl.server;

import java.util.Map;

import ufrn.sgl.dao.interfaces.UserSessionDaoInterface;
import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.connection.ConfirmConnection;
import ufrn.sgl.messages.protocol.register.RegistrationFailed;
import ufrn.sgl.messages.protocol.register.RegistrationSuccessfully;
import ufrn.sgl.messages.protocol.register.RequestBiddingRegistration;
import ufrn.sgl.messages.protocol.register.RequestRegistration;
import ufrn.sgl.messages.protocol.register.RequestUserRegistration;
import ufrn.sgl.messages.protocol.session.FailLogin;
import ufrn.sgl.messages.protocol.session.RequestCompanySession;
import ufrn.sgl.messages.protocol.session.RequestSession;
import ufrn.sgl.messages.protocol.session.RequestUserSession;
import ufrn.sgl.messages.protocol.session.SuccessfullyLogin;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.User;
import ufrn.sgl.model.UserSession;
import ufrn.sgl.service.BiddingService;
import ufrn.sgl.service.CompanyService;
import ufrn.sgl.service.CompanySessionService;
import ufrn.sgl.service.UserService;
import ufrn.sgl.service.UserSessionService;
import ufrn.sgl.service.interfaces.BiddingServiceInterface;
import ufrn.sgl.service.interfaces.CompanyServiceInterface;
import ufrn.sgl.service.interfaces.CompanySessionServiceInterface;
import ufrn.sgl.service.interfaces.UserServiceInterface;
import ufrn.sgl.service.interfaces.UserSessionServiceInterface;
import ufrn.sgl.util.TokenGenerator;

public class UDPProtocolServer {

	private static final UserServiceInterface userService = new UserService();
	private static final BiddingServiceInterface biddingService = new BiddingService();
	private static final CompanyServiceInterface companyService = new CompanyService();
	private static final UserSessionServiceInterface userSessionService = new UserSessionService();
	private static final CompanySessionServiceInterface companySessionService = new CompanySessionService();
	
	public static Message connection () {
		return new ConfirmConnection();
	}
	
	public static Message register (Message msg) {
		
		if (msg.getClass().equals(RequestUserRegistration.class)) {
			RequestUserRegistration msgUser = (RequestUserRegistration) msg;
			userService.create(msgUser.getUser());
			return new RegistrationSuccessfully();
		} 
		else if   (msg.getClass().equals(RequestBiddingRegistration.class)) {
			RequestBiddingRegistration msgBidding = (RequestBiddingRegistration) msg;
			if (userSessionService.read(new UserSession(msgBidding.getUser(), msgBidding.getToken())) != null) {
				biddingService.create(msgBidding.getBidding());
				return new RegistrationSuccessfully();
			}else {return new RegistrationFailed();}
		}		
		else { return new RegistrationFailed(); }

	}
	
	public static Message remove (Message msg) {
		return null;
	}
	
	public static Message session (Message msg) { 
		
		if (msg.getClass().equals(RequestUserSession.class)) {
			RequestUserSession msgUser = (RequestUserSession) msg;
			User user = userService.read(msgUser.getUser());
			if (user != null) {
				String token = TokenGenerator.getToken();
				userSessionService.create(new UserSession(user, token));
				return new SuccessfullyLogin(token);
			}
		
		} else if (msg.getClass().equals(RequestCompanySession.class)) {
			RequestCompanySession msgCompany = (RequestCompanySession) msg;
			Company company = companyService.read(msgCompany.getCompany());
			if (company != null) {
				String token = TokenGenerator.getToken();
				return new SuccessfullyLogin(token);
			}
			
			
		}
		
		
		
		
		
		return new FailLogin();
	}
	
}
