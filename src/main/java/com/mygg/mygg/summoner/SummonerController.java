package com.mygg.mygg.summoner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    private final String baseUrl = "https://kr.api.riotgames.com";

    @GetMapping("/summoner/{name}")
    public Summoner getSummoner(@PathVariable String name) {
        String url = baseUrl + "/lol/summoner/v4/summoners/by-name/" + name;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Summoner> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Summoner.class);

            return responseEntity.getBody();
        } catch (final HttpClientErrorException e) {
            return summonerRepository.findByName("랄투브");
        }
    }

    //TODO: 예외시 404 NOT FOUND 보내기
    @PostMapping("/summoner/{name}")
    public Summoner getAndPostSummoner(@PathVariable String name) {
        String url = baseUrl + "/lol/summoner/v4/summoners/by-name/" + name;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Summoner> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Summoner.class);
            Summoner summoner = responseEntity.getBody();

            assert summoner != null;
            boolean isExist = summonerRepository.existsById(summoner.getId());

            summonerRepository.save(summoner);
            return summoner;
        } catch (final HttpClientErrorException e) {
            return summonerRepository.findByName("랄투브");
        }

    }
}
