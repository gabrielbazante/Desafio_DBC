package com.gabrielbazante.agendavotingapi.controller;

import org.springframework.web.bind.annotation.*;

import com.gabrielbazante.agendavotingapi.entity.Voter;
import com.gabrielbazante.agendavotingapi.service.VoterService;

@RestController
@RequestMapping("/voters")
public class VoterController {

    private final VoterService voterService;

    public VoterController(VoterService service) {
        this.voterService = service;
    }

    @GetMapping("/voter/{cpf}")
    public Voter findByCpf(@PathVariable String cpf) {
        return voterService.findByCpf(cpf); 
    }

    @PostMapping("/voter")
    public Voter createVoter(@RequestBody Voter voter) {
        return voterService.createVoter(voter);
    }
    
    @PutMapping("/voter/{id}")
    public Voter updateVoter(@PathVariable Long id, @RequestBody Voter voter) {
        return voterService.updateVoter(id, voter);
    }

    @DeleteMapping("/voter/{id}")
    public void deleteById(@PathVariable Long id) {
        voterService.deleteById(id);
    }

}
