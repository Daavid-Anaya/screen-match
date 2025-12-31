// package com.david.screenmatch;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.david.screenmatch.principal.Principal;
// import com.david.screenmatch.repository.SerieRepository;

// @SpringBootApplication
// public class ScreenmatchApplicationConsola implements CommandLineRunner{
// 	@Autowired
// 	private SerieRepository serieRepository;
// 	public static void main(String[] args) {
// 		SpringApplication.run(ScreenmatchApplicationConsola.class, args);
// 	}

// 	@Override
// 	public void run(String... args) throws Exception {
// 		Principal principal = new Principal(serieRepository);
// 		principal.mostrarMenu();
// 	}
// }