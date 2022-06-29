package com.PsychoTeam.Psycho.repositories;

import com.PsychoTeam.Psycho.Models.Tattoer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TattoerRepository extends JpaRepository<Tattoer, Long> {

    Tattoer findByEmail(String email);
    Tattoer findByUserName(String userName);
    Tattoer getById(long id);
    Tattoer findByToken(String token);

}
