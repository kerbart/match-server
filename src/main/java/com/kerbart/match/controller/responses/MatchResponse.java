package com.kerbart.match.controller.responses;

import java.util.List;

import com.kerbart.match.model.PrenomMetaData;

public class MatchResponse {

	PrenomMetaData firstName;
	
	List<PrenomMetaData> firstNames;
	
    public MatchResponse() {

    }

	public PrenomMetaData getFirstName() {
		return firstName;
	}

	public void setFirstName(PrenomMetaData firstName) {
		this.firstName = firstName;
	}

	public List<PrenomMetaData> getFirstNames() {
		return firstNames;
	}

	public void setFirstNames(List<PrenomMetaData> firstNames) {
		this.firstNames = firstNames;
	}
	
	
}
