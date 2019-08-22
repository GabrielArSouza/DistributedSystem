package ufrn.sgl.messages.protocol.remove;

import ufrn.sgl.messages.Message;

public class RemoveSuccessfully extends Message{

	private static final long serialVersionUID = 1423470320188702080L;

	@Override
	public String getMessage() {
		return "remove ok";
	}

}
