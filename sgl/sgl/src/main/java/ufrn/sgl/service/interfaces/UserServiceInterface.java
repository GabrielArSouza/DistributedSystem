package ufrn.sgl.service.interfaces;

import ufrn.sgl.model.User;

public interface UserServiceInterface {

	/**
	 * create a new user in the database
	 * @param user the user to create
	 */
	public long create ( User user );
	
	/**
	 * get a user of database by a ID
	 * @param user the user to get in database
	 * @return the user that match with user
	 */
	public User read (long id);
	
	/**
	 * get a user of database by a email and password
	 * @param user the user to get in database
	 * @return the user that match with user
	 */
	public User read (User user);
	
	/**
	 * update the user in a database
	 * @param user the user for update
	 */
	public void update (User user);

	/**
	 * delete the user of database
	 * @param user the user for delete
	 */
	public void delete (long id);
	
}
