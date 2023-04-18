package com.gabrielbazante.agendavotingapi.DTO;

import lombok.Data;

@Data
public class VoteDTO {

    public VoteDTO() {
    }
    
    public VoteDTO(long idAgenda, String cpf, String vote) {
    }
    private String cpf;
    private Long idAgenda;
    private String vote;

}
