package ufrn.sgl.model;

import java.io.Serializable;

public class Bidding implements Serializable{

	private static final long serialVersionUID = 3750982075107378001L;

	private final User requestingUser;
	private final String description;
	private final int code;
	
	public Bidding(
			User requestingUser, 
			String description, 
			int code) 
	{
		super();
		this.requestingUser = requestingUser;
		this.description = description;
		this.code = code;
	}

	public User getRequestingUser() {
		return requestingUser;
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}
	
	@Override
	public String toString () {
		String msg = "\"Request User\" {\n" 
				+ this.requestingUser.toString() + "\n}";
		msg += ",\n \"Description\" : \"" + this.description;
		msg += "\",\n \"Code\" : \"" + this.code + "\"";
		
		return msg;
	}
	
	
	
}
