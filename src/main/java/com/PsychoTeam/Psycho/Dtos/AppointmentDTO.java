package com.PsychoTeam.Psycho.Dtos;

import com.PsychoTeam.Psycho.Models.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class AppointmentDTO {

    private long id;
    private ClientDTO client;

    private TattoerDTO tattoer;

    private LocalDate date;

    public AppointmentDTO(Appointment appointment){
        this.id = appointment.getId();
        this.client = new ClientDTO(appointment.getClient());
        this.tattoer = new TattoerDTO(appointment.getTattoer());
        this.date = appointment.getDate();
    }
}
