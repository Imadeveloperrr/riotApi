package com.example.riotapi.service.impl;

import com.example.riotapi.data.dto.SummonerDto;
import com.example.riotapi.data.dto.SummonerResponseDto;
import com.example.riotapi.service.GameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GameInfoServiceImpl implements GameInfoService {



    @Override
    public Mono<SummonerResponseDto> fetchGameInfo(String summonerName) {

        return null;
    }


}
