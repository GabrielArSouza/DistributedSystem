package ufrn.sgl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company implements Serializable{

	
	private static final long serialVersionUID = -5757486479916397922L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "companyName")
	private String companyName;

	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "fantasyName")
	private String fantasyName;
	
	@Column(name = "typeOfService")
	private String typeOfService;
	
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
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	@Override
	public String toString () {
		return "String [id=" + id + ", comapanyName=" + companyName + 
        		", cnpj=" + cnpj + ", fantasyName=" + fantasyName 
        		+ ",service=" + typeOfService + "]";	
	}
	
	public String getCompany () {
		
		String msg = "\"Company Name\" : \"" + this.companyName;
		msg += "\",\n\"CNPJ\" : \"" + this.cnpj;
		msg += "\",\n\"Fantasy Name\" : \"" + this.fantasyName;
		msg += "\",\n\"Service\" : \"" + this.typeOfService + "\"";
		
		return msg;
		
	}
	
	
	
}
