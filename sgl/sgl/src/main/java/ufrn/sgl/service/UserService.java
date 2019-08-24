package ufrn.sgl.service;

import ufrn.sgl.dao.UserDao;
import ufrn.sgl.dao.interfaces.UserDaoInterface;
import ufrn.sgl.model.User;
import ufrn.sgl.service.interfaces.UserServiceInterface;

public class UserService implements UserServiceInterface {

	private UserDaoInterface dao = new UserDao();
	
	@Override
	public long create(User user) {
		System.out.println("message from user service");
		return dao.create(user);
	}

	@Override
	public User read(long id) {
		//TODO exceptions
		return dao.read(id);
	}

	@Override
	public void update(User user) {
		dao.update(user);
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
	}
	
	
}
