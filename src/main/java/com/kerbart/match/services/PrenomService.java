package com.kerbart.match.services;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.kerbart.match.model.FirstName;
import com.kerbart.match.repositories.FirstNameRepository;

@Repository("prenomService")
@Transactional
public class PrenomService {

	  @PersistenceContext
	  private EntityManager em;
	
	  @Inject
	  FirstNameRepository repo;
	  
	  public FirstName create(FirstName firstName) {
		  em.persist(firstName);
		  return firstName;
	  }
	  
	  public FirstName random() {
		  return repo.randomFirstName(new PageRequest(0, 1)).get(0);
	  }
	  
	  public List<FirstName> randoms() {
		  return repo.randomFirstName(new PageRequest(0, 100));
	  }
	  
	  public FirstName randomGirl() {
		  return repo.randomFirstNameGirl(new PageRequest(0, 1)).get(0);
	  }
	  
	  public FirstName randomBoy() {
		  return repo.randomFirstNameBoy(new PageRequest(0, 1)).get(0);
	  }
	
	  
	  
	  
}
