package ufrn.sgl.Exceptions;

public class TokenErrorException extends Exception{

	private static final long serialVersionUID = 2760320454732856326L;
	
	private String msg = "Error: unrecognized token";
	
	public TokenErrorException(String msg) {
		this.msg = msg;
	}
	
	public TokenErrorException() {}

	public String getMessage () { return this.msg; }

}
