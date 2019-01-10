package app.model;

public class AccountCredit extends Account{
    private double creditLimit;

    public AccountCredit(int clientID, double creditLimit) {
	super();
	this.creditLimit = creditLimit;
        this.accType = "C";
        this.accId = clientID + 3000 + uniqID;
    }

    public double getCreditLimit() {
        return creditLimit;
    }
    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("Account ID: ").append(accId).append(" - ").append(accType).append(" Balance: ").append(balance);
	sb.append(" Credit Limit: ").append(creditLimit).append(" Operations count: ").append(operations.size()).append("\n");
	return sb.toString();
    }
}