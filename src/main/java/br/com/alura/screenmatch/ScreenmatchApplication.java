package br.com.alura.screenmatch;

import br.com.alura.screenmatch.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Primeiro projeto Spring sem web");

		ConsumoApi consumoApi = new ConsumoApi();
		String json = consumoApi.obterDados("http://www.omdbapi.com/?t=divertidamente&apikey=777ff11c");
		System.out.println(json);
	}
}
