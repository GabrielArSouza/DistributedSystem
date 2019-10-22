package ufrn.sgl.messages.protocol.remove;

import ufrn.sgl.messages.Message;

public class RemoveFailed extends Message{

	private static final long serialVersionUID = 3903033985059363131L;
	
	@Override
	public String getMessage() {
		return "Error: remove failed";
	}

	
	
}
