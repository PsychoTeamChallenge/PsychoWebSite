package com.PsychoTeam.Psycho.services;

import com.PsychoTeam.Psycho.Dtos.PurchaseDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Purchase;

import java.util.List;

public interface PurchaseService {

    Purchase getPurchaseById(long id);

    List<Purchase> getPurchasesByClient(Client client);

    List<PurchaseDTO> getPurchasesDTOByClient(Client client);

    void addProducts(Client client, Purchase purchase);

    void savePurchase(Purchase purchase);

    void removePurchase(Purchase purchase);

}
