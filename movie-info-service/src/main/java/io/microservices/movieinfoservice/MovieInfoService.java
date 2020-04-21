package io.microservices.movieinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableEurekaClient
public class MovieInfoService {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovieInfoService.class, args);
	}
	
	@Bean  
	public Sampler defaultSampler()  
	{  
		return Sampler.ALWAYS_SAMPLE;  
	} 

}
