package io.microservices.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.microservices.moviecatalogservice.model.Rating;
import io.microservices.moviecatalogservice.model.UserRating;

@Service
public class UserRatingsInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	//Params for Circuit Breaker Pattern
	/*@HystrixCommand(fallbackMethod = "getFallbackUserRating",
		commandProperties = {
				@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="100"),
				@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
				@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
				@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")
		}
	)*/
	
	//Params for Bulk Head Pattern
		@HystrixCommand(fallbackMethod = "getFallbackUserRating",
				threadPoolKey = "movieInfoPool",
				threadPoolProperties = {
						@HystrixProperty(name="coreSize",value="20"),
						@HystrixProperty(name="maxQueueSize",value="10"),
				}
		)
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://RATINGS-INFO-SERVICE/ratingsdata/users/"+userId, UserRating .class);
	}
	
		
	public UserRating getFallbackUserRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRating(Arrays.asList(
				new Rating("0",0)
			));
		return userRating;
	}

}
