package com.example.riotapi.service.impl;

import com.example.riotapi.data.dto.LeagueDto;
import com.example.riotapi.data.dto.SummonerDto;
import com.example.riotapi.data.dto.SummonerResponseDto;
import com.example.riotapi.data.dto.match.MatchDto;
import com.example.riotapi.data.dto.match.ParticipantDto;
import com.example.riotapi.service.GameInfoService;
import com.example.riotapi.service.RiotApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameInfoServiceImpl implements GameInfoService {

    @Autowired
    RiotApiService riotApiService;

    @Override
    public Mono<SummonerResponseDto> fetchGameInfo(String summonerName) {
        return riotApiService.getPuuidBySummonerName(summonerName)
                .flatMap(summonerDto -> Mono.zip(
                        riotApiService.getLeagueInfoBySummonerid(summonerDto.getId()),
                        riotApiService.getMatchIdByPuuid(summonerDto.getPuuid())
                ))
                .flatMap(tuple -> {
                    LeagueDto leagueDto = tuple.getT1();
                    List<String> matchIds = tuple.getT2();

                    Flux<MatchDto> matchInfoFlux = Flux.fromIterable(matchIds)
                            .flatMap(matchId -> riotApiService.getMatchInfoById(matchId));

                    return matchInfoFlux.collectList()
                            .flatMap(matchDtos -> {
                                SummonerResponseDto responseDto = new SummonerResponseDto();
                                // responseDto.setLeagueInfo(leagInfo);
                                List<List<String>> allSummonerNames = new ArrayList<>();

                                for (MatchDto matchDto : matchDtos) {
                                    List<String> summonerNames = new ArrayList<>();
                                    for (ParticipantDto participantDto : matchDto.getInfo().getParticipants()) {
                                        summonerNames.add(participantDto.getSummonerName());
                                    }
                                    allSummonerNames.add(summonerNames);
                                }

                                //responseDto.setAllSummonerNames(allSummonerNames);
                                return Mono.just(responseDto);
                            });
                });
    }


}
