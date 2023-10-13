package com.example.riotapi.service.impl;

import com.example.riotapi.data.dto.LeagueDto;
import com.example.riotapi.data.dto.SummonerDto;
import com.example.riotapi.data.dto.match.MatchDto;
import com.example.riotapi.service.RiotApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RiotApiServiceImpl implements RiotApiService {

    private final WebClient webClient;

    @Value("${riot.api.key}")
    private String riotApiKey;

    @Autowired
    public RiotApiServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://kr.api.riotgames.com/")
                .defaultHeader("X-Riot-Token", riotApiKey)
                .build();
    }

    @Override
    public Mono<SummonerDto> getPuuidBySummonerName(String summonerName) {
        return webClient.get()
                .uri("/lol/summoner/v4/summoner/by-name/" + summonerName)
                .retrieve()
                .bodyToMono(SummonerDto.class);
                //.map(SummonerDto::getPuuid);
    }

    @Override
    public Mono<List<String>> getMatchIdByPuuid(String puuid) {
        return webClient.get()
                .uri("/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=5")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<String>>() {});
    }

    @Override
    public Mono<LeagueDto> getLeagueInfoBySummonerId(String summonerId) {
        return webClient.get()
                .uri("/lol/league/v4/entries/by-summoner/" + summonerId)
                .retrieve()
                .bodyToMono(LeagueDto.class);
    }

    @Override
    public Mono<MatchDto> getMatchInfoById(String matchId) {
        return webClient.get()
                .uri("/lol/match/v5/matches/" + matchId)
                .retrieve()
                .bodyToMono(MatchDto.class);
    }
}
