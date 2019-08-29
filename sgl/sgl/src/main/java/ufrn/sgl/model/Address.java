package ufrn.sgl.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Embeddable
@Table(name = "Address")
public class Address implements Serializable{

	private static final long serialVersionUID = 676608019260642675L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "number")
	private int number;

	@Column(name = "neighborhood")
	private String neighborhood;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	
	public Address () {	}
	
	public Address (long id) { this.id = id;}
	
	public Address(
		long id,
		String street, 
		int number, 
		String neighborhood, 
		String city, 
		String state)
	{
		super();
		this.id = id;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
	}
	
	public Address(
		String street, 
		int number, 
		String neighborhood, 
		String city, 
		String state)
	{
		super();
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
	}

	public long getId() {
		return id;
	}
	
	public void setId( long id ) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public int getNumber() {
		return number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}
	
	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAdress () {
		
		String address = street + "," + number 
				+ " - " + neighborhood + " - " 
				+ city + ", " + state;
		
		return address;
	}
	
    @Override
    public String toString() {
        return "String [id=" + id + ", street=" + street + 
        		", number=" + number + ", neighborhood=" + neighborhood +
        		", city=" + city + ", state=" + state + "]";
    }
}
