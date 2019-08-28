package ufrn.sgl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Bidding")
public class Bidding implements Serializable{

	private static final long serialVersionUID = 3750982075107378001L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User requestingUser;
	
	@Column(name = "description")
	private String description;

	@Column(name = "code")
	private int code;
	
	public Bidding () {}
	
	public Bidding(
			User requestingUser, 
			String description, 
			int code) 
	{
		super();
		this.requestingUser = requestingUser;
		this.description = description;
		this.code = code;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getRequestingUser() {
		return requestingUser;
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}
	
	
	
	public void setRequestingUser(User requestingUser) {
		this.requestingUser = requestingUser;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setCode(int code) {
		this.code = code;
	}


	@Override
	public String toString () {
		return "String [id=" + id + ", description=" + description + 
        		", code=" + code + ", userId=" + requestingUser.getId() + "]";	
	}
	
	public String getBidding () {
		String msg = "\"Request User\" {\n" 
				+ this.requestingUser.toString() + "\n}";
		msg += ",\n \"Description\" : \"" + this.description;
		msg += "\",\n \"Code\" : \"" + this.code + "\"";
		
		return msg;
	}
	
	
	
}
