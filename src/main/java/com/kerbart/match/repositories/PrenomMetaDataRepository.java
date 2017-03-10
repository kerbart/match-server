package com.kerbart.match.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kerbart.match.model.PrenomMetaData;

public interface PrenomMetaDataRepository extends CrudRepository<PrenomMetaData, Long> {

    List<PrenomMetaData> findAll();
}
