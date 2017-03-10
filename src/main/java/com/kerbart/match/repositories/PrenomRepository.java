package com.kerbart.match.repositories;

import com.kerbart.match.model.Prenom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrenomRepository extends CrudRepository<Prenom, Long> {

    List<Prenom> findByPrenom(String prenom);

    List<Prenom> findByPrenomSansAccent(String prenomSansAccent);

    List<Prenom> findByPrenomSansAccentIgnoreCaseContaining(String prenomSansAccent);

    List<Prenom> findAll();

    Prenom findByToken(String token);



}
