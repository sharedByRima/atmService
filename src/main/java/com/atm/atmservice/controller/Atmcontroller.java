package com.atm.atmservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/atm")
public class Atmcontroller {

    @Autowired
   private RestTemplate restTemplate;
   private static  String BASE_URL="http://localhost:8081/bank/service/";
   private static final String SERVICEATM="serviceATM";

    @GetMapping("/check/{id}")
    @CircuitBreaker(name=SERVICEATM,fallbackMethod = "fallService")
    public String getServicebank( @PathVariable("id") int id){
        return restTemplate.getForObject(BASE_URL+"check/" +id,String.class);
    }

    @GetMapping("/checkPin/{id}")
    @CircuitBreaker(name=SERVICEATM,fallbackMethod = "fallService")
    public String getPassPin( @PathVariable("id") int id){
        return restTemplate.getForObject(BASE_URL+"checkPin/" +id,String.class);
    }
    @GetMapping("/checkpasssec/{id}")
    public String getPassSec( @PathVariable("id") int id){
        return restTemplate.getForObject(BASE_URL+"checkpass/sec/" +id,String.class);
    }
    @GetMapping("/balance/{id}")
    public String getBalance( @PathVariable("id") int id){
        return restTemplate.getForObject(BASE_URL+"balance/pin/" +id,String.class);
    }

    @GetMapping("/deposit/{amount}")
    public String setTranDeposit( @PathVariable("amount") int amount){
        return restTemplate.getForObject(BASE_URL+"deposit/" +amount,String.class);
    }

    @GetMapping("/withdraw/{amount}")
    public String setTranWithdraw( @PathVariable("amount") int amount) {
        return restTemplate.getForObject(BASE_URL + "withdraw/" + amount, String.class);
    }

    @GetMapping("/exitService")
    public String exitService()
    {
        return restTemplate.getForObject(BASE_URL + "exit/" , String.class);

    }

    public String fallService(Exception e){
        return "service is down";
}

}

