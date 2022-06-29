package com.PsychoTeam.Psycho.repositories;

import com.PsychoTeam.Psycho.Models.Appointment;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Tattoer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByClient(Client client);
    List<Appointment> findAllByDate(LocalDate date);
    List<Appointment> findAllByTattoer(Tattoer tattoer);
    Appointment getById(long id);

}
