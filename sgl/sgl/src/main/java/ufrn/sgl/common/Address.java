package ufrn.sgl.common;

public class Address {

	private final String street;
	private final int number;
	private final String neighborhood;
	private final String city;
	private final String state;
	
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
	
	
	public String getAdress () {
		
		String address = street + "," + number 
				+ " - " + neighborhood + " - " 
				+ city + ", " + state;
		
		return address;
	}
}
