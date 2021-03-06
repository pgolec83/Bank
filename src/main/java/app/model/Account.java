package app.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public abstract class Account implements Serializable{
    protected static int uniqID = 0; //unique account accId
    protected int accId; //account accId number
    protected String accType; //account Type
    protected double balance; //account balance
    protected int operations; //number of operations assigned to each account

    //parametrized constructor - each account have an accId and type
    //and new accounts are always at balance 0
    public Account() {
        uniqID++; //generates unique accId for each instance of Account
        this.balance = 0;
        this.operations = 0;
    }

    //getters and setters
    public int getId() {
        return accId;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccType() {
        return accType;
    }

    public void setBalance(double balance) {
        this.balance = this.balance + balance;
    }

    public int getOperations() {
        return operations;
    }

    //add operation to account
    public void addOperation() {
        this.operations++;
    }

    //abstract toString method - different account show various information
    @Override
    public abstract String toString();

}
