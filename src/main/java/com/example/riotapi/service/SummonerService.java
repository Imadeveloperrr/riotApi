package com.example.riotapi.service;

import com.example.riotapi.data.dto.SummonerResponseDto;

public interface SummonerService {

    SummonerResponseDto SummonerName(String string);
    SummonerResponseDto SummonerMatch(String string);
    SummonerResponseDto SummonerInfo(String string);
    SummonerResponseDto SummonerLeague(String string);

    SummonerResponseDto SummonerMastery(String String);

}
