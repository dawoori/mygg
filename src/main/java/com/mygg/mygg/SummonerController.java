package com.mygg.mygg;

import com.mygg.mygg.summoner.Summoner;
import com.mygg.mygg.summoner.SummonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class SummonerController {
    @Value("${mygg.api-key}")
    private String apiKey;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private SummonerRepository summonerRepository;

    @GetMapping("/summoner/{name}")
    public Summoner getSummoner(@PathVariable String name) throws Exception{
        String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + name + "?api_key=" + apiKey;
        Summoner summoner = restTemplate.getForObject(url, Summoner.class);

        return summoner;
    }

    @PostMapping("/summoner/{name}")
    public Summoner getAndPostSummoner(@PathVariable String name) throws Exception{
        String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + name + "?api_key=" + apiKey;
        Summoner summoner = restTemplate.getForObject(url, Summoner.class);

        assert summoner != null;
        boolean isExist = summonerRepository.existsById(summoner.getId());

        if(!isExist) summonerRepository.save(summoner);

        return summoner;
    }
}
