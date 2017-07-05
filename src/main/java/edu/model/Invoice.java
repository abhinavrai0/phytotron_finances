package edu.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pinakin Abhyankar on 6/23/2017.
 * This class represents the metadata for the invoices generated for the clients.
 */
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long projectId;

    @Column(nullable = false)
    private String invoice_id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "generation_date")
    private Date generationDate;

    private Double prev_balance;

    private Double current_bill;

    private Double total_due;

    public Invoice(){

    }

    public Invoice(Long id, String invoice_id, Project project) {
        this.setId(id);
        this.setInvoice_id(invoice_id);
        this.setCurrent_bill(100.00);
        this.setPrev_balance(50.00);
        this.setGenerationDate(new Date());
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
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

    public Double getTotoal_due() {
        return total_due;
    }

    public void setTotoal_due(Double totoal_due) {
        this.total_due = totoal_due;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", invoice_id='" + invoice_id + '\'' +
                ", generationDate=" + generationDate +
                ", prev_balance=" + prev_balance +
                ", current_bill=" + current_bill +
                ", totoal_due=" + total_due +
                '}';
    }
}
