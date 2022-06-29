package com.PsychoTeam.Psycho.Models;

import com.PsychoTeam.Psycho.Dtos.ClientProductDTO;
import com.PsychoTeam.Psycho.Dtos.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class Tattoer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String firstName,lastName,userName, email, password, token;

    @ElementCollection
    @Column(name = "schedule")
    private Set<LocalDate> schedule = new HashSet<>();

    @OneToMany(mappedBy = "tattoer", fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();
    private boolean enabled;

    public Tattoer(){}

    public Tattoer(String firstName,String lastName, String userName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public String getFullName(){
        return this.firstName + " " + this.getLastName();
    }

    public void addToSchedule(LocalDate date) {
        schedule.add(date);
    }

    public void addAppointments(Appointment appointment) {
        appointment.setTattoer(this);
        appointments.add(appointment);
    }

    public void deleteToken() {
        this.token = "";
    }
    public boolean isEnabled() {
        return this.enabled;
    }
}
