package ufrn.sgl.server;

import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.connection.ConfirmConnection;
import ufrn.sgl.messages.protocol.register.RequestRegistration;
import ufrn.sgl.messages.protocol.session.FailLogin;
import ufrn.sgl.messages.protocol.session.RequestSession;
import ufrn.sgl.messages.protocol.session.SuccessfullyLogin;
import ufrn.sgl.model.User;
import ufrn.sgl.service.UserService;
import ufrn.sgl.service.interfaces.UserServiceInterface;
import ufrn.sgl.util.TokenGenerator;

public class UDPProtocolServer {

	
	public static Message connection () {
		return new ConfirmConnection();
	}
	
	public static Message register (RequestRegistration msg) {
		// TODO
		return msg;
	}
	
	public static Message session (RequestSession msg) {
		UserServiceInterface service = new UserService();
		User user = service.read(msg.getUser());
		
		if (user != null) {
			String token = TokenGenerator.getToken();
			// TODO: save the new generated session
			return new SuccessfullyLogin(token);
		}
		
		return new FailLogin();
	}
	
}
