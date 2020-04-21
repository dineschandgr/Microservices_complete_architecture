
package io.microservices.ratingsdataservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_RATING")
public class Rating {

	@Id
	private String userId;
	private String movieId;
	private int rating;
	
	public Rating(){
		
	}
	
	public Rating(String movieId, int rating) {
		this.movieId = movieId;
		this.rating = rating;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
}
