package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadoEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=777ff11c";

    public void exibeMenu() {
        System.out.println("Digite o nome da série para buscar: ");
        var nomeSerie = scanner.nextLine();

        // Monta a URL da API, substituindo espaços por '+' e adicionando a chave da API
        String json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        // Converte o JSON recebido para um objeto da classe DadosSerie
        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);

        // Mostra as informações básicas da série
        System.out.println(dadosSerie);

        // Cria uma lista para armazenar os dados de todas as temporadas
        List<DadosTemporada> temporadas = new ArrayList<>();

        // Faz uma busca para cada temporada da série (começando da temporada 1)
        for (int i = 1; i < dadosSerie.totalTemporadas(); i++) {
            // Monta a URL para buscar os dados da temporada específica
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);

            // Converte o JSON da temporada em um objeto DadosTemporada
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);

            // Adiciona a temporada na lista
            temporadas.add(dadosTemporada);
        }

        // Exibe no console todas as temporadas e seus dados
        temporadas.forEach(System.out::println);

        // Para cada temporada, percorre todos os episódios e imprime o título de cada episódio
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadoEpisodio> dadoEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        dadoEpisodios.stream()
                .filter(e ->
                        !e.avaliacao()
                        .equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadoEpisodio::avaliacao)
                .reversed()).limit(5)
                .forEach(System.out::println);
    }

}
