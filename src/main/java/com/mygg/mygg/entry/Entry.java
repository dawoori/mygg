package com.mygg.mygg.entry;

import com.mygg.mygg.summoner.Summoner;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Entry {
    public Entry() {}

    @Id
    private String leagueId;

    private String queueType;

    private String tier;

    private String rank;

    private String summonerId;

    private String summonerName;

    private int leaguePoints;

    private int wins;

    private int losses;

    private boolean veteran;

    private boolean inactive;

    private boolean freshBlood;

    private boolean hotStreak;

}
