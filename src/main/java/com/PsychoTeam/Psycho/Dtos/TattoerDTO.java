package com.PsychoTeam.Psycho.Dtos;

import com.PsychoTeam.Psycho.Models.*;
import lombok.Getter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class TattoerDTO {
    private long id;

    String firstName, lastName, username, email;

    private Set<LocalDate> schedule;

    private Set<AppointmentDTO> appointments;

    public TattoerDTO(Tattoer tattoer){
        this.id = tattoer.getId();
        this.firstName = tattoer.getFirstName();
        this.lastName = tattoer.getLastName();
        this.username = tattoer.getUserName();
        this.email = tattoer.getEmail();
        this.schedule = tattoer.getSchedule();
        this.appointments = tattoer.getAppointments().stream().map(AppointmentDTO::new).collect(Collectors.toSet());
    }
}
