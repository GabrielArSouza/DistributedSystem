package ufrn.sgl.util;

public class Definitions {

	public static final int SERVER_SEND_PORT = 9993;
	public static final int SERVER_RECEIVE_PORT = 9003;
	public static final int PING_PORT = 9013;
	
	
	public static final String[] SERVERS = {
		"rmi://localhost:5000/sgl",
		"rmi://localhost:5000/sgl",
	};
	
	public static final String KAFKA_BROKER = "localhost:9092";
	public static final String KAFKA_GROUP = "sql";
	
	public static final int NUMBER_SERVERS = SERVERS.length;
}
