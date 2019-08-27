package ufrn.sgl.service;

import ufrn.sgl.dao.UserSessionDao;
import ufrn.sgl.dao.interfaces.UserSessionDaoInterface;
import ufrn.sgl.model.UserSession;
import ufrn.sgl.service.interfaces.UserSessionServiceInterface;

public class UserSessionService implements UserSessionServiceInterface {

	UserSessionDaoInterface dao = new UserSessionDao();
	
	@Override
	public long create(UserSession session) {
		return dao.create(session);
	}

	@Override
	public UserSession read(String token) {
		return dao.read(token);
	}

	@Override
	public void update(UserSession session) {
		dao.update(session);
		
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
		
	}

}
