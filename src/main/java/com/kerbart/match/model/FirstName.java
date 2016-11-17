package com.kerbart.match.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FirstName {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "firstname_id")
	Long id;

	@Column
	String prenom;	
	
	@Column
	String genre;
	
	@Column
	String langage;	
	
	@Column
	Double frequence;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLangage() {
		return langage;
	}

	public void setLangage(String langage) {
		this.langage = langage;
	}

	public Double getFrequence() {
		return frequence;
	}

	public void setFrequence(Double frequence) {
		this.frequence = frequence;
	}
}
