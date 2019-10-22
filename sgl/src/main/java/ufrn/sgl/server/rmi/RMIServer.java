package ufrn.sgl.server.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.CompanySession;
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

public class RMIServer extends UnicastRemoteObject implements RMIInterface{

	private static final long serialVersionUID = 4384666619828515080L;
	private static final UserServiceInterface userService = new UserService();
	private static final UserSessionServiceInterface userSessionService = new UserSessionService();
	private static final BiddingServiceInterface biddingService = new BiddingService();
	private static final CompanyServiceInterface companyService = new CompanyService();
	private static final CompanySessionServiceInterface companySessionService = new CompanySessionService();

	
	protected RMIServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String helloTo(String name) throws RemoteException {
		System.err.println(name + " is trying to contact!");
		return "Server says hello to " + name;
	}
	
	@Override
	public void registerUser (User user) throws RemoteException {
		userService.create(user);
	}
	

    public static void main(String[] args) {
    	try{  
    		RMIInterface stub=new RMIServer();  
    		LocateRegistry.createRegistry(5000);
    		Naming.rebind("rmi://0.0.0.0:5000/sgl",stub); 
    		System.out.println("Server running...");
    	}catch(Exception e){e.printStackTrace();}  
    		
    }

	@Override
	public User readUser(User user) throws RemoteException {
		return userService.read(user);
	}

	@Override
	public String userLogin(User user) throws RemoteException {
		User newUser = userService.read(user);
		if (newUser != null) {
			String token = TokenGenerator.getToken();
			userSessionService.create(new UserSession(newUser, token));
			return token;
		}else return null;
	}

	@Override
	public void userLogout(String token) throws RemoteException {
		UserSession userSession = userSessionService.read(token);
		if (userSession != null) {
			userSessionService.delete(userSession.getId());
		}
		
	}

	@Override
	public void createBidding(Bidding bidding, String token) throws RemoteException {
		if (userSessionService.read(token) != null)
			biddingService.create(bidding);
	}

	@Override
	public Bidding readBidding(Bidding bidding, String token) throws RemoteException {
		if (userSessionService.read(token) != null)
			return biddingService.read(bidding);
		return null;
	}

	@Override
	public void createCompany(Company company) throws RemoteException {
		companyService.create(company);
	}

	@Override
	public Company readCompany(Company company) throws RemoteException {
		return companyService.read(company);
	}

	@Override
	public String companyLogin(Company company) throws RemoteException {
		Company newCompany = companyService.read(company);
		if (newCompany != null) {
			String token = TokenGenerator.getToken();
			companySessionService.create(new CompanySession(newCompany, token));
			return token;
		}	
		return null;
	}

	@Override
	public void companyLogout(String token) throws RemoteException {
		CompanySession companySession = companySessionService.read(token);
		if (companySession != null) {
			companySessionService.delete(companySession.getId());
		}
		
	}

}
