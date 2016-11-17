package com.kerbart.match.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kerbart.match.model.FirstName;

public interface FirstNameRepository extends CrudRepository<FirstName, Long> {
	@Query("SELECT f FROM FirstName f WHERE langage LIKE '%french%' ORDER BY RAND() " )
    List<FirstName> randomFirstName(Pageable pageable);
	
	@Query("SELECT f FROM FirstName f WHERE langage LIKE '%french%' AND genre LIKE '%f%' ORDER BY RAND() " )
    List<FirstName> randomFirstNameGirl(Pageable pageable);
	
	@Query("SELECT f FROM FirstName f WHERE langage LIKE '%french%' AND genre LIKE '%m%' ORDER BY RAND() " )
    List<FirstName> randomFirstNameBoy(Pageable pageable);
}
