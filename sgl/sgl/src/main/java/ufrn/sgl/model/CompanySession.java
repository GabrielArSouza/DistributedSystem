package ufrn.sgl.model;

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
@Table(name = "CompanySessions")
public class CompanySession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="company_id")
	private Company company;
	
	@Column(name = "token")
	private String token;

	public CompanySession() {}
	
	public CompanySession(Company company, String token) {
		super();
		this.company = company;
		this.token = token;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
}
