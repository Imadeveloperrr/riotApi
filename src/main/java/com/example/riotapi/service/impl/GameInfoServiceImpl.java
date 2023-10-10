package com.example.riotapi.service.impl;

import com.example.riotapi.data.dto.GameParticipantDto;
import com.example.riotapi.data.dto.LeagueDto;
import com.example.riotapi.data.dto.SummonerDto;
import com.example.riotapi.data.dto.SummonerResponseDto;
import com.example.riotapi.data.dto.match.MatchDto;
import com.example.riotapi.data.dto.match.ParticipantDto;
import com.example.riotapi.data.utils.ResponseDtoMapper;
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

import static com.example.riotapi.data.utils.ResponseDtoMapper.mapToGameParticipantDto;

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
                    // Flux.fromIterable() -> List를 Flux<String>로 변환.
                    Flux<MatchDto> matchInfoFlux = Flux.fromIterable(matchIds) // 각 매치들의 정보
                            .flatMap(matchId -> riotApiService.getMatchInfoById(matchId));

                    return matchInfoFlux.collectList() // List<String>을 Mono<List<String>>합체
                            .flatMap(matchDtos -> { // matchDtos = List<MatchDto>
                                SummonerResponseDto responseDto = new SummonerResponseDto();

                                List<GameParticipantDto> gameParticipantList = new ArrayList<>();

                                for (MatchDto matchDto : matchDtos) { // matchDtos 5개 각각의 요소를 matchDto에 할당
                                    ParticipantDto firstParticipantDto = matchDto.getInfo().getParticipants().get(0);
                                    List<ParticipantDto> participants = matchDto.getInfo().getParticipants();

                                    GameParticipantDto gameParticipantDto = ResponseDtoMapper.mapToGameParticipantDto(firstParticipantDto, participants);
                                    // 맵핑이 많아서 유틸리티 클래스로 나누기. ResponseDtoMapper

                                    gameParticipantList.add(gameParticipantDto);
                                }
                                responseDto.setGameParticipantDtos(gameParticipantList);
                                return Mono.just(responseDto);
                            });
                });
    }

}
