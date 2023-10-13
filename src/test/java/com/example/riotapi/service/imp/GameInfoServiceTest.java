package com.example.riotapi.service.imp;

import com.example.riotapi.data.dto.SummonerDto;
import com.example.riotapi.data.dto.SummonerResponseDto;
import com.example.riotapi.data.dto.match.MatchDto;
import com.example.riotapi.service.RiotApiService;
import com.example.riotapi.service.impl.GameInfoServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class GameInfoServiceTest {

    @Mock
    RiotApiService riotApiService;

    @InjectMocks
    GameInfoServiceImpl gameInfoService;

    @Test
    void testFetchGameInfo() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MatchDto matchDto = objectMapper.readValue(
                Paths.get("src/test/resources/TestMatchDto.json").toFile(),
                MatchDto.class
        );
        SummonerDto summonerDto = objectMapper.readValue(
                Paths.get("src/test/resources/TestSummonerDto.json").toFile(),
                SummonerDto.class
        );
        List<String> matchIds = objectMapper.readValue(
                Paths.get("src/test/resources/TestMatchIdByPuuid.json").toFile(),
                new TypeReference<List<String>>() {}
        );

        // Mocking

        when(riotApiService.getPuuidBySummonerName(anyString())).thenReturn(Mono.just(summonerDto));
        when(riotApiService.getMatchIdByPuuid(anyString())).thenReturn(Mono.just(matchIds));
        when(riotApiService.getMatchInfoById(anyString())).thenReturn(Mono.just(matchDto));


        Mono<SummonerResponseDto> result = gameInfoService.fetchGameInfo("뭉둥이와방망이");

        StepVerifier.create(result)
                .assertNext(responseDto -> {
                    assertNotNull(responseDto);
                    assertNotNull(responseDto.getGameParticipantDtos());
                    assertEquals(5, responseDto.getGameParticipantDtos().size());

                })
                .verifyComplete();
    }
}
