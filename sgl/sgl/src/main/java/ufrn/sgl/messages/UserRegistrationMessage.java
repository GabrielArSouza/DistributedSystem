package ufrn.sgl.messages;

import ufrn.sgl.common.Address;

public class UserRegistrationMessage extends Message {

	private static final long serialVersionUID = 3L;
	
	private final String jurisdictionalName;
	private final String jurisdictionalCode;
	private final String cnpj;
	private final Address address;
	
	public UserRegistrationMessage(
			String jurisdictionalName, 
			String jurisdictionalCode, 
			String cnpj, 
			Address address) 
	{
		super();
		this.jurisdictionalName = jurisdictionalName;
		this.jurisdictionalCode = jurisdictionalCode;
		this.cnpj = cnpj;
		this.address = address;
	}

	@Override
	public String getMessage() {
		
		String msg = "{ \"type\": \"User Registration\"";
		msg += ",\n\"Jurisdictional Name\" : " + this.jurisdictionalName;
		msg += ",\n\"Jurisdictional Code\" : " + this.jurisdictionalCode;
		msg += ",\n\"CNPJ\" : " + this.cnpj;
		msg += ",\n\"Address\" : " + address.getAdress() + "\n}";
		
		return msg;
	}
	
	
	
	
}
