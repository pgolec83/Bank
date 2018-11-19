package app.model;

public class AccountNormal extends Account{
	
	public AccountNormal (int id) {
		super(id,"Standard Account");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Account ID: ").append(accId).append(" - ").append(accType).append(" Balance: ").append(balance);
		sb.append(" Operations count: ").append(operations.size()).append("\n");
		return sb.toString();
	}
	
}