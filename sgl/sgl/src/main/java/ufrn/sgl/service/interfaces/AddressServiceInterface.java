package ufrn.sgl.service.interfaces;

import ufrn.sgl.model.Address;

public interface AddressServiceInterface {

	/**
	 * create a new address in the database
	 * @param address the address to create
	 */
	public void create ( Address address );
	
	/**
	 * get a address of database by a ID
	 * @param id the Address ID
	 * @return the address that match with ID
	 */
	public Address read (long id);
	
	/**
	 * update the address in a database
	 * @param address the address for update
	 */
	public void update (Address address);

	/**
	 * delete the address of database
	 * @param address the address for delete
	 */
	public void delete (Address address);
	
}
