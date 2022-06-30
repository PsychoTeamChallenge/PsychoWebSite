package com.PsychoTeam.Psycho.controllers;
import com.PsychoTeam.Psycho.Dtos.AppointmentDTO;
import com.PsychoTeam.Psycho.Dtos.ClientDTO;
import com.PsychoTeam.Psycho.Dtos.TattoerDTO;
import com.PsychoTeam.Psycho.Models.Appointment;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Product;
import com.PsychoTeam.Psycho.Models.Tattoer;
import com.PsychoTeam.Psycho.services.AppointmentService;
import com.PsychoTeam.Psycho.services.ClientService;
import com.PsychoTeam.Psycho.services.ProductService;
import com.PsychoTeam.Psycho.services.TattoerService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.PsychoTeam.Psycho.Utils.Utils.DeleteToken;
import static com.PsychoTeam.Psycho.Utils.Utils.GenerateToken;

@RestController
@RequestMapping("/api")
public class TattoerController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    ClientService clientService;

    @Autowired
    TattoerService tattoerService;

    @GetMapping("/tattoer")
    public ResponseEntity<?> getTattoer(
            @RequestParam long tattoer_id
    ){

        Tattoer tattoer = tattoerService.getTattoerById(tattoer_id);

        if(tattoer == null)
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);

        TattoerDTO tattoerDTO = new TattoerDTO(tattoer);

        return new ResponseEntity<>(tattoerDTO,HttpStatus.OK);
    }

    @GetMapping("/tattoers")
    public ResponseEntity<?> getAllTattoers(){

        List<TattoerDTO> tattoersListDTO = tattoerService.getTattoersDTO();

        return new ResponseEntity<>(tattoersListDTO,HttpStatus.OK);
    }
}
