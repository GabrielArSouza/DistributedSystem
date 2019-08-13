package ufrn.sgl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Bidding")
public class Bidding implements Serializable{

	private static final long serialVersionUID = 3750982075107378001L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "userId")
	@Embedded
	private User requestingUser;
	
	@Column(name = "description")
	private String description;

	@Column(name = "code")
	private int code;
	
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
