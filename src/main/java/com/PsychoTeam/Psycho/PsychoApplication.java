package com.PsychoTeam.Psycho;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Post;
import com.PsychoTeam.Psycho.Models.Product;
import com.PsychoTeam.Psycho.repositories.ClientRepository;
import com.PsychoTeam.Psycho.services.ClientService;
import com.PsychoTeam.Psycho.services.PostService;
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

import static com.PsychoTeam.Psycho.Models.PostType.TATTOO;
import static java.util.Arrays.asList;

@SpringBootApplication
public class PsychoApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PsychoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientService clientService, ProductService productService, PostService postService) {
		return (args) -> {

//			DECLARACION DE CLIENTE: NOMBRE | APELLIDO | USERNAME | EMAIL | CONTRASEÑA

			Client clientAdmin = new Client("Ezequiel", "Priotto", "Zeke","zeke@psycho.com",passwordEncoder.encode("admin123") );
			Client clientAdmin2 = new Client("David", "Pereira", "Davey", "davey@psycho.com",passwordEncoder.encode("admin123"));

			clientAdmin.setEnabled(true);
			clientAdmin2.setEnabled(true);



			clientService.saveClient(clientAdmin);
			clientService.saveClient(clientAdmin2);


//			DECLARACION DE PRODUCTO: NOMBRE | DESCRIPCION | FOTO | STOCK | PRECIO | CATEGORIA (CLOTHING O PIERCING) | SUBCATEGORIA
//	        PARA OBTENER LAS URL DE LAS IMAGENES IR A  subidorImagenes.html Y COPIAR LA URL QUE LES DE EL SWEETALERT
//			PARA LOS TAMAÑOS Y COLORES USAR SIZES (O COLORS) = asList(ACA LOS DATOS)

			List<Double> sizes = asList(14.0,16.0);
			List<String> colors = asList("yellow");

			Product beanie = new Product("Beanie", "yellow beanie","./assets/productosPrueba/gorrito1.png",30,2600,"CLOTHING","HAT");
			beanie.setColors(colors);
			beanie.setSizes(sizes);

			sizes = asList(14.0,16.0,18.5);
			colors = asList("brown");


			Product beanie2 = new Product("Beanie", "brown beanie","./assets/productosPrueba/gorrito2.png",3,2450,"CLOTHING","HAT");
			beanie2.setColors(colors);
			beanie2.setSizes(sizes);


			sizes = asList(20.0,14.0,16.5);
			colors = asList("black");


			Product sweatshirt = new Product("Hoodie", "black hoodie","./assets/productosPrueba/buzo.png",10,6000,"CLOTHING","SWEATSHIRT");
			sweatshirt.setColors(colors);
			sweatshirt.setSizes(sizes);

			sizes = asList(14.0,16.0,18.5);
			colors = asList("beige");


			Product sweatshirt2 = new Product("Sweatshirt", "beige sweatshirt","./assets/productosPrueba/remera.png",45,4300,"CLOTHING","SWEATSHIRT");
			sweatshirt2.setColors(colors);
			sweatshirt2.setSizes(sizes);

			sizes = asList(14.0,16.0,18.5);
			colors = asList("beige");


			Product piercing = new Product("piercing1", "beige sweatshirt","./assets/piercings/piercing1.png",45,4300,"PIERCING","EAR");
			sweatshirt2.setColors(colors);
			sweatshirt2.setSizes(sizes);

			sizes = asList(14.0,16.0,18.5);
			colors = asList("beige");


			Product piercing2 = new Product("piercing2", "beige sweatshirt","./assets/piercings/piercing2.png",45,4300,"PIERCING","EAR");
			piercing2.setColors(colors);
			piercing2.setSizes(sizes);

			sizes = asList(14.0,16.0,18.5);
			colors = asList("beige");


			Product piercing3 = new Product("piercing3", "beige sweatshirt","./assets/piercings/piercing3.png",45,4300,"PIERCING","NOSE");
			piercing3.setColors(colors);
			piercing3.setSizes(sizes);

			sizes = asList(14.0,16.0,18.5);
			colors = asList("beige");


			Product piercing4 = new Product("piercing4", "beige sweatshirt","./assets/piercings/piercing4.png",45,4300,"PIERCING","BODY");
			piercing4.setColors(colors);
			piercing4.setSizes(sizes);

			Product piercing5 = new Product("piercing5", "beige sweatshirt","./assets/piercings/piercing5.png",45,4300,"PIERCING","BODY");
			piercing5.setColors(colors);
			piercing5.setSizes(sizes);




//	        DECLARACION DE POST: NOMBRE | TITULO | FOTO | DESCRIPCION | TATUADOR | TIPO DE POST (TATTOO O PIERCING) | FUEGOS (1 al 5)
//	        PARA OBTENER LAS URL DE LAS IMAGENES IR A  subidorImagenes.html Y COPIAR LA URL QUE LES DE EL SWEETALERT

			Post post = new Post("Post de muestra","https://cdn.filestackcontent.com/yOUujPVMQ2yC5IBboLLX", "es un tatuaje","pepito", TATTOO, 4 );
			clientAdmin.addPost(post);




			productService.saveProduct(beanie);
			productService.saveProduct(beanie2);
			productService.saveProduct(sweatshirt);
			productService.saveProduct(sweatshirt2);

			productService.saveProduct(piercing);
			productService.saveProduct(piercing2);
			productService.saveProduct(piercing3);
			productService.saveProduct(piercing4);
			productService.saveProduct(piercing5);

			postService.savePost(post);



			clientService.saveClient(clientAdmin);
			clientService.saveClient(clientAdmin2);


			System.out.println("PROGRAMA INICIADO :D");
		};
	}


}
