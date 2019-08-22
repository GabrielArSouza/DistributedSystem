package ufrn.sgl.messages;

import java.io.Serializable;
import java.net.InetAddress;

public abstract class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	protected InetAddress origin;
	
	public Message () { /*empty*/ }
	
	public abstract String getMessage ();

	public InetAddress getOrigin() {
		return origin;
	}

	public void setOrigin(InetAddress inetAddress) {
		this.origin = inetAddress;
	}
	
	
	
}
