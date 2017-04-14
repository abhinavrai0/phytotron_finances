package edu.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
public class Payment {
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Address_Gen")
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO,generator = "payment")
//	@TableGenerator( initialValue = 10000, name = "")
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Address_Gen")
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s="id :"+id+", amount: "+amount+", poject id : "+project+", client id : "+client+" & paid date : "+paidDate;
		return super.toString();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	@ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Client client;
	
	@ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name="project_id")
	private Project project;

	private Double amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date paidDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	


}
