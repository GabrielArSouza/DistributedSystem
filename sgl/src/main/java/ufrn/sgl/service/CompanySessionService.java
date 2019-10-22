package ufrn.sgl.service;

import ufrn.sgl.dao.CompanySessionDao;
import ufrn.sgl.dao.interfaces.CompanySessionDaoInterface;
import ufrn.sgl.model.CompanySession;
import ufrn.sgl.service.interfaces.CompanySessionServiceInterface;

public class CompanySessionService implements CompanySessionServiceInterface {

	CompanySessionDaoInterface dao = new CompanySessionDao();
	
	@Override
	public long create(CompanySession session) {
		return dao.create(session);
	}

	@Override
	public CompanySession read(String token) {
		return dao.read(token);
	}

	@Override
	public void update(CompanySession session) {
		dao.update(session);	
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
	}

}
