package br.com.letscode.starwarsapi.repository;

import br.com.letscode.starwarsapi.model.Inventario;
import br.com.letscode.starwarsapi.model.Item;
import br.com.letscode.starwarsapi.model.Localizacao;
import br.com.letscode.starwarsapi.model.Rebelde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//extends JpaRepository<Rebelde, String>

@Repository
public interface RebeldesRepository extends JpaRepository<Rebelde, String>  {

    //dados mockados
//    private static List<Rebelde> rebelde = new ArrayList<>();
//    private static Localizacao localizacao = new Localizacao("a","a","Rodrigo");
//    private static Item item = new Item("Bonece",2);
//    private static List<Item> itemLista = Arrays.asList(item);
//    private static Inventario inventario = new Inventario(itemLista);
//
//    static {
//        rebelde.addAll(Arrays.asList(new Rebelde("Rodrigo",10,"Masculino",2,true, localizacao,inventario )));
//    }

}
