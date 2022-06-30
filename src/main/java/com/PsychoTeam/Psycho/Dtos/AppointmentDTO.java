package com.PsychoTeam.Psycho.Dtos;

import com.PsychoTeam.Psycho.Models.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class AppointmentDTO {

    private long id, phone;

    private String tattooSize, bodyPart;

    private boolean color;

    private LocalDate date;

    public AppointmentDTO(Appointment appointment){
        this.id = appointment.getId();
        this.date = appointment.getDate();
        this.phone = appointment.getPhone();
        this.tattooSize = appointment.getTattooSize();
        this.bodyPart = appointment.getBodyPart();
        this.color = appointment.isColor();
    }
}
