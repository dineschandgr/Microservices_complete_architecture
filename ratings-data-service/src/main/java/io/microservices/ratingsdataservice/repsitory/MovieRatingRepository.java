package io.microservices.ratingsdataservice.repsitory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.microservices.ratingsdataservice.model.Rating;

public interface MovieRatingRepository extends CrudRepository<Rating, String> {

	public List<Rating> findByUserId(String userId);
	
}
