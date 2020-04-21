package io.microservices.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;
import io.microservices.zuul.filter.ZuulLoggingErrorFilter;
import io.microservices.zuul.filter.ZuulLoggingFilter;
import io.microservices.zuul.filter.ZuulLoggingPostFilter;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  


@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class NetflixZuulApiGatewayServerApplication {
	

	public static void main(String[] args) {
		
		SpringApplication.run(NetflixZuulApiGatewayServerApplication.class, args);
	}
	
	@Bean
	public ZuulLoggingFilter preFilter() {
	    return new ZuulLoggingFilter();
	}
	
	@Bean
	public ZuulLoggingPostFilter postFilter() {
	    return new ZuulLoggingPostFilter();
	}
	
	@Bean
	public ZuulLoggingErrorFilter errorFilter() {
	    return new ZuulLoggingErrorFilter();
	}
	
	@Bean  
	public Sampler defaultSampler()  
	{  
		return Sampler.ALWAYS_SAMPLE;  
	} 
	

}
