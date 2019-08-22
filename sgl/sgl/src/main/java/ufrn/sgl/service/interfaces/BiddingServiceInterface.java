package ufrn.sgl.service.interfaces;

import ufrn.sgl.model.Bidding;

public interface BiddingServiceInterface {

	/**
	 * create a new bidding in the database
	 * @param bidding the bidding to create
	 */
	public void create ( Bidding bidding );
	
	/**
	 * get a bidding of database by a ID
	 * @param id the Bidding ID
	 * @return the bidding that match with ID
	 */
	public Bidding read (long id);
	
	/**
	 * update the bidding in a database
	 * @param bidding the bidding for update
	 */
	public void update (Bidding bidding);

	/**
	 * delete the bidding of database
	 * @param bidding the bidding for delete
	 */
	public void delete (Bidding bidding);
	
}
