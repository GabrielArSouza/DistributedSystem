package ufrn.sgl.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Embeddable
@Table(name = "User")
public class User implements Serializable{

	private static final long serialVersionUID = -7698133836342063149L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "jurisdictionalName")
	private String jurisdictionalName;
	
	@Column(name = "jurisdictionalCode")
	private String jurisdictionalCode;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	
	public User (String email, String password){
		this.email = email;
		this.password = password;
	}
	
	public User () {}
	
	public User(
			String jurisdictionalName, 
			String jurisdictionalCode, 
			String cnpj, 
			Address address,
			String email,
			String password) 
	{
		super();
		this.jurisdictionalName = jurisdictionalName;
		this.jurisdictionalCode = jurisdictionalCode;
		this.cnpj = cnpj;
		this.address = address;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public String getJurisdictionalName() {
		return jurisdictionalName;
	}

	public String getJurisdictionalCode() {
		return jurisdictionalCode;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public void setJurisdictionalName(String jurisdictionalName) {
		this.jurisdictionalName = jurisdictionalName;
	}

	public void setJurisdictionalCode(String jurisdictionalCode) {
		this.jurisdictionalCode = jurisdictionalCode;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString () {
		return "String [id=" + id + ", jurisdictionalName=" + jurisdictionalName + 
        		", jurisdictionalCode=" + jurisdictionalCode 
        		+ ", cnpj=" + cnpj + ", email=" + email + ", addressId=" +
        		address.getId() + "]";	
	}
	
	public String getUser () {
		
		String msg = "\"Jurisdictional Name\" : \"" + this.jurisdictionalName;
		msg += "\",\n\"Jurisdictional Code\" : \"" + this.jurisdictionalCode;
		msg += "\",\n\"CNPJ\" : \"" + this.cnpj;
		msg += "\",\n\"Address\" : \"" + address.getAdress();
		msg += "\",\n\"Email\" : \"" + this.email;
		
		return msg;
	}
	
}
