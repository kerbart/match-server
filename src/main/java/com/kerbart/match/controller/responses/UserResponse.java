package com.kerbart.match.controller.responses;

public class UserResponse {

	String token;

	String phoneNumber;

    public UserResponse() {

    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
