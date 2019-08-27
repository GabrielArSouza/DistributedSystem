package ufrn.sgl;

import java.net.SocketException;
import java.net.UnknownHostException;

import ufrn.sgl.client.udp.UDPClient;
import ufrn.sgl.model.Address;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/**
    	 * 1 - Login como Usuário
    	 * 2 - Login como Empresa
    	 * 3 - Cadastro de Usuário
    	 * 4 - Cadastro de Empresa
    	 * 5 - (User) CRUD de Licitação 
    	 * 6 - (User) Listar todas as licitações cadastradas
    	 * 7 - (Company) Listar Licitações disponíveis
    	 * 8 - (Company) CRUD para oferta em Licitação
    	 * 9 - Logout user
    	 * 10 - Logout company
    	 */
    	
    	
    	User user = new User("teste5", "teste5", "teste5",
    			new Address("teste5", 5, "teste5", "teste5", "teste5"),
    			"teste5", "teste5");
    	
//    	Company company = new Company("teste5", "", "", "", 
//    			new Address("teste5", 5, "teste5", "teste5", "teste5"), "teste5", "teste5");
    	
    	Bidding bidding = new Bidding(user, "compra de papel", 78329011);
    	
    	try {
			UDPClient client = new UDPClient();
			client.createUser(user);
			Thread.sleep(2000);
			String token = client.userLogin(user);
			Thread.sleep(2000);
			client.createBidding(bidding, user, token);
			Thread.sleep(2000);			
			client.userLogout(token);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }
}
