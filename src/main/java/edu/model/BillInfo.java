package edu.model;
import javax.persistence.*;
/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;*/
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;


@Entity
public class BillInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String acc_number;
	private String project_user_name;
	private String project_user_email;
	private String dept_name;
	private String address;
	private String project_name;
	private float bill_rate;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAcc_number() {
		return acc_number;
	}
	public void setAcc_number(String acc_number) {
		this.acc_number = acc_number;
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
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public float getBill_rate() {
		return bill_rate;
	}
	public void setBill_rate(float bill_rate) {
		this.bill_rate = bill_rate;
	}

}
