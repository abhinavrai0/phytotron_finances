package edu.model;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Required;
@Entity
public class Crop {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String cropCommonName;
	private String cropScientificName;
	/*@OneToMany(mappedBy="crop")
	private Set<Project> projects;*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCropCommonName() {
		return cropCommonName;
	}
	@Required
	public void setCropCommonName(String cropCommonName) {
		this.cropCommonName = cropCommonName;
	}
	public String getCropScientificName() {
		return cropScientificName;
	}
	@Required
	public void setCropScientificName(String cropScientificName) {
		this.cropScientificName = cropScientificName;
	}
}
