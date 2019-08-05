package ufrn.sgl.model;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -7698133836342063149L;
	
	private final String jurisdictionalName;
	private final String jurisdictionalCode;
	private final String cnpj;
	private final Address address;
	private final String email;
	
	public User(
			String jurisdictionalName, 
			String jurisdictionalCode, 
			String cnpj, 
			Address address,
			String email) 
	{
		super();
		this.jurisdictionalName = jurisdictionalName;
		this.jurisdictionalCode = jurisdictionalCode;
		this.cnpj = cnpj;
		this.address = address;
		this.email = email;
	}

	public String getJurisdictionalName() {
		return jurisdictionalName;
	}

	public String getJurisdictionalCode() {
		return jurisdictionalCode;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Address getAddress() {
		return address;
	}
	
	@Override
	public String toString () {
		
		String msg = "\"Jurisdictional Name\" : \"" + this.jurisdictionalName;
		msg += "\",\n\"Jurisdictional Code\" : \"" + this.jurisdictionalCode;
		msg += "\",\n\"CNPJ\" : \"" + this.cnpj;
		msg += "\",\n\"Address\" : \"" + address.getAdress();
		msg += "\",\n\"Email\" : \"" + this.email;
		
		return msg;
	}
	
	
	
	
	
}
