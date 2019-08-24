package ufrn.sgl.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company implements Serializable{
	
	private static final long serialVersionUID = -5757486479916397922L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	
	public Company () {}
	
	public Company(
			String companyName, 
			String cnpj, 
			String fantasyName, 
			String typeOfService,
			Address address,
			String email, 
			String password)
	{
		super();
		this.companyName = companyName;
		this.cnpj = cnpj;
		this.fantasyName = fantasyName;
		this.typeOfService = typeOfService;
		this.email = email;
		this.password = password;
		this.address = address;
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
	
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}

	public void setTypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
