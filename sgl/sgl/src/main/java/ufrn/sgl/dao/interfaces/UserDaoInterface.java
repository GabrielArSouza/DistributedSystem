package ufrn.sgl.dao.interfaces;

import java.util.List;

import ufrn.sgl.model.User;

public interface UserDaoInterface {

	/**
	 * create a new user in the database
	 * @param user the user to create
	 */
	public long create ( User user );
	
	/**
	 * get a user of database by a user
	 * @param user the user
	 * @return the user that match with user
	 */
	public User read (long id);
	
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
	
	/**
	 * list all users
	 * @return a list of users
	 */
	public List<User> list ();
	
}
