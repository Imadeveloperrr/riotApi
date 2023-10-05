package com.example.riotapi.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SummonerResponseDto {
    private List<List<String>> summonerName;
    private int kill;
    private int death;
    private int assist;
    private List<List<String>> championIcon;
    private int spell1;
    private int spell2;
    private int spell3;
    private int spell4;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
}
