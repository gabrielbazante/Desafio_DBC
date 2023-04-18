package com.gabrielbazante.agendavotingapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CountVoteDTO {

    private Long idAgenda;
    private int yes;
    private int no;
    private int total;
}
