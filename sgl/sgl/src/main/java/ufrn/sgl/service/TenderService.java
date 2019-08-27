package ufrn.sgl.service;

import java.util.List;

import ufrn.sgl.dao.TenderDao;
import ufrn.sgl.dao.interfaces.TenderDaoInterface;
import ufrn.sgl.model.Tender;
import ufrn.sgl.service.interfaces.TenderServiceInterface;

public class TenderService implements TenderServiceInterface{

	private static final TenderDaoInterface dao = new TenderDao();
	
	@Override
	public long create(Tender tender) {
		return dao.create(tender);
	}

	@Override
	public Tender read(long id) {
		return dao.read(id);
	}

	@Override
	public void update(Tender tender) {
		dao.update(tender);
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
	}

	@Override
	public List<Tender> list() {
		return dao.list();
	}

	
	
}
