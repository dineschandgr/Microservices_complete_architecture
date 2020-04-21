package io.microservices.movieinfoservice.resource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.microservices.movieinfoservice.model.Movie;
import io.microservices.movieinfoservice.model.MovieSummary;
import io.microservices.movieinfoservice.repsitory.MovieInfoRepository;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  


@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass()); 
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private MovieInfoRepository movieInfoRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	/*@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId){
		return movieInfoRepository.findById(movieId).orElse(null);
	}*/
		
	@RequestMapping("/{movieId}")
	public Movie getMovieInfoMovieDB(@PathVariable("movieId") String movieId){
		logger.info("{}", "getMovieInfoMovieDB");  
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
		return new Movie(movieId,movieSummary.getTitle(),movieSummary.getOverview());
	}

}
