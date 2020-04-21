package io.microservices.moviecatalogservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.microservices.moviecatalogservice.model.CatalogItem;
import io.microservices.moviecatalogservice.model.UserRating;
import io.microservices.moviecatalogservice.services.MovieInfo;
import io.microservices.moviecatalogservice.services.UserRatingsInfo;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingsInfo userRatingsInfo;
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getMovieCatalog(@PathVariable("userId") String userId){
		logger.info("{}", "getMovieCatalog"); 
		UserRating userRating = userRatingsInfo.getUserRating(userId);
		return userRating.getUserRating().stream().map(rating ->  movieInfo.getcatalogueItem(rating))
				.collect(Collectors.toList());
				 
	}


}
