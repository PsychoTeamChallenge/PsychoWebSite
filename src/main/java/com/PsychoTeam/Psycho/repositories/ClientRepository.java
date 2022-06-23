package com.PsychoTeam.Psycho.repositories;
import com.PsychoTeam.Psycho.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);
    Client findByUserName(String userName);
    Client getById(long id);
    Client findByToken(String token);

}
