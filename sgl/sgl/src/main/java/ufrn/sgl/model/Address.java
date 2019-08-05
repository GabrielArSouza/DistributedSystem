package ufrn.sgl.model;

import java.io.Serializable;

public class Address implements Serializable{

	private static final long serialVersionUID = 676608019260642675L;

	private final int id;
	private final String street;
	private final int number;
	private final String neighborhood;
	private final String city;
	private final String state;
	
	public Address(
		int id,
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

	public int getId() {
		return id;
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

	public String getAdress () {
		
		String address = street + "," + number 
				+ " - " + neighborhood + " - " 
				+ city + ", " + state;
		
		return address;
	}
}
