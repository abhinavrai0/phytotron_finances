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
@Entity
//@Table(name = "client_info")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String project_client_name;
	private String project_client_email;
	private String dept_name;
	private String address;
	
	@OneToMany(mappedBy="client")
	private Set<Project> projects;
	
	public Client(){
	} 
	public Client(String project_client_name, String project_client_email, String dept_name,
			String address) {
		super();
		
		this.project_client_name = project_client_name;
		this.project_client_email = project_client_email;
		this.dept_name = dept_name;
		this.address = address;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getProject_client_name() {
		return project_client_name;
	}
	public void setProject_client_name(String project_client_name) {
		this.project_client_name = project_client_name;
	}
	public String getProject_client_email() {
		return project_client_email;
	}
	public void setProject_client_email(String project_client_email) {
		this.project_client_email = project_client_email;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
