package com.example.riotapi.data.dto.match;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerkStyleDto {
    private String description;
    private List<PerkStyleSelectionDto> selections;
    private int style;
}
