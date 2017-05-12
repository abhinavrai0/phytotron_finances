package edu.model;

import java.util.Set;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;*/
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Required;
@Entity
//@Table(name = "client_info")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String client_first_name;
//	@Column(nullable = false)
	private String client_last_name;
	@Column(nullable = false, unique=true)
	private String client_email;
	private String dept_name;
	private String client_address;
	
	private String client_phone;
	private String client_status;
	@OneToMany(mappedBy="client")
	private Set<Project> projects;
	public Client(){
	} 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getClient_first_name() {
		return client_first_name;
	}
	@Required
	public void setClient_first_name(String client_first_name) {
		this.client_first_name = client_first_name;
	}
	public String getClient_last_name() {
		return client_last_name;
	}
	@Required
	public void setClient_last_name(String client_last_name) {
		this.client_last_name = client_last_name;
	}
	public String getClient_email() {
		return client_email;
	}
	@Required
	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getClient_address() {
		return client_address;
	}
	public void setClient_address(String client_address) {
		this.client_address = client_address;
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
}
