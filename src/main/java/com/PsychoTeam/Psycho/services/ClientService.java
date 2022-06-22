package com.PsychoTeam.Psycho.services;

import com.PsychoTeam.Psycho.Dtos.ClientDTO;
import com.PsychoTeam.Psycho.Models.Client;

import java.util.List;

public interface ClientService {

    ClientDTO getClientDTO(long id);

    Client getClientById(long id);

    List<ClientDTO> getClientsDTO();

    Client getClient(String credential);

    Client getClientToken(String token);

    void saveClient(Client client);

    boolean existClient(long id);

}
