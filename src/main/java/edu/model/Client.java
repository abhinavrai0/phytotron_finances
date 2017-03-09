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
	private String project_user_name;
	private String project_user_email;
	private String dept_name;
	private String address;
	
	@OneToMany(mappedBy="client")
	private Set<Project> projects;
	
	public Client(){
	} 
	public Client(String project_user_name, String project_user_email, String dept_name,
			String address) {
		super();
		
		this.project_user_name = project_user_name;
		this.project_user_email = project_user_email;
		this.dept_name = dept_name;
		this.address = address;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getProject_user_name() {
		return project_user_name;
	}
	public void setProject_user_name(String project_user_name) {
		this.project_user_name = project_user_name;
	}
	public String getProject_user_email() {
		return project_user_email;
	}
	public void setProject_user_email(String project_user_email) {
		this.project_user_email = project_user_email;
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
