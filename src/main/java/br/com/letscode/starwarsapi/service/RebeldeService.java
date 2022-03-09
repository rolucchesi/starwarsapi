package br.com.letscode.starwarsapi.service;

import br.com.letscode.starwarsapi.model.*;
import br.com.letscode.starwarsapi.repository.RebeldesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RebeldeService {

    @Autowired
    private RebeldesRepository rebeldesRepository;

    public void criarRebelde() {
        final Localizacao localizacao = new Localizacao("123", "123", "abc");
        final List<Item> itens = Arrays.asList(new Item("arma", 4), new Item("água", 3));
        final Inventario inventario = new Inventario(itens);
        final Rebelde rebelde = new Rebelde("Joao", 35, "Masculino", 0, false, localizacao, inventario);

        rebeldesRepository.save(rebelde);
    }

}
