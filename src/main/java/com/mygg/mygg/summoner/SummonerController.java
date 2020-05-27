package com.mygg.mygg.summoner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class SummonerController {
    @Value(value = "${mygg.api-key}")
    protected String apiKey;

    @Autowired
    private SummonerRepository summonerRepository;

    private RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl = "https://kr.api.riotgames.com/lol/";

    @GetMapping("/summoner/{name}")
    public Summoner getSummoner(@PathVariable String name) {
        String url = baseUrl + "summoner/v4/summoners/by-name/" + name + "?api_key=" + apiKey;
        try {
            ResponseEntity<Summoner> responseEntity = restTemplate.getForEntity(url, Summoner.class);
            System.out.println(responseEntity.getStatusCode());

            return responseEntity.getBody();
        } catch (final HttpClientErrorException e) {
            return summonerRepository.findByName("랄투브");
        }
    }

    @PostMapping("/summoner/{name}")
    public Summoner getAndPostSummoner(@PathVariable String name) {
        String url = baseUrl + "summoner/v4/summoners/by-name/" + name + "?api_key=" + apiKey;
        try {
            ResponseEntity<Summoner> responseEntity = restTemplate.getForEntity(url, Summoner.class);
            Summoner summoner = responseEntity.getBody();

            assert summoner != null;
            boolean isExist = summonerRepository.existsById(summoner.getId());

            if (!isExist) summonerRepository.save(summoner);

            return summoner;
        } catch (final HttpClientErrorException e) {
            return summonerRepository.findByName("랄투브");
        }

    }
}
