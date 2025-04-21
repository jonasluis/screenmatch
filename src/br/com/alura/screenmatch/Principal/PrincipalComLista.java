package br.com.alura.screenmatch.Principal;

import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;
import br.com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;

public class PrincipalComLista {
    public static void main(String[] args) {
        Filme thor = new Filme("Thor: Ragnarok", 1970);
        thor.avalia(10);
        Filme vingadores = new Filme("Vingadores Ultimato", 2023);
        vingadores.avalia(10);
        Filme filmeJonas = new Filme("Jonas", 2023);
        filmeJonas.avalia(8);
        Serie lucifer = new Serie("Lucifer", 2000);
        lucifer.avalia(10);
        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(thor);
        lista.add(vingadores);
        lista.add(filmeJonas);
        lista.add(lucifer);

        for (Titulo item: lista){
            System.out.println(item);
            if (item instanceof Filme filme){
                System.out.println("Classificação " + filme.getClassificacao());
            }
        }
        Collections.sort(lista);
        System.out.println(lista);


        ArrayList<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("Jonas");
        buscaPorArtista.add("Luis");
        buscaPorArtista.add("Silva");
        buscaPorArtista.add("Ana");
        System.out.println(buscaPorArtista);
        Collections.sort(buscaPorArtista);
        System.out.println(buscaPorArtista);

    }
}
