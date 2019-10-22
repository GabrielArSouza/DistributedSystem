package ufrn.sgl.messages.protocol.read;

import ufrn.sgl.messages.Message;

public class ReadFailed extends Message{

	private static final long serialVersionUID = 1605253298019032759L;

	private final long id;
	private String message = "Error: not possible read the entity data with information";
	
	public ReadFailed(long id) {
		this.id = id;
	}
	
	public ReadFailed(long id, String msg) {
		this.id = id;
		this.message = msg;
	}
	
	@Override
	public String getMessage() {
		return message + " - id: " + this.id ;
	}

	public long getId() {
		return id;
	}

}
