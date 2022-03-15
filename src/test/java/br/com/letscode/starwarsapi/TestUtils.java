package br.com.letscode.starwarsapi;

import br.com.letscode.starwarsapi.model.Inventario;
import br.com.letscode.starwarsapi.model.Item;
import br.com.letscode.starwarsapi.model.Localizacao;
import br.com.letscode.starwarsapi.model.Rebelde;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static Rebelde criarRebelde1() {
        final Localizacao localizacao = new Localizacao("123", "456", "galaxia");
        final List<Item> itensRebelde = new ArrayList<>();

        itensRebelde.add(new Item("Arma", 4));
        itensRebelde.add(new Item("Munição", 4));
        itensRebelde.add(new Item("Água", 4));
        itensRebelde.add(new Item("Comida", 4));

        final Inventario inventarioRebelde = new Inventario(itensRebelde);

        final Rebelde rebelde = new Rebelde("João",
                33,
                "Masculino",
                2,
                false,
                localizacao,
                inventarioRebelde);

        rebelde.setId("1");

        return rebelde;
    }

    public static Rebelde criarRebelde2() {
        final Localizacao localizacao = new Localizacao("123", "456", "galaxia");
        final List<Item> itensRebelde = new ArrayList<>();

        itensRebelde.add(new Item("Arma", 5));
        itensRebelde.add(new Item("Munição", 5));
        itensRebelde.add(new Item("Água", 5));
        itensRebelde.add(new Item("Comida", 5));

        final Inventario inventarioRebelde = new Inventario(itensRebelde);

        final Rebelde rebelde = new Rebelde("Maria",
                28,
                "Feminino",
                0,
                false,
                localizacao,
                inventarioRebelde);

        rebelde.setId("2");

        return rebelde;
    }

    public static Rebelde criarRebelde3() {
        final Localizacao localizacao = new Localizacao("123", "456", "galaxia");
        final List<Item> itensRebelde = new ArrayList<>();

        itensRebelde.add(new Item("Arma", 3));
        itensRebelde.add(new Item("Munição", 6));
        itensRebelde.add(new Item("Água", 9));
        itensRebelde.add(new Item("Comida", 12));

        final Inventario inventarioRebelde = new Inventario(itensRebelde);

        final Rebelde rebelde = new Rebelde("Pedro",
                22,
                "Masculino",
                0,
                false,
                localizacao,
                inventarioRebelde);

        rebelde.setId("3");

        return rebelde;
    }

    public static Rebelde criarRebeldeTraidor() {
        final Localizacao localizacao = new Localizacao("123", "456", "galaxia");
        final List<Item> itensRebelde = new ArrayList<>();

        itensRebelde.add(new Item("Arma", 8));
        itensRebelde.add(new Item("Munição", 8));
        itensRebelde.add(new Item("Água", 8));
        itensRebelde.add(new Item("Comida", 8));

        final Inventario inventarioRebelde = new Inventario(itensRebelde);

        final Rebelde rebelde = new Rebelde("Caio",
                37,
                "Masculino",
                3,
                true,
                localizacao,
                inventarioRebelde);

        rebelde.setId("4");

        return rebelde;
    }

}
