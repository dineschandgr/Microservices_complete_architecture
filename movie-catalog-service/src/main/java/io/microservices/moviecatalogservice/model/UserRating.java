package io.microservices.moviecatalogservice.model;

import java.util.List;

public class UserRating {

	private List<Rating> userRating;
	
	private String userId;

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
