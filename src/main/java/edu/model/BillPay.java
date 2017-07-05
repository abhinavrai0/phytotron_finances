package edu.model;

import java.util.Date;

import org.hibernate.annotations.Type;

public class BillPay {
//	private Long id;
//	private Date date;
	@Type(type="date")
	private Date date;
/*	public Long getClient_id() {
		return id;
	}
	public void setClient_id(Long id) {
		this.id = id;
	}*/
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
