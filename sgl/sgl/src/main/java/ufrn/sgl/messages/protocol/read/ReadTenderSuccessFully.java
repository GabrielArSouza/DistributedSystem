package ufrn.sgl.messages.protocol.read;

import ufrn.sgl.model.Tender;

public class ReadTenderSuccessFully extends ReadSuccessfully{

	private static final long serialVersionUID = -636579802639419119L;

	private final Tender tender;
	
	public ReadTenderSuccessFully(Tender tender) {
		super();
		this.tender = tender;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
