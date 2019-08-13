package ufrn.sgl;

import ufrn.sgl.dao.AddressDao;
import ufrn.sgl.dao.UserDao;
import ufrn.sgl.model.Address;
import ufrn.sgl.model.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        AddressDao addressDao = new AddressDao();
//        Address address = new Address("merda", 31279, "ave", "N", "RJ");
//        addressDao.save(address);
        //addressDao.insert();
        
        
        User user = new User("Prefeitura Municipal de Jucurutu", "PMJuc", "1231383/0001-23", 
				 new Address("av José de Alencar", 8392, "Candelária", "Natal", "RN"), 
				 "pmnat@gmail.com");
        UserDao userDao = new UserDao();
        userDao.save(user);
        //userDao.insert();
        		
       
    }
}
