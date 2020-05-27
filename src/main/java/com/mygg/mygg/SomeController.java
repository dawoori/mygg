package com.mygg.mygg;

import com.mygg.mygg.summoner.Summoner;
import com.mygg.mygg.summoner.SummonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SomeController {

    @Value("${mygg.api-key}")
    private String apiKey;

    @Autowired
    private SummonerRepository summonerRepository;

    @Value("${mygg.appname}")
    private String appName;

    @GetMapping("/all")
    public @ResponseBody
    Iterable<Summoner> getAllUser() {
        return summonerRepository.findAll();
    }

    @GetMapping("/hello")
    public String getAppName() {
        return appName;
    }

    @GetMapping("/ralo")
    public String saveSummoner(RestTemplate restTemplate) {
        String url = "http://ddragon.leagueoflegends.com/cdn/10.10.4/data/ko_KR/champion.json";
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        HttpHeaders headers = new HttpHeaders();

        HttpEntity entity = new HttpEntity(headers);

        Map<String, String> params = new HashMap<String, String>();

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, params);
        String versions = responseEntity.getBody();
        System.out.println(versions);

        return "saved";
    }
}
