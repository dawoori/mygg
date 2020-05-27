package com.mygg.mygg;

import com.mygg.mygg.summoner.Summoner;
import com.mygg.mygg.summoner.SummonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @PostMapping("/ralo")
    public String saveSummoner(RestTemplate restTemplate) {
        String name = "랄투브";
        Summoner summoner = restTemplate.getForObject("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + name + "?api_key=" + apiKey, Summoner.class);
        summonerRepository.save(summoner);
        return "saved";
    }
}
