package com.mygg.mygg.spectator;

import com.mygg.mygg.summoner.Summoner;
import com.mygg.mygg.summoner.SummonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class SpectatorController {
    @Value(value = "${mygg.api-key}")
    protected String apiKey;

    @Autowired
    private SummonerRepository summonerRepository;

    private RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl = "https://kr.api.riotgames.com";

    @GetMapping("/spectator/{name}")
    public Object getSpectator(@PathVariable String name) {
        Summoner s = summonerRepository.findByName(name);

        if(s == null) {
            return "";
        }

        String id = s.getId();

        String url = baseUrl + "/lol/spectator/v4/active-games/by-summoner/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Spectator> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Spectator.class);

            return responseEntity.getBody();
        } catch (final HttpClientErrorException e) {
            return e.getStatusCode().toString();
        }
    }
}
