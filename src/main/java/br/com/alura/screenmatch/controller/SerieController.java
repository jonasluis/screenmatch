package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {
  @Autowired
  private SerieService serieService;

  @GetMapping()
  public List<SerieDTO> obterSeries() {
    return serieService.obterTodasAsSeries();
  }

  @GetMapping("/top5")
  public List<SerieDTO> obterTop5Series(){
    return serieService.obterTop5Series();
  }

  @GetMapping("/lancamentos")
  public List<SerieDTO> obterLancamentos(){
    return serieService.obterLancamentos();
  }

  @GetMapping("/{id}")
  public SerieDTO obterSeriePorId(@PathVariable Long id){
    return serieService.obterSeriePorId(id);
  }

  @GetMapping("/{id}/temporadas/todas")
  List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id){
    return serieService.obterTodasAsTemporadas(id);
  }

  @GetMapping("/{id}/temporadas/{numero}")
  public  List<EpisodioDTO> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero){
     return serieService.obterTemporadaPorNumero(id, numero);
  }

  @GetMapping("/categoria/{nomeGenero}")
  public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String nomeGenero){
    return serieService.obterSeriesPorCategoria(nomeGenero);
  }

  @GetMapping("/{id}/temporadas/top")
  public  List<EpisodioDTO> obterTopEpisodios(@PathVariable Long id) {
    return serieService.obterTop5Episodios(id);
  }

}
