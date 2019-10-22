package ufrn.sgl.service.interfaces;

import java.util.List;

import ufrn.sgl.model.Tender;

public interface TenderServiceInterface {

	/**
	 * create a new tender in the database
	 * @param tender the tender to create
	 */
	public long create ( Tender tender );
	
	/**
	 * get a tender of database by a tender
	 * @param tender the tender
	 * @return the tender that match with tender
	 */
	public Tender read (long id);
	
	/**
	 * update the tender in a database
	 * @param tender the tender for update
	 */
	public void update (Tender tender);

	/**
	 * delete the tender of database
	 * @param tender the tender for delete
	 */
	public void delete (long id);
	
	/**
	 * list all tenders
	 * @return a list of tenders
	 */
	public List<Tender> list ();
	
}
