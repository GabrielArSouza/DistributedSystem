package ufrn.sgl.messages.protocol.register;

import ufrn.sgl.messages.Message;

public class RequestRegistration extends Message {

	private static final long serialVersionUID = 2433153709589284567L;
	private final Object entity; 
	
	public RequestRegistration(Object entity) {
		this.entity = entity;
	}
	
	@Override
	public String getMessage() {
		return entity.toString();
	}

}
