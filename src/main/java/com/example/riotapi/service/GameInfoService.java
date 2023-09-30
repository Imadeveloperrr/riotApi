package com.example.riotapi.service;

import com.example.riotapi.data.dto.SummonerResponseDto;
import reactor.core.publisher.Mono;

public interface GameInfoService {

    Mono<SummonerResponseDto> fetchGameInfo(String summonerName);

}
