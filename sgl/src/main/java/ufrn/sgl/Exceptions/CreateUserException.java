package ufrn.sgl.Exceptions;

public class CreateUserException extends Exception{

	private static final long serialVersionUID = -352793238914174093L;
	private String msg = "Error - connection failure or user already exist";
	
	public CreateUserException( String msg ) {
		this.msg = msg;
	}
	
	public CreateUserException() {}
	
	public String getMessage () {return msg;}	
	
}
