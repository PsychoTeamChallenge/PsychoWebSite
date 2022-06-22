package com.PsychoTeam.Psycho;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.repositories.ClientRepository;
import com.PsychoTeam.Psycho.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PsychoApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PsychoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientService clientService) {
		return (args) -> {
			Client clientAdmin = new Client("Ezequiel", "Priotto", "Zeke","zeke@psycho.com",passwordEncoder.encode("admin123") );
			Client clientAdmin2 = new Client("David", "Pereira", "Davey", "davey@psycho.com",passwordEncoder.encode("admin123"));

			clientAdmin.setEnabled(true);
			clientAdmin2.setEnabled(true);

			clientService.saveClient(clientAdmin);
			clientService.saveClient(clientAdmin2);

			System.out.println("PROGRAMA INICIADO :D");
		};
	}


}
