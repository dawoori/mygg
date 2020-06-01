package com.mygg.mygg.entry;

import com.mygg.mygg.summoner.Summoner;
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
public class EntryController {
    @Value(value = "${mygg.api-key}")
    protected String apiKey;

    private RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl = "https://kr.api.riotgames.com";

    @GetMapping("/entry/{summonerId}")
    public Entry[] getEntry(@PathVariable String summonerId) {
        String url = baseUrl + "/lol/league/v4/entries/by-summoner/" + summonerId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Entry[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Entry[].class);
            Entry[] entries = responseEntity.getBody();

            return entries;
        } catch (final HttpClientErrorException e) {

            return new Entry[]{new Entry()};
        }
    }

    @GetMapping("/tier/{summonerName}")
    public String showMeTheTier(@PathVariable String summonerName) {
        String summonerUrl = baseUrl + "/lol/summoner/v4/summoners/by-name/" + summonerName;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String summonerId;
        try {
            ResponseEntity<Summoner> responseEntity = restTemplate.exchange(summonerUrl, HttpMethod.GET, entity, Summoner.class);
            summonerId = responseEntity.getBody().getId();


        } catch (final HttpClientErrorException e) {
            return "f1";
        }

        String entryUrl = baseUrl + "/lol/league/v4/entries/by-summoner/" + summonerId;
        Entry[] entries;
        try {
            ResponseEntity<Entry[]> responseEntity = restTemplate.exchange(entryUrl, HttpMethod.GET, entity, Entry[].class);
            entries = responseEntity.getBody();

        } catch (final HttpClientErrorException e) {
            return "f2";
        }
        String tier = entries[0].getTier().substring(0,1) + entries[0].getTier().substring(1).toLowerCase();

        String emblem = "<img src=\"/datacdn/rank/emblems/Emblem_" + tier + ".png\">";

        return emblem;
    }
}
