package ufrn.sgl.server;

import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.connection.ConfirmConnection;
import ufrn.sgl.messages.protocol.logout.FailLogout;
import ufrn.sgl.messages.protocol.logout.RequestCompanyLogout;
import ufrn.sgl.messages.protocol.logout.RequestUserLogout;
import ufrn.sgl.messages.protocol.logout.SuccessfullyLogout;
import ufrn.sgl.messages.protocol.read.ReadBiddingSuccessfully;
import ufrn.sgl.messages.protocol.read.ReadFailed;
import ufrn.sgl.messages.protocol.read.ReadUserSuccessfully;
import ufrn.sgl.messages.protocol.read.RequestBiddingRead;
import ufrn.sgl.messages.protocol.read.RequestUserRead;
import ufrn.sgl.messages.protocol.register.RegistrationFailed;
import ufrn.sgl.messages.protocol.register.RegistrationSuccessfully;
import ufrn.sgl.messages.protocol.register.RequestBiddingRegistration;
import ufrn.sgl.messages.protocol.register.RequestCompanyRegistration;
import ufrn.sgl.messages.protocol.register.RequestTenderRegistration;
import ufrn.sgl.messages.protocol.register.RequestUserRegistration;
import ufrn.sgl.messages.protocol.remove.RemoveFailed;
import ufrn.sgl.messages.protocol.remove.RemoveSuccessfully;
import ufrn.sgl.messages.protocol.remove.RequestBiddingRemove;
import ufrn.sgl.messages.protocol.remove.RequestTenderRemove;
import ufrn.sgl.messages.protocol.session.FailSession;
import ufrn.sgl.messages.protocol.session.RequestCompanySession;
import ufrn.sgl.messages.protocol.session.RequestUserSession;
import ufrn.sgl.messages.protocol.session.SuccessfullySession;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.CompanySession;
import ufrn.sgl.model.User;
import ufrn.sgl.model.UserSession;
import ufrn.sgl.service.BiddingService;
import ufrn.sgl.service.CompanyService;
import ufrn.sgl.service.CompanySessionService;
import ufrn.sgl.service.TenderService;
import ufrn.sgl.service.UserService;
import ufrn.sgl.service.UserSessionService;
import ufrn.sgl.service.interfaces.BiddingServiceInterface;
import ufrn.sgl.service.interfaces.CompanyServiceInterface;
import ufrn.sgl.service.interfaces.CompanySessionServiceInterface;
import ufrn.sgl.service.interfaces.TenderServiceInterface;
import ufrn.sgl.service.interfaces.UserServiceInterface;
import ufrn.sgl.service.interfaces.UserSessionServiceInterface;
import ufrn.sgl.util.TokenGenerator;

public class UDPProtocolServer {

	private static final UserServiceInterface userService = new UserService();
	private static final BiddingServiceInterface biddingService = new BiddingService();
	private static final CompanyServiceInterface companyService = new CompanyService();
	private static final UserSessionServiceInterface userSessionService = new UserSessionService();
	private static final CompanySessionServiceInterface companySessionService = new CompanySessionService();
	private static final TenderServiceInterface tenderService = new TenderService();
	
	public static Message connection () {
		return new ConfirmConnection();
	}
	
	public static Message register (Message msg) {
		
		if (msg.getClass().equals(RequestUserRegistration.class)) {
			RequestUserRegistration msgUser = (RequestUserRegistration) msg;
			userService.create(msgUser.getUser());
			return new RegistrationSuccessfully();
		} 
		
		else if (msg.getClass().equals(RequestCompanyRegistration.class)) {
			RequestCompanyRegistration msgCompany = (RequestCompanyRegistration) msg;
			companyService.create(msgCompany.getCompany());
			return new RegistrationSuccessfully();
		}

		else if (msg.getClass().equals(RequestBiddingRegistration.class)) {
			RequestBiddingRegistration msgBidding = (RequestBiddingRegistration) msg;
			if (userSessionService.read(msgBidding.getToken()) != null) {
				biddingService.create(msgBidding.getBidding());
				return new RegistrationSuccessfully();
			}else {return new RegistrationFailed();}
		}	

		else if (msg.getClass().equals(RequestTenderRegistration.class)) {
			RequestTenderRegistration msgTender = (RequestTenderRegistration) msg;
			if (companySessionService.read(msgTender.getToken()) != null) {
				tenderService.create(msgTender.getTender());
				return new RegistrationSuccessfully();
			}else return new RegistrationFailed();
			
		}
		
		else { return new RegistrationFailed(); }

	}
	
	public static Message read (Message msg) {
		
		if (msg.getClass().equals(RequestBiddingRead.class)) {
			RequestBiddingRead msgBidding = (RequestBiddingRead) msg;
			
			// check session
			if (userSessionService.read(msgBidding.getToken()) == null)
				return new ReadFailed(msgBidding.getBidding().getId());
			
			Bidding b = biddingService.read(msgBidding.getBidding());
			if (b != null) return new ReadBiddingSuccessfully(b);
			else return new ReadFailed(msgBidding.getId());
		
		} else if (msg.getClass().equals(RequestUserRead.class)) {
			RequestUserRead msgBidding = (RequestUserRead) msg;
			User u = userService.read(msgBidding.getUser());
			if (u != null) return new ReadUserSuccessfully(u);
			else return new ReadFailed(msgBidding.getId());
		}
		
		return new ReadFailed(-1);
		
		
	}
	
	public static Message remove (Message msg) {
		
		if (msg.getClass().equals(RequestBiddingRemove.class)) {
			RequestBiddingRemove msgBidding = (RequestBiddingRemove) msg;
			biddingService.delete(msgBidding.getId());
			return new RemoveSuccessfully();
		
		} else if (msg.getClass().equals(RequestTenderRemove.class)) {
			RequestTenderRemove msgTender = (RequestTenderRemove) msg;
			tenderService.delete(msgTender.getId());
			return new RemoveSuccessfully();
		
		}
		
		return new RemoveFailed();
		
	}
	
	public static Message session (Message msg) { 
		
		if (msg.getClass().equals(RequestUserSession.class)) {
			RequestUserSession msgUser = (RequestUserSession) msg;
			User user = userService.read(msgUser.getUser());
			if (user != null) {
				String token = TokenGenerator.getToken();
				userSessionService.create(new UserSession(user, token));
				return new SuccessfullySession(token);
			}
		
		} else if (msg.getClass().equals(RequestCompanySession.class)) {
			RequestCompanySession msgCompany = (RequestCompanySession) msg;
			Company company = companyService.read(msgCompany.getCompany());
			if (company != null) {
				String token = TokenGenerator.getToken();
				companySessionService.create(new CompanySession(company, token));
				return new SuccessfullySession(token);
			}	
		}
		
		return new FailSession();
	}
	
	public static Message logout (Message msg) { 
	
		if ( msg.getClass().equals(RequestUserLogout.class)) {
			RequestUserLogout msgLogout = (RequestUserLogout) msg;
			UserSession userSession = userSessionService.read(msgLogout.getUserSession());
			if (userSession != null) {
				userSessionService.delete(userSession.getId());
				return new SuccessfullyLogout();
			}
			
		} else if ( msg.getClass().equals(RequestCompanyLogout.class)) {
			RequestCompanyLogout msgLogout = (RequestCompanyLogout) msg;
			CompanySession companySession = companySessionService.read(msgLogout.getSession());
			if (companySession != null) {
				companySessionService.delete(companySession.getId());
				return new SuccessfullyLogout();
			}
		}
		
		return new FailLogout();	
	}
	
}
