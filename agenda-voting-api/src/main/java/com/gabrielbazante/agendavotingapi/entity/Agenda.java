package com.gabrielbazante.agendavotingapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "agenda")
@Data
public class Agenda {

    @Id
    @SequenceGenerator(name = "agenda_id_agenda_seq", sequenceName = "agenda_id_agenda_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agenda_id_agenda_seq")
    @Column(name = "id_agenda", nullable = false)
    private Long id_agenda;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;
    
}
