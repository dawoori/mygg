package com.mygg.mygg.spectator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Getter
@Setter
@Entity
public class Spectator {
    @Id
    private long gameId;

    private long mapId;

    private String gameMode;

    private String gameType;

    private long gameQueueConfigId;



    private String platformId;

    private long gameStartTime;

    private long gameLength;

}
