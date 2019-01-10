package app.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Operation implements Serializable {
	private int uniqID = 0; //unique operation ID for each instance of Operation
	private int oppId; //operation ID
	private String oppDate; //date and time of operation
	private int oppAccFrom; //source of operation
	private int oppAccTo; //destination of operation
	private float oppValue; //value of operation
	private float oppCost; //cost of operation
	private String oppTitle; //title of operation
	
	public Operation(int accId, int accFrom, int accTo, float value, String title) {
		uniqID++;
		this.oppId = accId + uniqID;
		Date createdDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String dateString = dateFormat.format(createdDate);
                this.oppDate = dateString;
                this.oppAccFrom = accFrom;
                this.oppAccTo = accTo;
                this.oppValue = value;
                this.oppCost = (float) (0.01 * value);
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

    public float getOppValue() {
        return oppValue;
    }

    public float getOppCost() {
        return oppCost;
    }

    public String getOppTitle() {
        return oppTitle;
    }

        
}