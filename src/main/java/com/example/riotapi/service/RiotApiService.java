package com.example.riotapi.service;

import com.example.riotapi.data.dto.LeagueDto;
import com.example.riotapi.data.dto.SummonerDto;
import com.example.riotapi.data.dto.match.MatchDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RiotApiService {
    Mono<SummonerDto> getPuuidBySummonerName(String summonerName);

    Mono<List<String>> getMatchIdByPuuid(String puuid);

    Mono<LeagueDto> getLeagueInfoBySummonerid(String summonerid);

    Mono<MatchDto> getMatchInfoById(String matchid);
}
