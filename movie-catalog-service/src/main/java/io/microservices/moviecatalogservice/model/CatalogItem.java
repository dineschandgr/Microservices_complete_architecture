package io.microservices.moviecatalogservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie_catalog")
public class CatalogItem {
	
	@Id
	private String movieId;
	private String movieTitle;
	private String movie_description;
	private int rating;
	
	public CatalogItem() {
		
	}
	
	public CatalogItem(String movieId, String movieTitle,String description,int rating) {
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movie_description = description;
		this.rating = rating;
	}
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieID(String movieID) {
		this.movieId = movieID;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getDescription() {
		return movie_description;
	}
	public void setDescription(String description) {
		this.movie_description = description;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	

}
