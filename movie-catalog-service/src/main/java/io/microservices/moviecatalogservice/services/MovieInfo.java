package io.microservices.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.microservices.moviecatalogservice.model.CatalogItem;
import io.microservices.moviecatalogservice.model.Movie;
import io.microservices.moviecatalogservice.model.Rating;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemplate;
	
	//Params for Circuit Breaker Pattern
	/*@HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
		commandProperties = {
				@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="100"),
				@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
				@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
				@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")
		}
	)*/
	
	//Params for Bulk Head Pattern
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
			threadPoolKey = "movieInfoPool",
			threadPoolProperties = {
					@HystrixProperty(name="coreSize",value="20"),
					@HystrixProperty(name="maxQueueSize",value="10"),
			}
	)
	
	public CatalogItem getcatalogueItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/"+rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getMovieId(), movie.getMovie_name(), movie.getDescription(), rating.getRating());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("NA", "Movie Name not found", "", rating.getRating());
		
	}
}
