package com.PsychoTeam.Psycho.services;

import com.PsychoTeam.Psycho.Dtos.TattoerDTO;
import com.PsychoTeam.Psycho.Models.Tattoer;

import java.util.List;

public interface TattoerService {

    TattoerDTO getTattoerDTO(long id);

    Tattoer getTattoerById(long id);

    List<TattoerDTO> getTattoersDTO();

    Tattoer getTattoer(String credential);

    Tattoer getTattoerToken(String token);

    void saveTattoer(Tattoer tattoer);

    boolean existTattoer(long id);

}
