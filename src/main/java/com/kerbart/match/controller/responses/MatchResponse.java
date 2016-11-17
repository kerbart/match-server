package com.kerbart.match.controller.responses;

import java.util.List;

import com.kerbart.match.model.FirstName;

public class MatchResponse {

	FirstName firstName;
	
	List<FirstName> firstNames;
	
    public MatchResponse() {

    }

	public FirstName getFirstName() {
		return firstName;
	}

	public void setFirstName(FirstName firstName) {
		this.firstName = firstName;
	}

	public List<FirstName> getFirstNames() {
		return firstNames;
	}

	public void setFirstNames(List<FirstName> firstNames) {
		this.firstNames = firstNames;
	}
	
	
}
