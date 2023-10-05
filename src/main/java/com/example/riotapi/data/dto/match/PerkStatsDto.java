package com.example.riotapi.data.dto.match;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerkStatsDto {
    private int defense;
    private int flex;
    private int offense;
}
