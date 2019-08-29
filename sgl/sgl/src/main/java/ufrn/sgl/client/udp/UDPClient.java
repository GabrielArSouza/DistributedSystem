package ufrn.sgl.client.udp;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.list.ListBiddingSuccessfully;
import ufrn.sgl.messages.protocol.list.ListTenderSuccessfully;
import ufrn.sgl.messages.protocol.list.RequestBiddingList;
import ufrn.sgl.messages.protocol.list.RequestTenderList;
import ufrn.sgl.messages.protocol.logout.RequestCompanyLogout;
import ufrn.sgl.messages.protocol.logout.RequestUserLogout;
import ufrn.sgl.messages.protocol.logout.SuccessfullyLogout;
import ufrn.sgl.messages.protocol.read.ReadBiddingSuccessfully;
import ufrn.sgl.messages.protocol.read.ReadCompanySuccessfully;
import ufrn.sgl.messages.protocol.read.ReadTenderSuccessFully;
import ufrn.sgl.messages.protocol.read.ReadUserSuccessfully;
import ufrn.sgl.messages.protocol.read.RequestBiddingRead;
import ufrn.sgl.messages.protocol.read.RequestCompanyRead;
import ufrn.sgl.messages.protocol.read.RequestTenderRead;
import ufrn.sgl.messages.protocol.read.RequestUserRead;
import ufrn.sgl.messages.protocol.register.RegistrationSuccessfully;
import ufrn.sgl.messages.protocol.register.RequestBiddingRegistration;
import ufrn.sgl.messages.protocol.register.RequestCompanyRegistration;
import ufrn.sgl.messages.protocol.register.RequestTenderRegistration;
import ufrn.sgl.messages.protocol.register.RequestUserRegistration;
import ufrn.sgl.messages.protocol.remove.RemoveSuccessfully;
import ufrn.sgl.messages.protocol.remove.RequestBiddingRemove;
import ufrn.sgl.messages.protocol.remove.RequestTenderRemove;
import ufrn.sgl.messages.protocol.session.RequestCompanySession;
import ufrn.sgl.messages.protocol.session.RequestUserSession;
import ufrn.sgl.messages.protocol.session.SuccessfullySession;
import ufrn.sgl.messages.protocol.update.RequestBiddingUpdate;
import ufrn.sgl.messages.protocol.update.RequestCompanyUpdate;
import ufrn.sgl.messages.protocol.update.RequestTenderUpdate;
import ufrn.sgl.messages.protocol.update.RequestUserUpdate;
import ufrn.sgl.messages.protocol.update.UpdateSuccessfully;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.Tender;
import ufrn.sgl.model.User;

public class UDPClient {
	
	private final UDPProtocolClient protocol;
	private final String successMessage =  "operation successfully performed";
	
	public UDPClient() throws SocketException, UnknownHostException {
		this.protocol = new UDPProtocolClient();
	}
	
	/**
	 * CREATE
	 */
	
	public void createUser ( User user ) {
		Message response = protocol.requestOperation(new RequestUserRegistration(user));
		this.createResult(response);
	}
	
	public void createCompany ( Company company ) {
		Message response = protocol.requestOperation(new RequestCompanyRegistration(company));
		this.createResult(response);
	}
	
	public void createBidding ( Bidding bidding, String token ) {
		Message response = protocol.requestOperation(
				new RequestBiddingRegistration(bidding, token));
		this.createResult(response);
	}
	
	public void createTender (Tender tender, String token ) {
		Message response = protocol.requestOperation(
				new RequestTenderRegistration(tender, token));
		this.createResult(response);
	}
	
	private void createResult (Message response) {
		if (response.getClass().equals(RegistrationSuccessfully.class))
			System.out.println(successMessage);
		else System.out.println(response.getMessage());
	}
	
	/**
	 * REMOVE
	 */
	
	public void removeBidding (long id, String token) {
		Message response = protocol.requestOperation(
				new RequestBiddingRemove(id, token));
		this.removeResult(response);
	}

	public void removeTender (long id, String token) {
		Message response = protocol.requestOperation( 
				new RequestTenderRemove(id, token));
		this.removeResult(response);
				
	}
	
	private void removeResult (Message msg) {
		if (msg.getClass().equals(RemoveSuccessfully.class))
			System.out.println(successMessage);
		else System.out.println(msg.getMessage());
	}
	
	/**
	 * UPDATE
	 */
	
	public void updateUser (User user) {
		Message response = protocol.requestOperation(
				new RequestUserUpdate(user));
		this.updateResult(response);
	}
	
	public void updateCompany (Company company) {
		Message response = protocol.requestOperation(
				new RequestCompanyUpdate(company));
		this.updateResult(response);
	}
	
	public void updateBidding (Bidding bidding, String token) {
		Message response = protocol.requestOperation(
				new RequestBiddingUpdate(bidding, token));
		this.updateResult(response);
	}
	
	public void updateTender (Tender tender, String token) {
		Message response = protocol.requestOperation(
				new RequestTenderUpdate(tender, token));
		this.updateResult(response);
	}
	
	private void updateResult (Message msg) {
		if (msg.getClass().equals(UpdateSuccessfully.class))
			System.out.println(successMessage);
		else System.out.println(msg.getMessage());
	}
	
	/**
	 * READ
	 */
	
	public User readUser (User user) {
		Message response = protocol.requestOperation( 
				new RequestUserRead(user));
		if (response.getClass().equals(ReadUserSuccessfully.class)) {
			ReadUserSuccessfully u = (ReadUserSuccessfully) response;
			return u.getUser();
		}else {
			System.out.println("Erro na consulta");
			return null;
		}
	}
	
	public Company readCompany (Company company) {
		Message response = protocol.requestOperation( 
				new RequestCompanyRead(company));
		if (response.getClass().equals(ReadCompanySuccessfully.class)) {
			ReadCompanySuccessfully c = (ReadCompanySuccessfully) response;
			return c.getCompany();
		}else {
			System.out.println("Erro na consulta");
			return null;
		}
	}
	
	public Bidding readBidding (Bidding bidding, String token) {
		Message response = protocol.requestOperation( 
				new RequestBiddingRead(bidding, token));
		if (response.getClass().equals(ReadBiddingSuccessfully.class)) {
			ReadBiddingSuccessfully b = (ReadBiddingSuccessfully) response;
			return b.getBidding();
		}else {
			System.out.println("Erro na consulta");
			return null;
		}
	}
	
	public Tender readTender (Tender tender, String token) {
		Message response = protocol.requestOperation( 
				new RequestTenderRead(tender, token));
		if (response.getClass().equals(ReadTenderSuccessFully.class)) {
			ReadTenderSuccessFully t = (ReadTenderSuccessFully) response;
			return t.getTender();
		}else {
			System.out.println("Erro na consulta");
			return null;
		}
	}
	
	/**
	 * LIST
	 */
	
	public List<Bidding> listBidding (String token) { 
		Message response = protocol.requestOperation( 
				new RequestBiddingList(token));
		if (response.getClass().equals(ListBiddingSuccessfully.class)) {
			ListBiddingSuccessfully b = (ListBiddingSuccessfully) response;
			return b.getBiddings();
		}else return null;
	}
	
	public List<Tender> listTender (String token) { 
		Message response = protocol.requestOperation( 
				new RequestTenderList(token));
		if (response.getClass().equals(ListTenderSuccessfully.class)) {
			ListTenderSuccessfully b = (ListTenderSuccessfully) response;
			return b.getTender();
		}else return null;
	}
	
	/**
	 * LOGIN
	 */
	
	public String userLogin ( User user ) {
		Message response = protocol.requestOperation(new RequestUserSession(user));
		if (response.getClass().equals( SuccessfullySession.class )) {
			SuccessfullySession session = (SuccessfullySession) response;
			System.out.println("Successfully Session - Token: " + session.getMessage());
			return session.getMessage();
		} else return response.getMessage();
	}
	
	public String companyLogin ( Company company ) {
		Message response = protocol.requestOperation(new RequestCompanySession(company));
		if (response.getClass().equals( SuccessfullySession.class )) {
			SuccessfullySession session = (SuccessfullySession) response;
			System.out.println("Successfully Session - Token: " + session.getMessage());
			return session.getMessage();
		} else return response.getMessage();
	}
	
	/**
	 * LOGOUT
	 */
	
	public void userLogout ( String token ) {
		Message response = protocol.requestOperation(new RequestUserLogout(token));
		if (response.getClass().equals( SuccessfullyLogout.class ))
			System.out.println("Logout Successfully");
		else System.out.println(response.getMessage());
	}
	
	
	public void companyLogout ( String token ) {
		Message response = protocol.requestOperation(new RequestCompanyLogout(token));
		if (response.getClass().equals( SuccessfullyLogout.class ))
			System.out.println("Logout Successfully");
		else System.out.println(response.getMessage());
	}
	
	
}
