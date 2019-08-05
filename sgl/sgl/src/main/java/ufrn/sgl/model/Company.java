package ufrn.sgl.model;

import java.io.Serializable;

public class Company implements Serializable{

	
	private static final long serialVersionUID = -5757486479916397922L;
	
	private final String companyName;
	private final String cnpj;
	private final String fantasyName;
	private final String typeOfService;
	
	public Company(
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
	
	public String toString () {
		
		String msg = "\"Company Name\" : \"" + this.companyName;
		msg += "\",\n\"CNPJ\" : \"" + this.cnpj;
		msg += "\",\n\"Fantasy Name\" : \"" + this.fantasyName;
		msg += "\",\n\"Service\" : \"" + this.typeOfService + "\"";
		
		return msg;
		
	}
	
	
	
}
