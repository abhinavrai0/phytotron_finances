package edu.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pinakin Abhyankar on 6/27/2017.
 */
@Entity
@Table(name = "invoice_report_view")
public class InvoiceReportView {

    @Id
    @Column(name = "ID")
    private Long id; // The row number!

    private Long client_id;

    private String client_address;

    private String client_email;

    private String client_first_name;

    private String client_last_name;

    private String client_phone;

    private String client_status;

    private String dept_name;

    private String acc_number;

    private Integer carts;

    @Type(type="date")
    private Date end_date;

    private String project_status;

    private String project_title;

    private String project_id;

    @Type(type="date")
    private Date start_date;

    private String rate_type;

    private Double rate;


    private String invoice_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date generation_date;

    private Double prev_balance;

    private Double current_bill;

    private Double total_due;

    public InvoiceReportView() {
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public String getClient_email() {
        return client_email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getClient_first_name() {
        return client_first_name;
    }

    public void setClient_first_name(String client_first_name) {
        this.client_first_name = client_first_name;
    }

    public String getClient_last_name() {
        return client_last_name;
    }

    public void setClient_last_name(String client_last_name) {
        this.client_last_name = client_last_name;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getClient_status() {
        return client_status;
    }

    public void setClient_status(String client_status) {
        this.client_status = client_status;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
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

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }

    public String getProject_title() {
        return project_title;
    }

    public void setProject_title(String project_title) {
        this.project_title = project_title;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getRate_type() {
        return rate_type;
    }

    public void setRate_type(String rate_type) {
        this.rate_type = rate_type;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Date getGeneration_date() {
        return generation_date;
    }

    public void setGeneration_date(Date generation_date) {
        this.generation_date = generation_date;
    }

    public Double getPrev_balance() {
        return prev_balance;
    }

    public void setPrev_balance(Double prev_balance) {
        this.prev_balance = prev_balance;
    }

    public Double getCurrent_bill() {
        return current_bill;
    }

    public void setCurrent_bill(Double current_bill) {
        this.current_bill = current_bill;
    }

    public Double getTotal_due() {
        return total_due;
    }

    public void setTotal_due(Double total_due) {
        this.total_due = total_due;
    }
}