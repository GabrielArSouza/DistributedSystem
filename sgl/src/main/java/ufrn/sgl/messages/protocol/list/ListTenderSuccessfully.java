package ufrn.sgl.messages.protocol.list;

import java.util.List;

import ufrn.sgl.model.Tender;

public class ListTenderSuccessfully extends ListSuccessfully{

	private static final long serialVersionUID = 8217242055134988278L;

	private final List<Tender> tender;
	
	public ListTenderSuccessfully(List<Tender> tender) {
		super();
		this.tender = tender;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Tender> getTender() {
		return tender;
	}

	
	
}
