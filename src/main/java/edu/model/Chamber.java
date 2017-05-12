package edu.model;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Chamber {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique=true)
	private String chamberName;
//	private String chamberId;
	private String chamberCarts;
	/*@OneToMany(mappedBy="chambers")
	private Set<Project> projects;
	*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChamberName() {
		return chamberName;
	}
	public void setChamberName(String chamberName) {
		this.chamberName = chamberName;
	}
/*	public String getChamberId() {
		return chamberId;
	}
	public void setChamberId(String chamberId) {
		this.chamberId = chamberId;
	}*/
	public String getChamberCarts() {
		return chamberCarts;
	}
	public void setChamberCarts(String chamberCarts) {
		this.chamberCarts = chamberCarts;
	} 

}
