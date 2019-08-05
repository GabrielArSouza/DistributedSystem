package ufrn.sgl.messages;

public class CompanyRegistrationMessage extends Message {

	private static final long serialVersionUID = 2L;

	private final String companyName;
	private final String cnpj;
	private final String fantasyName;
	private final String typeOfService;
	
	public CompanyRegistrationMessage(
			String companyName,
			String cnpj, 
			String fantasyName, 
			String typeOfService) 
	{
		super();
		this.companyName = companyName;
		this.cnpj = cnpj;
		this.fantasyName = fantasyName;
		this.typeOfService = typeOfService;
	}

	/**
	 * Getters and Setter
	 */
	
	public String getCompanyName() {
		return companyName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getFantasyName() {
		return fantasyName;
	}

	public String getTypeOfService() {
		return typeOfService;
	}

	@Override
	public String getMessage() {
		
		String msg = "{ \"type\": \"Company Registration\"";
		msg += ",\n\"Company Name\" : " + this.companyName;
		msg += ",\n\"CNPJ\" : " + this.cnpj;
		msg += ",\n\"Fantasy Name\" : " + this.fantasyName;
		msg += ",\n\"Service\" : " + this.typeOfService + "\n}";
		
		return msg;
	}

	
	
	
	
}
