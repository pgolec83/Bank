package app.model;

public class AccountSavings extends Account{
    private double interestRate;
	
    public AccountSavings(int clientID, double interestRate) {
	super();
        this.accType = "S";
        this.accId = clientID + 6000 + uniqID;
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }
    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("Account ID: ").append(accId).append(" - ").append(accType).append(" Balance: ").append(balance);
	sb.append(" Interest Rate: ").append(interestRate).append(" Operations count: ").append(operations).append("\n");
	return sb.toString();
    }
}