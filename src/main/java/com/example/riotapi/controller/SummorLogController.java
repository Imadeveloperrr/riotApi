package com.example.riotapi.controller;

import com.example.riotapi.data.dto.SummonerResponseDto;
import com.example.riotapi.service.GameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class SummorLogController {

    @Autowired
    private GameInfoService gameInfoService;

    @GetMapping("/Summoner/{summonername}")
    public Mono<SummonerResponseDto> getSummonerNameVariable(@PathVariable String summonername) {

        return gameInfoService.fetchGameInfo(summonername);
    }
}
