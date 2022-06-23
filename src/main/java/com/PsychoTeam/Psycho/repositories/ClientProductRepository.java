package com.PsychoTeam.Psycho.repositories;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.ClientProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ClientProductRepository extends JpaRepository<ClientProduct, Long> {
    public List<ClientProduct> findAllByClient(Client client);
}
