package ufrn.sgl.service.interfaces;

import ufrn.sgl.model.Company;

public interface CompanyServiceInterface {

	/**
	 * create a new company in the database
	 * @param company the company to create
	 */
	public long create ( Company company );
	
	/**
	 * get a company of database by a ID
	 * @param id the Company ID
	 * @return the company that match with ID
	 */
	public Company read (long id);
	
	/**
	 * update the company in a database
	 * @param company the company for update
	 */
	public void update (Company company);

	/**
	 * delete the company of database
	 * @param company the company for delete
	 */
	public void delete (long id);
	
	public Company read(Company company);
	
}
