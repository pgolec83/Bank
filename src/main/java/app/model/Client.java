package app.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Client implements Serializable {
	private static int uniqID=0;
	protected int clientId;
	private String name; //client name
	private String created; //date of client created
	private List<Account> accounts = new LinkedList<Account>(); //client's account list
	
	public Client(String name) {
		uniqID++;
		this.clientId = (1000000 + (uniqID*10000));
		this.name = name;
		Date createdDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String dateString = dateFormat.format(createdDate);
		this.created = dateString;
	}

	public String getName() {
		return name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreated() {
		return created;
	}
	
	public int getClientId() {
		return clientId;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	//new account for client
	public void newAccount(Account acc){
		accounts.add(acc);
	}
	
        
        
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID-").append(clientId).append(" Client: ").append(name).append("    [created: ").append(created).append("]\n");
		for(Account a:accounts) {
			sb.append(a);
		}
		return sb.toString();	
	}
}