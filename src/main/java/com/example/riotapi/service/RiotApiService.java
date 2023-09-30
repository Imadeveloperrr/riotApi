package com.example.riotapi.service;

import com.example.riotapi.data.dto.LeagueDto;
import com.example.riotapi.data.dto.SummonerDto;
import reactor.core.publisher.Mono;

public interface RiotApiService {
    Mono<SummonerDto> getPuuidBySummonerName(String summonerName);

    Mono<String> getMatchIdByPuuid(String puuid);

    Mono<LeagueDto> getLeagueInfoBySummonerid(String summonerid);
}
