package com.PsychoTeam.Psycho.repositories;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> getAllPurchaseByClient(Client client);

}
