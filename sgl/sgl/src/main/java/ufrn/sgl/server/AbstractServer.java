package ufrn.sgl.server;

import java.io.IOException;
import java.net.InetAddress;

import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.connection.CheckConnection;
import ufrn.sgl.messages.protocol.list.RequestList;
import ufrn.sgl.messages.protocol.logout.RequestLogout;
import ufrn.sgl.messages.protocol.read.RequestRead;
import ufrn.sgl.messages.protocol.register.RequestRegistration;
import ufrn.sgl.messages.protocol.remove.RequestRemove;
import ufrn.sgl.messages.protocol.session.RequestSession;
import ufrn.sgl.messages.protocol.update.RequestUpdate;
import ufrn.sgl.model.User;
import ufrn.sgl.service.UserService;
import ufrn.sgl.service.interfaces.UserServiceInterface;
import ufrn.sgl.util.Definitions;
import ufrn.sgl.util.MessageConvert;

public abstract class AbstractServer {

	protected MessageConvert msgConvert = MessageConvert.getInstance();
	protected static final UserServiceInterface service = new UserService();
	
	protected abstract void sendMessage ( Message msg, InetAddress address, int port ) throws IOException;
	protected abstract Message receiveMessage() throws IOException, ClassNotFoundException;
	
	protected void startDatabase () {
		// Start database connection
		System.out.println("Starting the databse connection...");
		User user = new User("init", "init");
		service.create(user);
		user = service.read(user);
		service.delete(user.getId());
		System.out.println("Done!");
	}
	
	public void run () throws ClassNotFoundException, IOException {
		
		while (true) {
			Message msg = this.receiveMessage();
			
			// check messages
			if ( msg.getClass().equals(CheckConnection.class) ) { 
				Message replyMessage = ProtocolServer.connection();
				sendMessage(replyMessage, msg.getOrigin(), Definitions.PING_PORT);
			
			} else if (msg.getClass().getSuperclass().equals(RequestSession.class)) {
				System.out.println("requestSession");
				Message reply = ProtocolServer.session( msg );
				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
			
			} else if (msg.getClass().getSuperclass().equals(RequestLogout.class)) {
				System.out.println("request logout");
				Message reply = ProtocolServer.logout( msg );
				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
		
			} else if (msg.getClass().getSuperclass().equals(RequestRegistration.class)) {
				Message reply = ProtocolServer.register(msg);
				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
			
			} else if (msg.getClass().getSuperclass().equals(RequestRemove.class)) {
				Message reply = ProtocolServer.remove(msg);
				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
			
			} else if (msg.getClass().getSuperclass().equals(RequestRead.class)) {
				Message reply = ProtocolServer.read(msg);
				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
			
			} else if (msg.getClass().getSuperclass().equals(RequestUpdate.class)) {
				Message reply = ProtocolServer.update(msg);
				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
				
			} else if (msg.getClass().getSuperclass().equals(RequestList.class)) {
				Message reply = ProtocolServer.list(msg);
				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
			}
			
			else continue;
			
		}
	}
	
	
}
