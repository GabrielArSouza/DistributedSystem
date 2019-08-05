package ufrn.sgl.messages;

import java.io.Serializable;

public abstract class Message implements Serializable{

	private static final long serialVersionUID = 1L;

	public Message () { /*empty*/ }
	
	public abstract String getMessage ();
	
}
