package com.PsychoTeam.Psycho.services;

import com.PsychoTeam.Psycho.Dtos.ClientProductDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.ClientProduct;

import java.util.List;

public interface ClientProductService {

    ClientProductDTO getClientProductDTO(long id);

    ClientProduct getClientProductById(long id);

    List<ClientProductDTO> getClientProductsByClient(Client client);

    List<ClientProductDTO> getClientsProductDTO();

    long getTotalExpensesOfCart(Client client);

    void saveClientProduct(ClientProduct clientProduct);

    void removeClientProduct(ClientProduct clientProduct);

    void removeClientProducts(Client client);

}
