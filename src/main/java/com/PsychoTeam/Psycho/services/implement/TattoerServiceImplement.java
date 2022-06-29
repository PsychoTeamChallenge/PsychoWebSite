package com.PsychoTeam.Psycho.services.implement;

import com.PsychoTeam.Psycho.Dtos.TattoerDTO;
import com.PsychoTeam.Psycho.Models.Tattoer;
import com.PsychoTeam.Psycho.repositories.TattoerRepository;
import com.PsychoTeam.Psycho.services.TattoerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TattoerServiceImplement implements TattoerService {

    @Autowired
    TattoerRepository tattoerRepository;

    @Override
    public TattoerDTO getTattoerDTO(long id) {
        return new TattoerDTO(tattoerRepository.findById(id).orElse(null));
    }

    @Override
    public Tattoer getTattoerById(long id) {
        return tattoerRepository.findById(id).orElse(null);
    }

    @Override
    public List<TattoerDTO> getTattoersDTO() {
        return tattoerRepository.findAll().stream().map(TattoerDTO::new).collect(Collectors.toList());
    }

    @Override
    public Tattoer getTattoer(String credential) {
        if (credential.contains("@")){
            return tattoerRepository.findByEmail(credential);
        }
        else{
            return tattoerRepository.findByUserName(credential);
        }
    }

    @Override
    public Tattoer getTattoerToken(String token) {
        return tattoerRepository.findByToken(token);
    }

    @Override
    public void saveTattoer(Tattoer Tattoer) {
        tattoerRepository.save(Tattoer);
    }

    @Override
    public boolean existTattoer(long id) {
        return tattoerRepository.existsById(id);
    }
}