package br.com.letscode.starwarsapi.service;

import br.com.letscode.starwarsapi.dto.CadastrarRebeldeDTO;
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

    public void criarRebelde(CadastrarRebeldeDTO cadastrarRebeldeDTO) {
        final Localizacao localizacao = new Localizacao("123", "123", "abc");
        final List<Item> itens = Arrays.asList(new Item("arma", 4), new Item("água", 3));
        final Inventario inventario = new Inventario(itens);
        Rebelde rebelde = new Rebelde(cadastrarRebeldeDTO.getNome(),
                cadastrarRebeldeDTO.getIdade(),
                cadastrarRebeldeDTO.getGenero(),
                0,
                false,
                localizacao,
                inventario);
//        final Rebelde rebelde = new Rebelde("Joao", 35, "Masculino", 0, false, localizacao, inventario);
        rebeldesRepository.save(rebelde);
    }

    public List<Rebelde> listarRebeldes(){
        return rebeldesRepository.findAll();
    }

    public void editarLocalizacao(String id){
        List<Rebelde> listaRebeldes = listarRebeldes();
        Rebelde rebeldeEditar;
        for(int i=0; i <= listaRebeldes.size();i++){
            if(listaRebeldes.get(i).getId().equals(id)){
                rebeldeEditar = listaRebeldes.get(i);
                final Localizacao localizacaoNova = new Localizacao("456", "789", "Lua");
                rebeldeEditar.setLocalizacao(localizacaoNova);
                return;
            }
        }

    }

}
