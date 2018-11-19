package app.model;

import java.io.Serializable;

public class Operation implements Serializable {
	private int uniqID = 0; //unique operation ID for each instance of Operation
	private int oppId; //operation ID
	private double accId; //which account operation refers to
	private String oppDate; //date and time of operation
	private String oppFrom; //source of operation
	private String oppTo; //destination of operation
	private double oppValue; //value of operation
	private double oppCost; //cost of operation
	private String oppTitle; //title of operation
	
	public Operation(int oppId, double accId, String oppDate, String oppFrom, String oppTo, double oppValue,
			double oppCost, String opptitle) {
		uniqID++;
		this.oppId = oppId + uniqID;
		this.accId = accId;
		this.oppDate = oppDate;
		this.oppFrom = oppFrom;
		this.oppTo = oppTo;
		this.oppValue = oppValue;
		this.oppCost = oppCost;
		this.oppTitle = opptitle;
	}

	public int getOppId() {
		return oppId;
	}

	public double getAccId() {
		return accId;
	}

	public String getOppDate() {
		return oppDate;
	}

	public String getOppFrom() {
		return oppFrom;
	}

	public String getOppTo() {
		return oppTo;
	}

	public double getOppValue() {
		return oppValue;
	}

	public double getOppCost() {
		return oppCost;
	}

	public String getOpptitle() {
		return oppTitle;
	}
}