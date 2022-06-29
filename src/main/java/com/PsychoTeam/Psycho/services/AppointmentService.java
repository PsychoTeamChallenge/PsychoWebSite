package com.PsychoTeam.Psycho.services;

import com.PsychoTeam.Psycho.Dtos.AppointmentDTO;

import com.PsychoTeam.Psycho.Models.Appointment;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Tattoer;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    AppointmentDTO getAppointmentDTO(long id);

    Appointment getAppointmentById(long id);

    List<AppointmentDTO> getAppointmentsByClient(Client client);

    List<AppointmentDTO> getAppointmentsByDate(LocalDate date);

    List<AppointmentDTO> getAppointsmentsByTattoer(Tattoer tattoer);

    List<AppointmentDTO> getAppointmentsDTO();

    void saveAppointment(Appointment appointment);

    void removeAppointment(Appointment appointment);

}
