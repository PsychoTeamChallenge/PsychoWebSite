package com.PsychoTeam.Psycho.services.implement;


import com.PsychoTeam.Psycho.Dtos.ClientProductDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.repositories.ClientProductRepository;
import com.PsychoTeam.Psycho.services.ClientProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
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
    public List<ClientProduct> getClientProductsByClientNotDTO(Client client) {
        return clientProductRepository.findAllByClient(client);
    }

    @Override
    public List<ClientProductDTO> getClientsProductDTO() {
        return clientProductRepository.findAll().stream().map(ClientProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public double getTotalExpensesOfCart(Client client) {

        ArrayList<ClientProduct> clientProducts = new ArrayList<>(client.getCart());

        //        for(int i = 0; i < clientProducts.size(); i++){
//            double clientProductPrice = clientProducts.get(i).getPrice() * clientProducts.get(i).getQuantity();
//            totalPrice += clientProductPrice;
//        }

        double totalPrice = clientProducts.stream().mapToDouble(clientProduct -> clientProduct.getPrice() * clientProduct.getQuantity()).sum();

        return totalPrice;
    }

    @Override
    public void saveClientProduct(ClientProduct clientProduct) {
        clientProductRepository.save(clientProduct);
    }

    @Override
    public void removeClientProduct(ClientProduct clientProduct) {
        clientProductRepository.delete(clientProduct);
    }

    @Override
    public void removeClientProducts(Client client) {
        List<ClientProduct> cart = clientProductRepository.findAllByClient(client);
        for(int i = 0; i < cart.size(); i++){
            ClientProduct cartProduct = cart.get(i);
            clientProductRepository.delete(cartProduct);
        }
    }


}