package app.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Operation implements Serializable {
	private static int uniqOppID = 0; //unique operation ID for each instance of Operation
	private int oppId; //operation ID
        private int userOpp; //user making operation
	private String oppDate; //date and time of operation
	private int oppAccFrom; //source of operation
	private int oppAccTo; //destination of operation
	private double oppValue; //value of operation
	private String oppTitle; //title of operation
	
	public Operation(int user, int accFrom, int accTo, double value, String title) {
		uniqOppID++;
		this.oppId = user + uniqOppID;
                this.userOpp = user;
		Date createdDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String dateString = dateFormat.format(createdDate);
                this.oppDate = dateString;
                this.oppAccFrom = accFrom;
                this.oppAccTo = accTo;
                this.oppValue = value;
                this.oppTitle = title; 
	}    
        
    public int getOppId() {
        return oppId;
    }

    public String getOppDate() {
        return oppDate;
    }

    public int getOppAccFrom() {
        return oppAccFrom;
    }

    public int getOppAccTo() {
        return oppAccTo;
    }

    public double getOppValue() {
        return oppValue;
    }

    public String getOppTitle() {
        return oppTitle;
    }

    public int getUserOpp() {
        return userOpp;
    }

    @Override
    public String toString() {
        return "Operation{oppId=" + oppId + ", userOpp=" + userOpp + ", oppDate=" + oppDate + ", oppAccFrom=" + oppAccFrom + ", oppAccTo=" + oppAccTo + ", oppValue=" + oppValue + ", oppTitle=" + oppTitle + "}\n";
    }

    
        
}