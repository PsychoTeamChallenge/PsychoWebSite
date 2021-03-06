package com.PsychoTeam.Psycho.controllers;
import com.PsychoTeam.Psycho.Dtos.ClientDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Product;
import com.PsychoTeam.Psycho.services.ClientService;
import com.PsychoTeam.Psycho.services.ProductService;
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
import java.util.List;

import static com.PsychoTeam.Psycho.Utils.Utils.DeleteToken;
import static com.PsychoTeam.Psycho.Utils.Utils.GenerateToken;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    ProductService productService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/clients")
    public List<ClientDTO> getClients(){
        return clientService.getClientsDTO();
    }

    @GetMapping("/clients/current")
    public ResponseEntity<?> getCurrentClient(Authentication authentication){

        Client client = clientService.getClient(authentication.getName());

        if(client == null)
            return new ResponseEntity<>("The user no have client rol", HttpStatus.FORBIDDEN);

        ClientDTO clientDTO = new ClientDTO(client);

        return new ResponseEntity<>(clientDTO,HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<?> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password, @RequestParam String userName, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || userName.isEmpty())
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);


        if (userName.contains(" ") && userName.contains("@"))
            return new ResponseEntity<>("Character invalid", HttpStatus.FORBIDDEN);


        if (clientService.getClient(email) != null )
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);


        if (clientService.getClient(userName) != null)
            return new ResponseEntity<>("Username already in use", HttpStatus.FORBIDDEN);

        if(password.length() < 5)
            return new ResponseEntity<>("Short password", HttpStatus.FORBIDDEN);

        if(userName.length() > 15)
            return new ResponseEntity<>("Username too long", HttpStatus.FORBIDDEN);


        Client client = new Client(firstName, lastName, userName, email, passwordEncoder.encode(password));

        String randomCode = GenerateToken(64);
        client.setToken(randomCode);
        client.setEnabled(false);

        clientService.saveClient(client);

        sendVerificationEmail(client);

        return new ResponseEntity<>("User created, email verification is required",HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/activateAccount/{token}")
    public ResponseEntity<?> activateAccount(HttpServletRequest request, @PathVariable String token){

        Client client = clientService.getClientToken(token);

        if(client == null)
            return new ResponseEntity<>("Token invalid", HttpStatus.FORBIDDEN);

        client.setEnabled(true);
        client.deleteToken();
        DeleteToken(token);
        clientService.saveClient(client);
        return new ResponseEntity<>( HttpStatus.ACCEPTED);
    }

    @PatchMapping("/clients/current")
    public ResponseEntity<?> changeCurrentClient(Authentication authentication, @RequestParam String userName) {
        Client client = clientService.getClient(authentication.getName());
        if ( userName.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (userName.contains(" ")) {
            return new ResponseEntity<>("Character invalid", HttpStatus.FORBIDDEN);
        }
        client.setUserName(userName);
        clientService.saveClient(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/clients/current/disable")
    public ResponseEntity<?> disableCurrentClient(Authentication authentication) throws MessagingException, UnsupportedEncodingException {
        Client client = clientService.getClient(authentication.getName());

        if (client == null)
            return new ResponseEntity<>("You no are client",HttpStatus.FORBIDDEN);

        String randomCode = GenerateToken(64);
        client.setToken(randomCode);
        client.setEnabled(false);

        clientService.saveClient(client);
        sendDisableEmail(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/clients/current/favourites")
    public ResponseEntity<?> addFavourite(@RequestParam long idProduct, Authentication authentication){
        Client client = clientService.getClient(authentication.getName());
        if(client == null){
            return new ResponseEntity<>("Client unauthorized", HttpStatus.FORBIDDEN);
        }

        Product product = productService.getProductById(idProduct);
        if(product == null){
            return new ResponseEntity<>("Product not exist", HttpStatus.FORBIDDEN);
        }
        client.addFavourite(product);
        clientService.saveClient(client);
        return new ResponseEntity<>("Product remove success", HttpStatus.ACCEPTED);
    }

    @PatchMapping("/clients/current/favourites")
    public ResponseEntity<?> removeFavourite(@RequestParam long idProduct, Authentication authentication){
        Client client = clientService.getClient(authentication.getName());
        if(client == null){
            return new ResponseEntity<>("Client unauthorized", HttpStatus.FORBIDDEN);
        }

        Product product = productService.getProductById(idProduct);
        if(product == null){
            return new ResponseEntity<>("Product not exist", HttpStatus.FORBIDDEN);
        }
        client.removeFavourite(product);
        clientService.saveClient(client);
        return new ResponseEntity<>("Product remove success", HttpStatus.ACCEPTED);
    }

    @PatchMapping("/clients/edit")
    public ResponseEntity<?> editCurrentClient(@RequestParam String firstName,
                                               @RequestParam String lastName,
                                               @RequestParam String email,
                                               Authentication authentication){
        Client client = clientService.getClient(authentication.getName());
        if(client == null){
            return new ResponseEntity<>("Client unauthorized", HttpStatus.FORBIDDEN);
        }

        if(firstName.equals("") && lastName.equals("") && email.equals(""))
            return new ResponseEntity<>("Nothing has changed", HttpStatus.ACCEPTED);

        if (!firstName.equals(""))
            client.setFirstName(firstName);

        if (!lastName.equals(""))
            client.setLastName(lastName);

        if (!email.equals(""))
            client.setEmail(email);

        clientService.saveClient(client);
        return new ResponseEntity<>("Client properties edited succesfully", HttpStatus.ACCEPTED);
    }

    private void sendVerificationEmail(Client client)
            throws MessagingException, UnsupportedEncodingException {

        String toAddress = client.getEmail();
        String fromAddress = "psychoteammh@gmail.com";
        String senderName = "PsychoTeam";
        String subject = "Please verify your registration";
        String content = "<img src=\"https://i.imgur.com/vfyatKL.png\" style=\"width:100%; \" alt=\"ImgRegister\" width=\"450\" height=\"302\"/> <br>" +
                "<h2 style=\"font-family: system-ui;color: #720651;font-weight: bold;font-size: 2rem;letter-spacing: 0.1px;\">Hi [[name]]!</h2>"
                + "<p style=\"color:black;font-weight:bolder;font-size:1.1rem;display:flex;gap: 0.4rem;\"> Thank you very much for trusting our website! Below you will find a link to verify <a href=\"[[URL]]\" target=\"_self\" style=\" color:#00c4ff;text-decoration:underline;margin-left: 0.4rem;\"> your account. </a> </p>"
                + "<div style=\"display:flex;font-size: 1rem;\"> <p style=\"color:black;font-weight: bold;\"> See you soon, </p> <p style=\"color: rgb(114 6 81);font-weight:bold\"> <a style=\"color: #720651;text-decoration: none;font-weight: bold; \" href=\"https://github.com/PsychoTeamChallenge\" target=\"_self\"  > Psycho teams. </a>  </p> </div> "
                ;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", client.getFullName());
        String verifyURL = "https://psychoweb.herokuapp.com/web/verification.html?token=" + client.getToken();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);
        mailSender.send(message);
    }

    private void sendDisableEmail(Client client)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = client.getEmail();
        String fromAddress = "bankrdox@gmail.com";
        String senderName = "BankrdoX";
        String subject = "Disable account";
        String content = "<h2 style=\"color:black;\">Hi [[name]]!</h2>"
                + "<p style=\"color:black;\"> You have recently terminated your BankrdoX account. We hope you will be back soon. If you wish, please click on the following link: </p>"
                +"<img src=\"https://i.imgur.com/DjW6seD.png\" alt=\"ImgRegister\" width=\"450\" height=\"302\"/> <br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\" style=\"color:#ff5e14;\">REACTIVE YOUR ACCOUNT</a></h3>"
                + "<div style=\"display:flex;gap: 0.4rem;\"> <p style=\"color:black;\"> Thank you, </p> <p style=\"color:rgb(232, 91, 26);font-weight: bold;\"> BankrdoX teams. </p> </div> "
                ;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", client.getFullName());

        String verifyURL = "https://bankrdox.herokuapp.com/web/activateClient.html?token=" + client.getToken();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);
        mailSender.send(message);
    }

}
