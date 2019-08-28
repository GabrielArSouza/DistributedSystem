package ufrn.sgl.model;

import java.io.Serializable;

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
@Table (name = "Tender")
public class Tender implements Serializable {

	private static final long serialVersionUID = -9081239952128040140L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique=true)
	private long id;
	
	@OneToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	@OneToOne
	@JoinColumn(name="bidding_id")
	private Bidding bidding;
	
	@Column(name = "tender")
	private Double tender;
	
	public Tender () {} 
	
	public Tender(Company company, Bidding bidding, Double tender) {
		super();
		this.company = company;
		this.bidding = bidding;
		this.tender = tender;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public Bidding getBidding() {
		return bidding;
	}


	public void setBidding(Bidding bidding) {
		this.bidding = bidding;
	}


	public Double getTender() {
		return tender;
	}


	public void setTender(Double tender) {
		this.tender = tender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
