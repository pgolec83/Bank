package app;

import java.util.LinkedList;
import java.util.List;

import app.model.*;

public class Application {
	
	public static void main(String [] args){
		List<Client> clients = new LinkedList<Client>();
		clients.add(new Client("Pawel"));
		clients.add(new Client("Agnieszka"));
		clients.add(new Client("Wieslaw"));
		System.out.println(clients);
		
		for(Client c:clients){
			if (c.getClientId() == 101){
				c.newAccount(new AccountNormal(((c.getClientId()*1000)+100)));
				c.newAccount(new AccountNormal(100));
				c.newAccount(new AccountNormal(100));
				c.newAccount(new AccountCredit(200, 400000));
				c.newAccount(new AccountSavings(300, 0.80));
				
			}
		}
		System.out.println(clients);
	}
}
