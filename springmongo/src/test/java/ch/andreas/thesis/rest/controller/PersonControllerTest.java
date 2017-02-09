package ch.andreas.thesis.rest.controller;


import ch.andreas.thesis.rest.json.PersonJson;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by heuby on 28.12.16.
 */
public class PersonControllerTest {


    private RestTemplate restTemplate = new RestTemplate();

    @Test
    //@Ignore("for int testing.")
    public void createPerson() throws RestClientException{
        PersonJson personJson = new PersonJson("Andreas", "Heubeck", "Ackerstrasse",
                44, "Dübendorf", 8005, "Schweiz");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("content-type", "application/json");
        HttpEntity request = new HttpEntity(personJson,requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange("http://spring-mongo-meon-cd-dev.osebase01.sn.six-group.net/createPerson", HttpMethod.POST, request, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}