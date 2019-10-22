package ufrn.sgl.messages;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import ufrn.sgl.util.TokenGenerator;

public abstract class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	protected InetAddress origin;
	private String ip;
	private String machine;
	private final String token = TokenGenerator.getToken(); 
	private String addressee;
	
	public Message () { 
		try {
			this.ip = InetAddress.getLocalHost().getHostAddress();
			this.machine = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setAddressee (String ip) {this.addressee = ip;}
	
	public abstract String getMessage ();

	public InetAddress getOrigin() {
		return origin;
	}

	public void setOrigin(InetAddress inetAddress) {
		this.origin = inetAddress;
	}
	
	public String getIP () {return this.ip;}
	public String getMachine () {return this.machine;}	
	public String getToken () {return this.token;}
	public String getAddressee() {return this.addressee;}
}
