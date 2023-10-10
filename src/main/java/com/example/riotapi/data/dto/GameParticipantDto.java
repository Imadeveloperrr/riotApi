package com.example.riotapi.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameParticipantDto {
    private List<String> summonerName;
    private List<String> championName;
    private int kill;
    private int death;
    private int assist;
    private int perkId1;
    private int perkId2;
    private int summonerId1;
    private int summonerId2;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;

}
