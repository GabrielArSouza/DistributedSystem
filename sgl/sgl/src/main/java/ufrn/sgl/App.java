package ufrn.sgl;

import java.util.List;

import ufrn.sgl.dao.AddressDao;
import ufrn.sgl.dao.UserDao;
import ufrn.sgl.dao.interfaces.AddressDaoInterface;
import ufrn.sgl.dao.interfaces.UserDaoInterface;
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
  
        Address address1 = new Address("av principal", 767, "teste", "Natal", "RN");
        Address address2 = new Address("av da rosas", 12325, "teste", "Jucurutu", "RN");
        Address address3 = new Address("av jucurutu ", 1122, "teste", "Currais Novos", "RN");
        
        User user1 = new User("teste1", "teste1",  "teste1", address1, "teste1", "teste1");
        User user2 = new User("teste2", "teste2",  "teste2", address2, "teste2", "teste2");
        User user3 = new User("teste3", "teste3",  "teste3", address3, "teste3", "teste3");
      
        
        UserDaoInterface daoUser = new UserDao();
        daoUser.create(user1);
        daoUser.create(user2);
        daoUser.create(user3);
        
        List<User> listUser = daoUser.list();
        for (User u : listUser) System.out.println(u.toString());
        
        daoUser.delete(3);
        user2.setEmail("email atualizado");
        user2.setId(2);
        daoUser.update(user2);
        System.out.println(daoUser.read(2).toString());
        
        listUser = daoUser.list();
        for (User u : listUser) System.out.println(u.toString());
        
        //System.out.println(reply.toString());
        return;
       
    }
}
