package ufrn.sgl.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.User;

public interface RMIInterface extends Remote{

	public String helloTo (String name) throws RemoteException;

	// Methods for user
	void registerUser(User user) throws RemoteException;
	User readUser (User user) throws RemoteException;
	String userLogin (User user) throws RemoteException;
	void userLogout (String token) throws RemoteException;
 
	/// Methods for Bidding
	void createBidding (Bidding bidding, String token) throws RemoteException;
	Bidding readBidding (Bidding bidding, String token) throws RemoteException;
	
	// Methods for Company
	void createCompany (Company company) throws RemoteException;
	Company readCompany (Company company) throws RemoteException;
	String companyLogin (Company company) throws RemoteException;
	void companyLogout (String token) throws RemoteException;
	
}
