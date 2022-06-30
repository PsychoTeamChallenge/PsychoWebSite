package com.PsychoTeam.Psycho.controllers;
import com.PsychoTeam.Psycho.Dtos.AppointmentDTO;
import com.PsychoTeam.Psycho.Dtos.ClientDTO;
import com.PsychoTeam.Psycho.Models.Appointment;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Product;
import com.PsychoTeam.Psycho.Models.Tattoer;
import com.PsychoTeam.Psycho.services.AppointmentService;
import com.PsychoTeam.Psycho.services.ClientService;
import com.PsychoTeam.Psycho.services.ProductService;
import com.PsychoTeam.Psycho.services.TattoerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.PsychoTeam.Psycho.Utils.Utils.DeleteToken;
import static com.PsychoTeam.Psycho.Utils.Utils.GenerateToken;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    ClientService clientService;

    @Autowired
    TattoerService tattoerService;

    @GetMapping("/appointments")
    public List<AppointmentDTO> getAppointments(){
        return appointmentService.getAppointmentsDTO();
    }

    @GetMapping("/appointments/current")
    public ResponseEntity<?> getAppointmentsCurrentClient(Authentication authentication){

        Client client = clientService.getClient(authentication.getName());

        if(client == null)
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);

        List<AppointmentDTO> appointmentDTOS = appointmentService.getAppointmentsByClient(client);

        return new ResponseEntity<>(appointmentDTOS,HttpStatus.OK);
    }

    @GetMapping("/appointments/tattoer")
    public ResponseEntity<?> getAppointmentsTattoer(
            @RequestParam long tattoer_id) throws MessagingException, UnsupportedEncodingException {

        Tattoer tattoer = tattoerService.getTattoerById(tattoer_id);

        List<AppointmentDTO> appointmentDTOS = appointmentService.getAppointsmentsByTattoer(tattoer);

        return new ResponseEntity<>(appointmentDTOS,HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/appointments/add")
    public ResponseEntity<?> addAppointment(
            @RequestParam long tattoer_id,
            @RequestParam String date,
            @RequestParam long phone,
            @RequestParam String bodyPart,
            @RequestParam String tattooSize,
            @RequestParam boolean color,
            Authentication auth
            ){

        if (date == null ||bodyPart == null || tattooSize == null)
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        if(auth.getName() == null)
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);

        Client client = clientService.getClient(auth.getName());
        Tattoer tattoer = tattoerService.getTattoerById(tattoer_id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Date: " + date);
        LocalDate dateUsed = LocalDate.parse(date, formatter);

        if (client == null || tattoer == null)
            return new ResponseEntity<>("Invalid client or tattoer", HttpStatus.FORBIDDEN);

        List<LocalDate> schedule = new ArrayList<>(tattoer.getSchedule());

        if (dateUsed == null)
            return new ResponseEntity<>("Invalid date", HttpStatus.FORBIDDEN);

        Appointment newAppointment = new Appointment(client, phone, bodyPart, tattooSize, color, tattoer, dateUsed);

        client.addAppointments(newAppointment);
        tattoer.addAppointments(newAppointment);

        appointmentService.saveAppointment(newAppointment);
        tattoerService.saveTattoer(tattoer);
        clientService.saveClient(client);
        return new ResponseEntity<>( HttpStatus.ACCEPTED);
    }

    @Transactional
    @DeleteMapping("/appointment/remove")
    public ResponseEntity<?> removeAppointment(@RequestParam long idAppointment, Authentication authentication){
        Client client = clientService.getClient(authentication.getName());
        if(client == null){
            return new ResponseEntity<>("Client unauthorized", HttpStatus.FORBIDDEN);
        }

        Appointment appointment = appointmentService.getAppointmentById(idAppointment);
        if(appointment == null)
            return new ResponseEntity<>("Invalid Data", HttpStatus.FORBIDDEN);

        appointmentService.removeAppointment(appointment);
        return new ResponseEntity<>("Appointment removed successfully", HttpStatus.ACCEPTED);
    }
}
