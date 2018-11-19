package app.model;

public class AccountSavings extends Account{
	private double interestRate;
	
	public AccountSavings(int id, double interestRate) {
		super(id, "Savings Account");
		this.interestRate = interestRate;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Account ID: ").append(accId).append(" - ").append(accType).append(" Balance: ").append(balance);
		sb.append(" Interest Rate: ").append(interestRate).append(" Operations count: ").append(operations.size()).append("\n");
		return sb.toString();
	}
}