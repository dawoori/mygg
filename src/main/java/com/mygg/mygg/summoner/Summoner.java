package com.mygg.mygg.summoner;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Summoner {
    public Summoner(){

    }

    @Id
    private String id;

    private String accountId;

    private String puuid;

    private String name;

    private int profileIconId;

    private long revisionDate;

    private long summonerLevel;
}
