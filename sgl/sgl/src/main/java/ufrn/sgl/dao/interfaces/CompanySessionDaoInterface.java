package ufrn.sgl.dao.interfaces;

import ufrn.sgl.model.CompanySession;

public interface CompanySessionDaoInterface {

	/**
	 * create a new company session in the database
	 * @param company the company to create
	 */
	public long create ( CompanySession companySession );
	
	/**
	 * get a company session of database by a ID
	 * @param id the Company session ID
	 * @return the company that match with ID
	 */
	public CompanySession read (CompanySession session);
	
	/**
	 * update the company session in a database
	 * @param company the company for update
	 */
	public void update (CompanySession companySession);

	/**
	 * delete the company session of database
	 * @param company the company for delete
	 */
	public void delete (long id);
	
}
