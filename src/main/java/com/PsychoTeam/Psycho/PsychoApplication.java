package com.PsychoTeam.Psycho;
import com.PsychoTeam.Psycho.Models.*;
import com.PsychoTeam.Psycho.repositories.ClientRepository;
import com.PsychoTeam.Psycho.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.PsychoTeam.Psycho.Models.PostType.PIERCING;
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
	public CommandLineRunner initData(ClientService clientService, ProductService productService, PostService postService, TattoerService tattoerService, AppointmentService appointmentService) {
		return (args) -> {

//			DECLARACION DE CLIENTE: NOMBRE | APELLIDO | USERNAME | EMAIL | CONTRASEÑA

			Client clientAdmin = new Client("Ezequiel", "Priotto", "Zeke","zeke@psycho.com",passwordEncoder.encode("admin123") );
			Client clientAdmin2 = new Client("David", "Pereira", "Davey", "davey@psycho.com",passwordEncoder.encode("admin123"));

			clientAdmin.setEnabled(true);
			clientAdmin2.setEnabled(true);

			clientService.saveClient(clientAdmin);
			clientService.saveClient(clientAdmin2);

			Client clientA = new Client("Matias","Romano","romat","r25789@gmail.com",passwordEncoder.encode("prueba123"));
			clientA.setEnabled(true);
			clientService.saveClient(clientA);

			Client clientB = new Client("Hector","Ciabattoni","titi","titic@gmail.com",passwordEncoder.encode("92.3lapopu"));
			clientB.setEnabled(true);
			clientService.saveClient(clientB);

			Client clientC = new Client("Rocio","Altamirano","ra_alt","altamirano335@gmail.com",passwordEncoder.encode("00303456"));
			clientC.setEnabled(true);
			clientService.saveClient(clientC);

			Client clientD = new Client("luna","carreras","lunamia","astrocar",passwordEncoder.encode("lunamia1112"));
			clientD.setEnabled(true);
			clientService.saveClient(clientD);

			Client clientE = new Client("Kimberly","Simmons","keeks","kiss@gmail.com",passwordEncoder.encode("pass2022"));
			clientE.setEnabled(true);
			clientService.saveClient(clientE);

			Client clientF = new Client("Emilia","Clark","beccabec","rbc_personal@gmail.com",passwordEncoder.encode("beca2022"));
			clientF.setEnabled(true);
			clientService.saveClient(clientF);

			Client clientG = new Client("Stephanie","Germanotta","gagalicious","ladygaga@gmail.com",passwordEncoder.encode("lovetatoos123"));
			clientG.setEnabled(true);
			clientService.saveClient(clientG);

			Client clientH = new Client("Susan","Doyle","susie","sususud@gmail.com",passwordEncoder.encode("susan1234"));
			clientH.setEnabled(true);
			clientService.saveClient(clientH);

			Client clientI = new Client("Olivia","Matcher","xxOlivexx","om@gmail.com",passwordEncoder.encode("asdcvb000"));
			clientI.setEnabled(true);
			clientService.saveClient(clientI);

			Client clientJ= new Client("Lizzie","Pells","notLizzie","liz@gmail.com",passwordEncoder.encode("lol4447"));
			clientJ.setEnabled(true);
			clientService.saveClient(clientJ);

			Client clientK = new Client("Kevin","Tran","the_scrib_of_god","kevint@gmail.com",passwordEncoder.encode("hatemetatron"));
			clientK.setEnabled(true);
			clientService.saveClient(clientK);

			Client clientL = new Client("Julian", "Casablancas", "YOLO","julian24@gmail.com",passwordEncoder.encode("mantecol1123"));
			clientL.setEnabled(true);
			clientService.saveClient(clientL);

			Client clientM = new Client("Aaron", "Carter", "Grey","sdsadas@gmail.com",passwordEncoder.encode("5888962"));
			clientM.setEnabled(true);
			clientService.saveClient(clientM);

			Client clientN = new Client("Alex", "Conan", "Grey","notContains@gmail.com",passwordEncoder.encode("6666933"));
			clientN.setEnabled(true);
			clientService.saveClient(clientN);

			Client clientO = new Client("Jake", "Corner", "posivity_vibes","notJake@gmail.com",passwordEncoder.encode("6666933"));
			clientO.setEnabled(true);
			clientService.saveClient(clientO);

			Client clientP = new Client("Veronika", "Mars", "Beyond","imalive@gmail.com",passwordEncoder.encode("6666953"));
			clientP.setEnabled(true);
			clientService.saveClient(clientP);

			Client clientQ = new Client("Anna", "Olsen", "_metal_girl_","kissandaerosmit@gmail.com",passwordEncoder.encode("6666933"));
			clientQ.setEnabled(true);
			clientService.saveClient(clientQ);

			Client clientR = new Client("Dustin", "Looksser", "dust_in","dlw@gmail.com",passwordEncoder.encode("6666933"));
			clientR.setEnabled(true);
			clientService.saveClient(clientR);

			Client clientS = new Client("Lele", "Ponds", "angezuendet","lelelele@gmail.com",passwordEncoder.encode("6666933"));
			clientS.setEnabled(true);
			clientService.saveClient(clientS);

			Client clientT = new Client("max", "tomas", "handlewithcaution","iabarcae@yahoo.es",passwordEncoder.encode("6666933"));
			clientT.setEnabled(true);
			clientService.saveClient(clientT);

			Client clientU = new Client("alex", "bernard", "pr3tty-b4by","maeillanes@hotmail.com",passwordEncoder.encode("6666933"));
			clientU.setEnabled(true);
			clientService.saveClient(clientU);

			Client clientV = new Client("jane", "lucius", "lovescene","osabarca@hotmail.com",passwordEncoder.encode("6666933"));
			clientV.setEnabled(true);
			clientService.saveClient(clientV);

			Client clientW = new Client("Erica", "clanton", "freakerika","Sb.nashxo.sk8@hotmail.com",passwordEncoder.encode("6666933"));
			clientW.setEnabled(true);
			clientService.saveClient(clientW);

			Client clientX = new Client("Anne", "simpson", "abcdef__","vizkala@hotmail.com",passwordEncoder.encode("6666933"));
			clientX.setEnabled(true);
			clientService.saveClient(clientX);

			Client clientY = new Client("Lili", "swan", "uwu","luuuuuuci@hotmail.com",passwordEncoder.encode("6666933"));
			clientY.setEnabled(true);
			clientService.saveClient(clientY);

			Client clientZ = new Client("Elizabeth", "myers", "*-_-af-_-*","aargomedo@hecsa.cl",passwordEncoder.encode("6666933"));
			clientZ.setEnabled(true);
			clientService.saveClient(clientZ);

			Tattoer tattoerA = new Tattoer("David", "Tattoer", "daveytattoo", "daveytattoo@psycho.com", passwordEncoder.encode("daveybtw"));
			tattoerService.saveTattoer(tattoerA);

			long phone = 3186942813L;
			Appointment appointmentA = new Appointment(clientZ, phone, "Arm", "13cm", true, tattoerA, LocalDate.of(2022, 06, 30));
			appointmentService.saveAppointment(appointmentA);

			Appointment appointmentB = new Appointment(clientZ, phone, "Arm", "13cm", true, tattoerA, LocalDate.of(2022, 06, 13));
			appointmentService.saveAppointment(appointmentB);

			Appointment appointmentC = new Appointment(clientZ, phone, "Arm", "13cm", true, tattoerA, LocalDate.of(2022, 06, 20));
			appointmentService.saveAppointment(appointmentC);

			// ----------- CLOTHING

			List<Double> sizes = asList(14.0,16.0);
			List<String> colors = asList("yellow");

			Product beanie = new Product("Beanie", "yellow beanie","./assets/productosPrueba/gorrito1.png",30,2600,"CLOTHING","HAT");
			beanie.setColors(colors);
			beanie.setSizes(sizes);


//			DECLARACION DE PRODUCTO: NOMBRE | DESCRIPCION | FOTO | STOCK | PRECIO | CATEGORIA (CLOTHING O PIERCING) | SUBCATEGORIA
//	        PARA OBTENER LAS URL DE LAS IMAGENES IR A  subidorImagenes.html Y COPIAR LA URL QUE LES DE EL SWEETALERT
//			PARA LOS TAMAÑOS Y COLORES USAR SIZES (O COLORS) = asList(ACA LOS DATOS)


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




			Product sweatshirt2 = new Product("Sweatshirt", "beige sweatshirt","./assets/productosPrueba/remera.png",45,4300,"CLOTHING","SWEATSHIRT");
			sweatshirt2.setColors(colors);
			sweatshirt2.setSizes(sizes);

			sizes = asList(14.0,16.0,18.5);
			colors = asList("beige");


			Product cloth1 = new Product("Goth Hoodie", "Black hoodie with a goth stamp","https://cdn.filestackcontent.com/kZYP1N0ERXCZjf5PFeU4",10,6890,"CLOTHING","SWEATSHIRT");
			cloth1.setColors(colors);
			cloth1.setSizes(sizes);
			productService.saveProduct(cloth1);


			sizes = asList(4.0,5.0,7.0);
			colors = asList("black");

			Product cloth2 = new Product("Calavera Hoodie", "Black hoodie with stamped sleeves","https://cdn.filestackcontent.com/moSCJ8kTU2FZh8B8xvau",5,7500,"CLOTHING","SWEATSHIRT");
			cloth2.setColors(colors);
			cloth2.setSizes(sizes);
			productService.saveProduct(cloth2);

			sizes = asList(1.0,2.0,4.0);
			colors = asList("pink");

			Product cloth3 = new Product("Sad Hoodie", "Pink hoodie","https://cdn.filestackcontent.com/NJd628AeRfGYjGfmpUQr",12,6300,"CLOTHING","SWEATSHIRT");
			cloth3.setColors(colors);
			cloth3.setSizes(sizes);
			productService.saveProduct(cloth3);

			sizes = asList(2.0,5.0,6.0);
			colors = asList("blue");

			Product cloth4 = new Product("Scorpio Hoodie", "Light blue hoodie with a goth spamp","https://cdn.filestackcontent.com/4tBjvXS5WYLVkWDLpMxQ",3,6800,"CLOTHING","SWEATSHIRT");
			cloth4.setColors(colors);
			cloth4.setSizes(sizes);
			productService.saveProduct(cloth4);

			sizes = asList(3.0,4.0);
			colors = asList("white");

			Product cloth5 = new Product("Pandemic Shirt", "White shirt with a black stamp","https://cdn.filestackcontent.com/rTiXnLwCRgaeCsPbpwkw",9,2890,"CLOTHING","T-SHIRT");
			cloth5.setColors(colors);
			cloth5.setSizes(sizes);
			productService.saveProduct(cloth5);

			sizes = asList(1.0);
			colors = asList("gray", "black");

			Product cloth6 = new Product("Advisory Beanie", "Embroidered hat","https://cdn.filestackcontent.com/yTvNuUYpTOiqsPWoifzw",17,1900,"CLOTHING","HAT");
			cloth6.setColors(colors);
			cloth6.setSizes(sizes);
			productService.saveProduct(cloth6);

			sizes = asList(3.0,4.0,5.0);
			colors = asList("black", "white");

			Product cloth7 = new Product("He Shirt", "Shirt with limited edition design","https://cdn.filestackcontent.com/jKlEOcSOWTYLSzLpgjAn",3,3120,"CLOTHING","T-SHIRT");
			cloth7.setColors(colors);
			cloth7.setSizes(sizes);
			productService.saveProduct(cloth7);

			sizes = asList(1.0,2.0,4.0);
			colors = asList("black", "yellow");

			Product cloth8 = new Product("Existencial Dread Shirt", "Printed shirt","https://cdn.filestackcontent.com/9VbhzTsYSbi3x3npAvtn",15,2100,"CLOTHING","T-SHIRT");
			cloth8.setColors(colors);
			cloth8.setSizes(sizes);
			productService.saveProduct(cloth8);

			sizes = asList(1.0,2.0,4.0);
			colors = asList("black", "red", "white");

			Product cloth9 = new Product("Graffity Shirt", "Printed shirt","https://cdn.filestackcontent.com/zEdOykZSRLucay7CF5Wg",9,2680,"CLOTHING","T-SHIRT");
			cloth9.setColors(colors);
			cloth9.setSizes(sizes);
			productService.saveProduct(cloth9);

			sizes = asList(1.0);
			colors = asList("black");

			Product cloth10 = new Product("Checkered bucket hat", "Black and white checkered bucket hat","https://cdn.filestackcontent.com/xg8kbsFMRQOrbbxaRdsU",7,2420,"CLOTHING","HAT");
			cloth10.setColors(colors);
			cloth10.setSizes(sizes);
			productService.saveProduct(cloth10);

			sizes = asList(1.0);
			colors = asList("red");

			Product cloth11 = new Product("Flames bucket hat", "Red flames bucket hat","https://cdn.filestackcontent.com/Ki1AsxtRCuPZmS4h75of",2,2420,"CLOTHING","HAT");
			cloth11.setColors(colors);
			cloth11.setSizes(sizes);
			productService.saveProduct(cloth11);

			sizes = asList(1.0);
			colors = asList("green");

			Product cloth12 = new Product("Billie Eilish Beanie", "Green Billie Eilish beanie","https://cdn.filestackcontent.com/7A0B7t30TlaVSFzC7m8g",12,2900,"CLOTHING","HAT");
			cloth12.setColors(colors);
			cloth12.setSizes(sizes);
			productService.saveProduct(cloth12);

			sizes = asList(1.0);
			colors = asList("brown");

			Product cloth13 = new Product("Cheeta Bucket Hat", "Brown cheeta bucket hat","https://cdn.filestackcontent.com/jM1JNaS3S8e63VoTtE6o",20,2420,"CLOTHING","HAT");
			cloth13.setColors(colors);
			cloth13.setSizes(sizes);
			productService.saveProduct(cloth13);

			sizes = asList(2.0,3.0,4.0);
			colors = asList("black");

			Product cloth14 = new Product("Black and White Shirt", "Black and white limited edition shirt","https://cdn.filestackcontent.com/L6WkCCGPQ2aSxVJOnWH1",4,2890,"CLOTHING","T-SHIRT");
			cloth14.setColors(colors);
			cloth14.setSizes(sizes);
			productService.saveProduct(cloth14);

			sizes = asList(3.0,4.0,5.0);
			colors = asList("yellow", "pink", "white");

			Product cloth15 = new Product("Stencil Shirt", "Printed shirt with stencil design","https://cdn.filestackcontent.com/9v0RDvBqQsqqNH055AC9",16,3120,"CLOTHING","T-SHIRT");
			cloth15.setColors(colors);
			cloth15.setSizes(sizes);
			productService.saveProduct(cloth15);

			sizes = asList(4.0,5.0,6.0);
			colors = asList("white", "red");

			Product cloth16 = new Product("Tiger Shirt", "Printed shirt with tiger design","https://cdn.filestackcontent.com/EatK5Ms6Rlax4h4TwIFd",13,2760,"CLOTHING","T-SHIRT");
			cloth16.setColors(colors);
			cloth16.setSizes(sizes);
			productService.saveProduct(cloth16);

			sizes = asList(2.0,3.0,4.0,5.0,6.0);
			colors = asList("black", "pink", "blue", "white");

			Product cloth17 = new Product("Hands Shirt", "Shirt with tattoo style print","https://cdn.filestackcontent.com/qkZgXwM0QWWnAAxJ3fXz",16,2530,"CLOTHING","T-SHIRT");
			cloth17.setColors(colors);
			cloth17.setSizes(sizes);
			productService.saveProduct(cloth17);

			sizes = asList(4.0,6.0);
			colors = asList("gray", "black");

			Product cloth18 = new Product("Heart Shirt", "Shirt with a heart print","https://cdn.filestackcontent.com/OrYAZg7ORpswccJmSwA0",10,3060,"CLOTHING","T-SHIRT");
			cloth18.setColors(colors);
			cloth18.setSizes(sizes);
			productService.saveProduct(cloth18);

			sizes = asList(1.0);
			colors = asList("orange");

			Product cloth19 = new Product("Tie Dye Beanie", "Orange and purple tie dye beanie","https://cdn.filestackcontent.com/G5ZioZ7SxKxpKTOoVNBS",6,2760,"CLOTHING","HAT");
			cloth19.setColors(colors);
			cloth19.setSizes(sizes);
			productService.saveProduct(cloth19);

			sizes = asList(1.0);
			colors = asList("black", "white", "green");

			Product cloth20 = new Product("Skull Shirt", "Shirt with skull print","https://cdn.filestackcontent.com/uKpBJmHiTUWY8gOkFdU1",14,2320,"CLOTHING","T-SHIRT");
			cloth20.setColors(colors);
			cloth20.setSizes(sizes);
			productService.saveProduct(cloth20);

			/// ------ PIERCING
			sizes = asList(1.2,4.0);
			colors = asList("grey");
			Product piercing = new Product("Polar Star Titanium Labret", "Star shaped labret with zirconia in the center, perfect for healed and healing piercings. ","https://cdn.filestackcontent.com/oJTUG62RTZu6WNi2oiGc",25,3100,"PIERCING","EAR");
			piercing.setColors(colors);
			piercing.setSizes(sizes);
			productService.saveProduct(piercing);


			sizes = asList(1.2,10.0);
			colors = asList("grey");
			Product piercing2 = new Product("Soul Titanium", "Ring decorated with a row of zircons covered with ball details. Clicker ring perfect for your Daith and Septum piercing. Remember that being Implant Grade Titanium you can anodize it in a wide variety of colors.","https://cdn.filestackcontent.com/jhEhMMw2R46tNu4z3MtC",14,4000,"PIERCING","EAR");
			piercing2.setColors(colors);
			piercing2.setSizes(sizes);
			productService.saveProduct(piercing2);


			sizes = asList(1.2,4.0);
			colors = asList("grey");
			Product piercing3 = new Product("Circular Barbell earring Titanium Ring", "Perfect jewel for any of your piercings. Available in various sizes and colors, so that it adapts 100% to what you need and your personality.","https://cdn.filestackcontent.com/Pnh1LPaLRUqSyuwjgFF6",26,2220,"PIERCING","NOSE");
			piercing3.setColors(colors);
			piercing3.setSizes(sizes);
			productService.saveProduct(piercing3);


			sizes = asList(1.2,4.0);
			colors = asList("grey");
			Product piercing4 = new Product("Ball with Titanium Flat Disc", "Ball labret perfect for any of your ear or lip piercings. A versatile jewel, and combinable with all the other jewels you can wear.","https://cdn.filestackcontent.com/ldagbJ1XSwKVBEsECvOD",18,1230,"PIERCING","BODY");
			piercing4.setColors(colors);
			piercing4.setSizes(sizes);
			productService.saveProduct(piercing4);

			sizes = asList(1.2,4.0);
			colors = asList("grey");
			Product piercing5 = new Product("BCR Titanium Ring", "Earing BCR with Ball perfect for any of your piercings, available in different sizes and measurements. And also, being made of Titanium, it can be anodized in various colors to find your perfect design.","https://cdn.filestackcontent.com/FKley2HHRravDJeX5uzC",45,4300,"PIERCING","BODY");
			piercing5.setColors(colors);
			piercing5.setSizes(sizes);
			productService.saveProduct(piercing5);


			sizes = asList(1.0,6.0);
			colors = asList("grey");
			Product piercing6 = new Product("Nostril White Gold","Nostril White Gold 14k for your nose piercing already healed or in the process of healing.","https://cdn.filestackcontent.com/RurrRkR0SeK0aKtoBavz",23,4500,"PIERCING","NOSE");
			piercing6.setSizes(sizes);
			piercing6.setColors(colors);
			productService.saveProduct(piercing6);

			sizes = asList(1.2, 8.0);
			colors = asList("grey");
			Product piercing7 = new Product("Banana Diamonds","Piece with a diamond, made with 316L surgical steel, recommended for Rook, Daith or Vertical Labret already cured or in the process of healing.","https://cdn.filestackcontent.com/2DGxhBE6SMCZ6eGNgNH7",43,1200,"PIERCING","BODY");
			piercing7.setSizes(sizes);
			piercing7.setColors(colors);
			productService.saveProduct(piercing7);


			sizes = asList(14.0);
			colors = asList("grey");
			Product piercing8 = new Product("Surgical Steel Labret","Surgical Steel Labret perfect for any of your ear piercings already healed or in the process of healing.","https://cdn.filestackcontent.com/8U0r0XYpSVqGIESwIfAe",12,3400,"PIERCING","EAR");
			piercing8.setSizes(sizes);
			piercing8.setColors(colors);
			productService.saveProduct(piercing8);


			sizes = asList(2.0,3.0);
			colors = asList("grey");
			Product piercing9 = new Product("Big BCR Piercing","Recommended piece for dilations and dilated septum already cured or in the process of healing.","https://cdn.filestackcontent.com/k7LastX2QnuYTK97egNF",33,1500,"PIERCING","EAR");
			piercing9.setSizes(sizes);
			piercing9.setColors(colors);
			productService.saveProduct(piercing9);

			sizes = asList(1.6,34.0);
			colors = asList("grey");
			Product piercing10 = new Product("Zirconia Industrial Barbell","Perfect for your industrial piercing. Customize your little ear with this unique piece. Possibility of anodizing it in various colors to convey your personality to the jewel.","https://cdn.filestackcontent.com/cv4OYwMQjWe1i7ONBhea",22,1340,"PIERCING","EAR");
			piercing10.setSizes(sizes);
			piercing10.setColors(colors);
			productService.saveProduct(piercing10);

			sizes = asList(14.0,16.0,18.5);
			colors = asList("grey");
			Product piercing11 = new Product("Barbell Titanium Balls","Ball labret perfect for any of your ear or lip piercings. A versatile jewel, and combinable with all the other jewels you can wear.","https://cdn.filestackcontent.com/zxZNndrdQJU1l69m7qKq",15,1000,"PIERCING", "MOUTH");
			piercing11.setSizes(sizes);
			piercing11.setColors(colors);
			productService.saveProduct(piercing11);

			sizes = asList(1.2);
			colors = asList("grey","lightblue");
			Product piercing12 = new Product("Labret Dawn Flower Titanium","Perfect jewel to combine all your piercings and make a perfect design of your little ear. The measurement of the top is 5.4mm Remember that being Implant Grade Titanium it can be anodized in various colors. Choose yours in our dropdown.","https://cdn.filestackcontent.com/zdvZOM0QTQiMZ0VlLNXU",20,2100,"PIERCING","MOUTH");
			piercing12.setSizes(sizes);
			piercing12.setColors(colors);
			productService.saveProduct(piercing12);

			sizes = asList(1.6);
			colors = asList("grey","lightblue");
			Product piercing13 = new Product("Industrial Opal Heart","Piercing for Industrial with heart-shaped balls on the sides. Heart in blue opal. Single measurement: 1.6 mm x 37 mm.","https://cdn.filestackcontent.com/hHy0Xh4SomsZ4BAySsMg",12,3050,"PIERCING","EAR");
			piercing13.setSizes(sizes);
			piercing13.setColors(colors);
			productService.saveProduct(piercing13);

			sizes = asList(1.6);
			colors = asList("grey","lightblue");
			Product piercing14 = new Product("Navel Piercing – Turquoise","Navel piercing with ethnic details and turquoise Opal Stone in the center. Single measurement: 1.6mm x 12mm. Standard navel measurement.","https://cdn.filestackcontent.com/DSK5Jz5DTOqJ6YBVTVIg",24,1540,"PIERCING","BODY");
			piercing14.setSizes(sizes);
			piercing14.setColors(colors);
			productService.saveProduct(piercing14);

			sizes = asList(8.0);
			colors = asList("grey","lightblue");
			Product piercing15 = new Product("Labret Opal Stone Aquamarine","Labret Opal Stone in Aquamarine color for healed piercings. The top is available in various sizes. internal thread","https://cdn.filestackcontent.com/66Kc6RRSEyuHDK2tlGfQ",10,1300,"PIERCING","EAR");
			piercing15.setSizes(sizes);
			piercing15.setColors(colors);
			productService.saveProduct(piercing15);

			sizes = asList(1.2,7.0);
			colors = asList("grey");
			Product piercing16 = new Product("Nebula Titanium Labret","Labret with flat zirconia and decorated with a chain ending with another zirconia.","https://cdn.filestackcontent.com/h5oXezqST0ufhVYJVJss",26,1500,"PIERCING","BODY");
			piercing16.setSizes(sizes);
			piercing16.setColors(colors);
			productService.saveProduct(piercing16);

			sizes = asList(1.6);
			colors = asList("grey");
			Product piercing17 = new Product("Barbell Colors Titanium","Perfect barbell for nipple piercing, with two Opal tips available in various colors. The material is Implant Grade Titanium so you can anodize it in the color you want.","https://cdn.filestackcontent.com/ZVtRZqN9RNdnVuufOV5Q",16,2800,"PIERCING","BODY");
			piercing17.setSizes(sizes);
			piercing17.setColors(colors);
			productService.saveProduct(piercing17);

			sizes = asList(1.6);
			colors = asList("grey");
			Product piercing18 = new Product("Septum Piercing – Hindú","316L Surgical Steel.Septum Piercing. Single measure."," https://cdn.filestackcontent.com/hhZe9lVORdWBNIook2qk",25,1099,"PIERCING","NOSE");
			piercing18.setSizes(sizes);
			piercing18.setColors(colors);
			productService.saveProduct(piercing18);

			sizes = asList(1.6);
			colors = asList("grey");
			Product piercing19 = new Product("Surface with Flat Disc","Surface Bar with Flat Disc. Perfect jewel for your Superficial Piercing or vertical Labret. Available in various sizes and measurements, it can also be anodized in various colors.","https://cdn.filestackcontent.com/rvPoSrFmQmOCH21fLHLm",25,2100,"PIERCING","EAR");
			piercing19.setSizes(sizes);
			piercing19.setColors(colors);
			productService.saveProduct(piercing19);

			sizes = asList(6.0,8.0,10.0,14.0);
			colors = asList("grey");
			Product piercing20 = new Product("Banana Roof Titanium Balls","Labret Roof made of 3.4mm balls perfect for your Rook, Lip and Snug Piercing","https://cdn.filestackcontent.com/0fnSq1S6RZuwTmm7580s",30,1100,"PIERCING","EAR");
			piercing20.setSizes(sizes);
			piercing20.setColors(colors);
			productService.saveProduct(piercing20);



//	        DECLARACION DE POST: NOMBRE | TITULO | FOTO | DESCRIPCION | TATUADOR | TIPO DE POST (TATTOO O PIERCING) | FUEGOS (1 al 5)
//	        PARA OBTENER LAS URL DE LAS IMAGENES IR A  subidorImagenes.html Y COPIAR LA URL QUE LES DE EL SWEETALERT

			Post post = new Post("Post de muestra","https://cdn.filestackcontent.com/yOUujPVMQ2yC5IBboLLX", "es un tatuaje","pepito", TATTOO, 4 );
			clientAdmin.addPost(post);

			Post postA = new Post("Estrella fugaz", "https://cdn.filestackcontent.com/ILA2brq0T86AxPnWxR3y","recorre los cielos de mis noches.","Branko",TATTOO,5);
			clientA.addPost(postA);
			postService.savePost(postA);

			Post postB = new Post("galapagos","https://cdn.filestackcontent.com/NL0w8uU4Qxen6GYYvfvj","con mi novia nos tatuamos","tatuador",TATTOO,3);
			clientB.addPost(postB);
			postService.savePost(postB);

			Post postC =new Post("galapagos-2","https://cdn.filestackcontent.com/Ipxr5WUOQrKHtx4vZVHJ","feliz ♥","Lauteh",TATTOO,5);
			clientC.addPost(postC);
			postService.savePost(postC);

			Post postD = new Post("la luna","https://cdn.filestackcontent.com/AXpDmXq0Q0uiB02eVBOn","mi nombre","Fla",TATTOO,5);
			clientD.addPost(postD);
			postService.savePost(postD);

			Post postE = new Post("selfie","https://cdn.filestackcontent.com/pTsTE8KDTXkdB948qolA","piercing nuevo","celes",PIERCING,4);
			clientE.addPost(postE);
			postService.savePost(postE);

			Post postF = new Post("mano","https://cdn.filestackcontent.com/nQUA1kxTUuOazPMvnug6","new tatto","David",TATTOO,4);
			clientF.addPost(postF);
			postService.savePost(postF);

			Post postG = new Post("freedom","https://cdn.filestackcontent.com/ESZDsqVfSlfD2peBz1gu","love your self","Polli",TATTOO,5);
			clientG.addPost(postG);
			postService.savePost(postG);

			Post postH = new Post("frame","https://cdn.filestackcontent.com/v4AjAK0sQmeM4zbsbMfE","break the limits","Branko",TATTOO,2);
			clientH.addPost(postH);
			postService.savePost(postH);

			Post postI = new Post("betty boop","https://cdn.filestackcontent.com/zOuPzcGuSDWzqwrEg5Ou","love you","cele",TATTOO,4);
			clientI.addPost(postI);
			postService.savePost(postI);

			Post postJ = new Post("I'm darks","https://cdn.filestackcontent.com/0ufiKf41RWW3BQ29rytQ","very darks","Lauteh",PIERCING,5);
			clientJ.addPost(postJ);
			postService.savePost(postJ);

			Post postK = new Post("Bazinga!","https://cdn.filestackcontent.com/mqtFyhXiTcGTJpYaZWBZ","microbiologi","Zeke",TATTOO,5);
			clientK.addPost(postK);
			postService.savePost(postK);

			Post postL = new Post("idk what anime is ","https://cdn.filestackcontent.com/uDYHD81kRtyAaU8ds4fS","loves she","Polli",TATTOO,5);
			clientL.addPost(postL);
			postService.savePost(postL);

			Post postM = new Post("BOO 👻","https://cdn.filestackcontent.com/ycmdkDvRJqPpyNXAtDmA","bood, lol ","Fla",TATTOO,4);
			clientM.addPost(postM);
			postService.savePost(postM);

			Post postN = new Post("It could have been better","https://cdn.filestackcontent.com/zDE88yWMQcewjQeZwvEA","I appreciate the art but...","Branko",TATTOO,3);
			clientN.addPost(postN);
			postService.savePost(postN);

			Post postO = new Post("Erica","https://cdn.filestackcontent.com/pc7q08HGSvuKMpZMkk1q"," it a sheep, but with 2 heads ","Celes",TATTOO,5);
			clientO.addPost(postO);
			postService.savePost(postO);

			Post postP = new Post("muehehe :3","https://cdn.filestackcontent.com/vLMRramBTAuv12EHqpxe","tonge piercing ","Polli",PIERCING,5);
			clientP.addPost(postP);
			postService.savePost(postP);

			Post postQ = new Post("cool","https://cdn.filestackcontent.com/HeyZLP7fSuGJEri1iOQi","both","Fla",PIERCING,5);
			clientQ.addPost(postQ);
			postService.savePost(postQ);

			Post postR = new Post("sweet or not","https://cdn.filestackcontent.com/wV5N8z85SGSB1EiSLsq5","the love is like a cake ","Zeke",TATTOO,5);
			clientR.addPost(postR);
			postService.savePost(postR);

			Post postS = new Post("Casper","https://cdn.filestackcontent.com/XzBzOqkeTZKI7KKG2vvA"," mini casper ","Lauteh",TATTOO,5);
			clientS.addPost(postS);
			postService.savePost(postS);

			Post postT = new Post("peace","https://cdn.filestackcontent.com/Qf6ikcypRmyG3PCpNkP4"," ... ","Lauteh",TATTOO,5);
			clientT.addPost(postT);
			postService.savePost(postT);

			Post postU = new Post("Old school","https://cdn.filestackcontent.com/sPDeUYSiCUzYXPcn10Sg"," I love she ","Lauteh",TATTOO,5);
			clientU.addPost(postU);
			postService.savePost(postU);

			Post postV = new Post("Flowers","https://cdn.filestackcontent.com/7p0QEtPEQk6f7GZBLDmj"," I love she part 2 ","Lauteh",TATTOO,5);
			clientV.addPost(postV);
			postService.savePost(postV);

			Post postW = new Post("both","https://cdn.filestackcontent.com/rbYmIQM7SJqqNzur16ry","it's not a selfie ","Polli",PIERCING,5);
			clientW.addPost(postW);
			postService.savePost(postW);

			Post postX = new Post("infinite","https://cdn.filestackcontent.com/OwJB3tqQjCxz5ckKdxRJ","... ","Lauteh",TATTOO,4);
			clientX.addPost(postX);
			postService.savePost(postX);

			Post postY = new Post("flowers","https://cdn.filestackcontent.com/wWW2uX5sTemQ5n2lavBS"," I love she ","Zeke",TATTOO,5);
			clientY.addPost(postY);
			postService.savePost(postY);

			Post postZ = new Post("darks","https://cdn.filestackcontent.com/VrsWIY0TSk2Qxe3X6Qdb"," I'm gonna kill u ","Pollo",PIERCING,5);
			clientZ.addPost(postZ);
			postService.savePost(postZ);

			Post postZA = new Post("piercing","https://cdn.filestackcontent.com/LyBtRujCQlqhmRAVhQ7Y"," slave ","Fla",PIERCING,5);
			clientZ.addPost(postZA);
			postService.savePost(postZA);


			postService.savePost(post);

			productService.saveProduct(beanie);
			productService.saveProduct(beanie2);
			productService.saveProduct(sweatshirt);







			clientService.saveClient(clientAdmin);
			clientService.saveClient(clientAdmin2);


			System.out.println("PROGRAMA INICIADO :D");
		};
	}


}
