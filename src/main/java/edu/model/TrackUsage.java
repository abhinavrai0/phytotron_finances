package edu.model;
import java.util.Date;

import javax.persistence.*;
/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/
@Entity
public class TrackUsage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
//	private ClientInfo client;
	
	/*@ManyToOne
    @JoinColumn(name = "book_category_id")
	public ClientInfo getClient() {
		return client;
	}
	public void setClient(ClientInfo client) {
		this.client = client;
	}*/
	public String getChambers() {
		return chambers;
	}
	public void setChambers(String chambers) {
		this.chambers = chambers;
	}
	public Double getBillPaidTotal() {
		return billPaidTotal;
	}
	public void setBillPaidTotal(Double billPaidTotal) {
		this.billPaidTotal = billPaidTotal;
	}
	public Double getBillPay() {
		return billPay;
	}
	public void setBillPay(Double billPay) {
		this.billPay = billPay;
	}
	private String project_name;
	private Float rate;
	private String acc_number;
	private String chambers;
	private Integer carts;
	private Date startDate;
	private Date endDate;
	private Date lastBillDate;
	private Double currentBill;
	private Double billPaidTotal;
	private Double billPay;
	private String accountStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	public String getAcc_number() {
		return acc_number;
	}
	public void setAcc_number(String acc_number) {
		this.acc_number = acc_number;
	}
	public Integer getCarts() {
		return carts;
	}
	public void setCarts(Integer carts) {
		this.carts = carts;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getLastBillDate() {
		return lastBillDate;
	}
	public void setLastBillDate(Date lastBillDate) {
		this.lastBillDate = lastBillDate;
	}
	public Double getCurrentBill() {
		return currentBill;
	}
	public void setCurrentBill(Double currentBill) {
		this.currentBill = currentBill;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

}
