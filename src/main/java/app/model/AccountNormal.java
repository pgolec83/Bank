package app.model;

public class AccountNormal extends Account{
	
	public AccountNormal(int clientID) {
		super();
                this.accType = "N";
                this.accId = clientID + 1000 +uniqID;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Account ID: ").append(accId).append(" - ").append(accType).append(" Balance: ").append(balance);
		sb.append(" Operations count: ").append(operations).append("\n");
		return sb.toString();
	}
	
}