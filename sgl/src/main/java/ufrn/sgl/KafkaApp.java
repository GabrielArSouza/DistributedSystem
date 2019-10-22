package ufrn.sgl;

import java.util.concurrent.ExecutionException;

import ufrn.sgl.client.kafka.KafkaClient;
import ufrn.sgl.model.Address;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.User;


public class KafkaApp {

	public static void main( String[] args ){

		
		User user = new User("teste5", "teste5", "12312938183-0001/23",
    			new Address("teste5", 5, "teste5", "teste5", "teste5"),
    			"pmj@gmail.com", "teste5"); 
	
		Company company = new Company("teste5", "", "", "", 
    			new Address("teste5", 5, "teste5", "teste5", "teste5"), "teste5", "teste5");
    	
    	Bidding bidding = new Bidding(user, "compra de papel", 78329011);		
    
		KafkaClient client = new KafkaClient();
    	    	
    	try {
    		System.out.println("Registrando Usuário");
    		client.userRegister(user);
    		System.out.println("Usuário Registrado");
			Thread.sleep(3000);
			System.out.println("Consultando Usuário");
			user = client.userRead(user);
			System.out.println("Ok");
			Thread.sleep(3000);
			System.out.println("Realizando login de usuário");
			client.userLogin(user);
			System.out.println("Login OK");
			Thread.sleep(3000);
			System.out.println("Cadastrando Licitação");
			client.registerBidding(bidding, user);
			System.out.println("OK");
			Thread.sleep(3000);	
			System.out.println("Consultando Licitação");
			bidding = client.readBidding(bidding, user);
			System.out.println("OK");
			Thread.sleep(3000);	
			System.out.println("Cadastrando Empresa");
			client.companyRegister(company);
			System.out.println("OK");
			Thread.sleep(3000);
			System.out.println("Consultando Empresa");
			company = client.companyRead(company);
			System.out.println("OK");
			Thread.sleep(3000);	
			System.out.println("Logando Empresa");
			client.companyLogin(company);
			System.out.println("OK");
			Thread.sleep(3000);	
			System.out.println("Encerrando sessão de Empresa");
			client.companyLogout(company);
			System.out.println("OK");
			Thread.sleep(3000);
			System.out.println("Encerrando sessão de usuário");
			client.userLogout(user);
			System.out.println("OK");
				
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
    }
}
