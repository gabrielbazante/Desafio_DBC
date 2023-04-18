package com.gabrielbazante.agendavotingapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielbazante.agendavotingapi.DTO.CountVoteDTO;
import com.gabrielbazante.agendavotingapi.DTO.VoteDTO;
import com.gabrielbazante.agendavotingapi.entity.Vote;
import com.gabrielbazante.agendavotingapi.service.VoteService;

@RestController
@RequestMapping("/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService service) {
        this.voteService = service;
    }

    @PostMapping("/voting")
    public Vote vote(@RequestBody VoteDTO voteDTO) {
        return voteService.createVote(voteDTO);
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<CountVoteDTO> countTotalVotesPerAgenda(@PathVariable Long id) {
        int totalVotesYes = voteService.countTotalVotesPerAgendaYes(id);
        int totalVotesNo = voteService.countTotalVotesPerAgendaNo(id);
        int totalVotes = totalVotesYes + totalVotesNo;
        return ResponseEntity.ok(new CountVoteDTO(id, totalVotesYes, totalVotesNo, totalVotes));
    }
    

    
}
