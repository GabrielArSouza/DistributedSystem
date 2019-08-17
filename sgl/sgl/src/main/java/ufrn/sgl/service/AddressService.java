package ufrn.sgl.service;

import ufrn.sgl.dao.AddressDao;
import ufrn.sgl.dao.interfaces.AddressDaoInterface;
import ufrn.sgl.model.Address;
import ufrn.sgl.service.interfaces.AddressServiceInterface;

public class AddressService implements AddressServiceInterface {

	private AddressDaoInterface dao = new AddressDao();
	
	@Override
	public void create(Address address) {
		dao.create(address);
	}

	@Override
	public Address read(long id) {
		return dao.read(id);
	}

	@Override
	public void update(Address address) {
		dao.update(address);
	}

	@Override
	public void delete(Address address) {
		dao.delete(address);
	}

	
	
}
