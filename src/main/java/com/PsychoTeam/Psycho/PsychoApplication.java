package com.PsychoTeam.Psycho;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Product;
import com.PsychoTeam.Psycho.repositories.ClientRepository;
import com.PsychoTeam.Psycho.services.ClientService;
import com.PsychoTeam.Psycho.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

@SpringBootApplication
public class PsychoApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PsychoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientService clientService, ProductService productService) {
		return (args) -> {
			Client clientAdmin = new Client("Ezequiel", "Priotto", "Zeke","zeke@psycho.com",passwordEncoder.encode("admin123") );
			Client clientAdmin2 = new Client("David", "Pereira", "Davey", "davey@psycho.com",passwordEncoder.encode("admin123"));

			clientAdmin.setEnabled(true);
			clientAdmin2.setEnabled(true);

			List<Double> sizes = asList(14.0,16.0);
			List<String> colors = asList("yellow");

			Product beanie = new Product("Beanie", "yellow beanie","./assets/productosPrueba/gorrito1.png",30,2600,"CLOTHING","HAT");
			beanie.setColors(colors);
			beanie.setSizes(sizes);

			sizes = asList(14.0,16.0,18.5);
			colors = asList("brown");


			Product beanie2 = new Product("Beanie", "brown beanie","./assets/productosPrueba/gorrito2.png",3,2600,"CLOTHING","HAT");
			beanie2.setColors(colors);
			beanie2.setSizes(sizes);


			sizes = asList(20.0,14.0,16.5);
			colors = asList("black");


			Product sweatshirt = new Product("Sweatshirt", "black sweatshirt","./assets/productosPrueba/buzo.png",10,6000,"CLOTHING","SWEATSHIRT");
			sweatshirt.setColors(colors);
			sweatshirt.setSizes(sizes);

			sizes = asList(14.0,16.0,18.5);
			colors = asList("beige");


			Product sweatshirt2 = new Product("Sweatshirt", "beige sweatshirt","./assets/productosPrueba/remera.png",45,4300,"CLOTHING","SWEATSHIRT");
			sweatshirt2.setColors(colors);
			sweatshirt2.setSizes(sizes);

			productService.saveProduct(beanie);
			productService.saveProduct(beanie2);
			productService.saveProduct(sweatshirt);
			productService.saveProduct(sweatshirt2);
			
			clientService.saveClient(clientAdmin);
			clientService.saveClient(clientAdmin2);

			clientService.saveClient(clientAdmin);



			System.out.println("PROGRAMA INICIADO :D");
		};
	}


}
