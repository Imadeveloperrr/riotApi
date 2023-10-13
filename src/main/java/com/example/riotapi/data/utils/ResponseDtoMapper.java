package com.example.riotapi.data.utils;

import com.example.riotapi.data.dto.GameParticipantDto;
import com.example.riotapi.data.dto.match.ParticipantDto;

import java.util.ArrayList;
import java.util.List;

public class ResponseDtoMapper {

    public static GameParticipantDto mapToGameParticipantDto(ParticipantDto firstParticipantDto, List<ParticipantDto> participants) {
        GameParticipantDto gameParticipantDto = new GameParticipantDto();

        gameParticipantDto.setKill(firstParticipantDto.getKills());
        gameParticipantDto.setDeath(firstParticipantDto.getDeaths());
        gameParticipantDto.setAssist(firstParticipantDto.getAssists());
        gameParticipantDto.setItem1(firstParticipantDto.getItem1());
        gameParticipantDto.setItem2(firstParticipantDto.getItem2());
        gameParticipantDto.setItem3(firstParticipantDto.getItem3());
        gameParticipantDto.setItem4(firstParticipantDto.getItem4());
        gameParticipantDto.setItem5(firstParticipantDto.getItem5());
        gameParticipantDto.setItem6(firstParticipantDto.getItem6());
        gameParticipantDto.setPerkId1(firstParticipantDto.getPerksDto().getStyles().get(0).getSelections().get(0).getPerk()); //주룬의 첫번째 룬
        gameParticipantDto.setPerkId2(firstParticipantDto.getPerksDto().getStyles().get(1).getStyle()); // 부룬
        gameParticipantDto.setSummonerId1(firstParticipantDto.getSummoner1Id());
        gameParticipantDto.setSummonerId2(firstParticipantDto.getSummoner2Id());

        List<String> championNames = new ArrayList<>();
        List<String> summonerNames = new ArrayList<>();
        for (ParticipantDto participant : participants) {
            championNames.add(participant.getChampionName());
            summonerNames.add(participant.getSummonerName());
        }

        gameParticipantDto.setChampionName(championNames);
        gameParticipantDto.setSummonerName(summonerNames);

        return gameParticipantDto;
    }
}
