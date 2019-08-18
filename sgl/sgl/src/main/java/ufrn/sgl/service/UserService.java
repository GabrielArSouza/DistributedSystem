package ufrn.sgl.service;

import ufrn.sgl.dao.UserDao;
import ufrn.sgl.dao.interfaces.UserDaoInterface;
import ufrn.sgl.model.User;
import ufrn.sgl.service.interfaces.UserServiceInterface;

public class UserService implements UserServiceInterface {

	private UserDaoInterface dao = new UserDao();
	
	@Override
	public void create(User user) {
		dao.create(user);
	}

	@Override
	public User read(User user) {
		//TODO exceptions
		return dao.read(user);
	}

	@Override
	public void update(User user) {
		dao.update(user);
	}

	@Override
	public void delete(User user) {
		dao.delete(user);
	}
	
	
}
