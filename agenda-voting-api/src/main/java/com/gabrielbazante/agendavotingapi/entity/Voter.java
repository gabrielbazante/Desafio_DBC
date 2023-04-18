package com.gabrielbazante.agendavotingapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "voter")
@Data
public class Voter {

    @Id
    @SequenceGenerator(name = "voter_id_voter_seq", sequenceName = "voter_id_voter_seq", allocationSize = 1)
    @GeneratedValue(generator = "voter_id_voter_seq")
    @Column(name = "id_voter")
    private Long idVoter;
    private String name;
    private String cpf;
}
