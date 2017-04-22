package edu.model;
import java.util.Date;

import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Type;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Client client;
	@ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name="rate_id")
	private Rate rateValue;
	public Project(){
	}
	
	/*@ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name="crop_id")
	private Crop crop;*/
	@Column(nullable = false)
	private String project_id;
	@Column(nullable = false)
	private String project_Title;
//	private Float rate;
	private String acc_number;
//	private String chambers;
	private Integer carts;
	@Type(type="date")
	private Date startDate;
	@Type(type="date")
	private Date endDate;
	@Type(type="date")
	private Date lastBillDate;//Last Invoice Date
	@Type(type="date")
	private Date lastBillPaidDate;//Last Date Paid
	private Double currentBill=0.0;
	private Double billPaidTotal=0.0;//Total Paid
	private String accountStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProject_Title() {
		return project_Title;
	}
	public void setProject_Title(String project_Title) {
		this.project_Title = project_Title;
	}
/*	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}*/
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
	public Date getLastBillPaidDate() {
		return lastBillPaidDate;
	}
	public void setLastBillPaidDate(Date lastBillPaidDate) {
		this.lastBillPaidDate = lastBillPaidDate;
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
/*	public String getChambers() {
		return chambers;
	}
	public void setChambers(String chambers) {
		this.chambers = chambers;
	}*/
	public Double getBillPaidTotal() {
		return billPaidTotal;
	}
	public void setBillPaidTotal(Double billPaidTotal) {
		this.billPaidTotal = billPaidTotal;
	}
	
/*	
    @ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name="chambers_id")
	private Chamber chambers;
	public Chamber getChambers() {
		return chambers;
	}
	public void setChambers(Chamber chambers) {
		this.chambers = chambers;
	}*/
	
	
	///working with minor issues like null columns
//	@ManyToMany//(cascade = CascadeType.ALL)
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "project_chamber", joinColumns = { @JoinColumn(name = "project_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "chamber_id", referencedColumnName = "id") })
	private Set<Chamber> chambers=new HashSet<Chamber>(0);
	public Set<Chamber> getChambers(){
		return this.chambers;
	}
	public void setChambers(Set<Chamber> chambers){
		this.chambers=chambers;
	}
	public Rate getRateValue() {
		return rateValue;
	}
	public void setRateValue(Rate rateValue) {
		this.rateValue = rateValue;
	}
	
	
	
	
	
	

	/*public Double getBillPay() {
		return billPay;
	}
	public void setBillPay(Double billPay) {
		this.billPay = billPay;
	}*/
	/*private Crop[] crops;
	public Crop[] getCrops() {
		return crops;
	}
	public void setCrops(Crop[] crops) {
		this.crops = crops;
	}*/
	

}
