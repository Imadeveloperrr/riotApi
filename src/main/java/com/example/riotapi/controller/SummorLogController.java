package com.example.riotapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SummorLogController {

    @GetMapping("/Summoner/{summonername}")
    public String getSummonerNameVariable(@PathVariable String summonername) {

        return "Not Yet";
    }
}
