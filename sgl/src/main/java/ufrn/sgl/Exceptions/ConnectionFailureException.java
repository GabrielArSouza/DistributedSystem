package ufrn.sgl.Exceptions;

public class ConnectionFailureException extends Exception{

	private final String msg;
	private static final long serialVersionUID = -2725162590770657005L;
	
	public ConnectionFailureException () {this.msg = "Error: Server Offline";}
	public ConnectionFailureException (String msg) {this.msg = msg;}
	
	public String getMessage() {return this.msg;}
	
}
