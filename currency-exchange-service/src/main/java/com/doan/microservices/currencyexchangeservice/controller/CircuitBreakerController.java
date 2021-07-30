package com.doan.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    // default retry times is 3 change to different profiles to toggle retry times
    //@Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")  //define fallback method for retry after max fails
    @CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse") //will break chain and return exception response without retries after circuit breaker conditions are tripped
    //get request will automatically return 404 to test retry features
    //@RateLimiter(name="default") //limit requests in a given window for a specific apis
    @Bulkhead(name="default")
    public String sampleAPI(){
        logger.info("\n\n ==========sample api call received========== \n\n");
        ResponseEntity<String> intentionalFail = new RestTemplate().getForEntity("http://localhost:8100/fail-on-purpose",String.class);
        return intentionalFail.getBody();
    }

    public String hardCodedResponse(Exception ex){
        return "boo on you!";
    }

}
