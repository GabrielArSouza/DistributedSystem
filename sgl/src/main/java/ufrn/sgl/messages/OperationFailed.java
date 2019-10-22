package ufrn.sgl.messages;

public class OperationFailed extends Message{

	private static final long serialVersionUID = -5872369477777205648L;
	private String msg = "Error - operation failed";
	
	public OperationFailed() {}
	
	public OperationFailed (String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}

}
