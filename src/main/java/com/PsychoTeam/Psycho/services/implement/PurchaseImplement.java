package com.PsychoTeam.Psycho.services.implement;

import com.PsychoTeam.Psycho.Dtos.ProductDTO;
import com.PsychoTeam.Psycho.Dtos.PurchaseDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.Models.Product;
import com.PsychoTeam.Psycho.Models.Purchase;
import com.PsychoTeam.Psycho.repositories.ClientProductRepository;
import com.PsychoTeam.Psycho.repositories.ClientRepository;
import com.PsychoTeam.Psycho.repositories.ProductRepository;
import com.PsychoTeam.Psycho.repositories.PurchaseRepository;
import com.PsychoTeam.Psycho.services.ProductService;
import com.PsychoTeam.Psycho.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseImplement implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public Purchase getPurchaseById(long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Purchase> getPurchasesByClient(Client client) {
        return purchaseRepository.getAllPurchaseByClient(client);
    }

    @Override
    public List<PurchaseDTO> getPurchasesDTOByClient(Client client) {
        return purchaseRepository.getAllPurchaseByClient(client).stream().map(PurchaseDTO::new).collect(Collectors.toList());
    }

    @Override
    public void savePurchase(Purchase Purchase) {
        purchaseRepository.save(Purchase);
    }

    @Override
    public void removePurchase(Purchase purchase) {
        purchaseRepository.delete(purchase);
    }
}
