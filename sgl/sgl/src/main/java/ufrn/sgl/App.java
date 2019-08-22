package ufrn.sgl;

import ufrn.sgl.dao.AddressDao;
import ufrn.sgl.dao.UserDao;
import ufrn.sgl.dao.interfaces.AddressDaoInterface;
import ufrn.sgl.model.Address;
import ufrn.sgl.model.User;
import ufrn.sgl.util.TokenGenerator;

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
//        addressDao.create(address);
 
//        User user = new User("Prefeitura Municipal de Jucurutu", "PMJuc", "1231383/0001-23", 
//				 new Address("av José de Alencar", 8392, "Candelária", "Natal", "RN"), 
//				 "pmnat@gmail.com", "123");
//        UserDao userDao = new UserDao();
//        userDao.create(user);
        
        //Address address = new Address (1);
        AddressDaoInterface dao = new AddressDao();
        Address reply = dao.read(19);
        System.out.println(reply.toString());
        
       
    }
}
