package com.PsychoTeam.Psycho.services.implement;

import com.PsychoTeam.Psycho.Dtos.AppointmentDTO;
import com.PsychoTeam.Psycho.Dtos.TattoerDTO;
import com.PsychoTeam.Psycho.Models.Appointment;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Tattoer;
import com.PsychoTeam.Psycho.repositories.AppointmentRepository;
import com.PsychoTeam.Psycho.repositories.TattoerRepository;
import com.PsychoTeam.Psycho.services.AppointmentService;
import com.PsychoTeam.Psycho.services.TattoerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImplement implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public AppointmentDTO getAppointmentDTO(long id) {
        return new AppointmentDTO(appointmentRepository.getById(id));
    }

    @Override
    public Appointment getAppointmentById(long id) {
        return appointmentRepository.getById(id);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByClient(Client client) {
        return appointmentRepository.findAllByClient(client).stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findAllByDate(date).stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointsmentsByTattoer(Tattoer tattoer) {
        return appointmentRepository.findAllByTattoer(tattoer).stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsDTO() {
        return appointmentRepository.findAll().stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public void removeAppointment(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }
}