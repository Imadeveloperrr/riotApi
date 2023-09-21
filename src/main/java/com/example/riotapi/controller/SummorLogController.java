package com.example.riotapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SummorLogController {

    @GetMapping("/Summoner")
    public String test() {
        return "Hello, world !";
    }
}
