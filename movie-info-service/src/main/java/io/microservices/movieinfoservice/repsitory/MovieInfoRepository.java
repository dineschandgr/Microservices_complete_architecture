package io.microservices.movieinfoservice.repsitory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.microservices.movieinfoservice.model.Movie;

public interface MovieInfoRepository extends CrudRepository<Movie, String> {

	public List<Movie> findByMovieId(String movieId);
	
}
