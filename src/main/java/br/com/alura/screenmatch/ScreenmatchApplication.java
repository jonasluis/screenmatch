package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadoEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
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
		String json = consumoApi.obterDados("http://www.omdbapi.com/?t=lucifer&apikey=777ff11c");
		System.out.println(json);
		ConverteDados converteDados = new ConverteDados();
		DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
		System.out.println(dadosSerie);
		json = consumoApi.obterDados("https://www.omdbapi.com/?t=lucifer&season=1&episode=2&apikey=777ff11c");
		DadoEpisodio dadoEpisodio = converteDados.obterDados(json, DadoEpisodio.class);
		System.out.println(dadoEpisodio);

	}
}
