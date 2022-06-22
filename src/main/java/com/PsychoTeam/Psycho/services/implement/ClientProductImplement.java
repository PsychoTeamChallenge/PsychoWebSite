package com.PsychoTeam.Psycho.services.implement;


import com.PsychoTeam.Psycho.Dtos.ClientProductDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.repositories.ClientProductRepository;
import com.PsychoTeam.Psycho.services.ClientProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientProductImplement implements ClientProductService {

    @Autowired
    ClientProductRepository clientProductRepository;

    @Override
    public ClientProductDTO getClientProductDTO(long id) {
        return new ClientProductDTO(clientProductRepository.findById(id).orElse(null));
    }

    public ClientProduct getClientProductById(long id) {
        return clientProductRepository.findById(id).orElse(null);
    }
    @Override
    public List<ClientProductDTO> getClientProductsByClient(Client client) {
        return clientProductRepository.findAllByClient(client).stream().map(ClientProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ClientProductDTO> getClientsProductDTO() {
        return clientProductRepository.findAll().stream().map(ClientProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public void saveClientProduct(ClientProduct clientProduct) {
        clientProductRepository.save(clientProduct);
    }
}