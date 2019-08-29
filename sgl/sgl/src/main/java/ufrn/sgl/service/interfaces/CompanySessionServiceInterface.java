package ufrn.sgl.service.interfaces;

import ufrn.sgl.model.CompanySession;

public interface CompanySessionServiceInterface {

	/**
	 * create a new company session in the database
	 * @param company the company to create
	 */
	public long create ( CompanySession session );
	
	/**
	 * get a company session of database by a ID
	 * @param id the Company session ID
	 * @return the company that match with ID
	 */
	public CompanySession read (String token);
	
	/**
	 * update the company session in a database
	 * @param company the company for update
	 */
	public void update (CompanySession session);

	/**
	 * delete the company session of database
	 * @param company the company for delete
	 */
	public void delete (long id);
	
}
