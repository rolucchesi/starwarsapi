package br.com.letscode.starwarsapi.service;

import br.com.letscode.starwarsapi.dto.CadastrarRebeldeDTO;
import br.com.letscode.starwarsapi.dto.NegociarItensRequestDto;
import br.com.letscode.starwarsapi.model.Inventario;
import br.com.letscode.starwarsapi.model.Item;
import br.com.letscode.starwarsapi.model.Localizacao;
import br.com.letscode.starwarsapi.model.Rebelde;
import br.com.letscode.starwarsapi.repository.RebeldesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RebeldeService {

    @Autowired
    private RebeldesRepository rebeldesRepository;

    public Rebelde criarRebelde(CadastrarRebeldeDTO cadastrarRebeldeDTO) {
        validarDadosDto(cadastrarRebeldeDTO);

        Rebelde rebelde = criarEntidadeDoDto(cadastrarRebeldeDTO);
        rebeldesRepository.save(rebelde);

        return rebelde;
    }

    public List<Rebelde> listarRebeldes() {
        return rebeldesRepository.findAll();
    }

    public void editarLocalizacao(String id) {
        Rebelde rebeldeEditar = rebeldesRepository.getById(id);
        final Localizacao localizacaoNova = new Localizacao("456", "789", "Lua");
        rebeldeEditar.setLocalizacao(localizacaoNova);
        rebeldesRepository.save(rebeldeEditar);
    }

    public void negociarItens(NegociarItensRequestDto negociarItensRequestDto) {
        Rebelde rebelde1 = rebeldesRepository.findById(negociarItensRequestDto.getRebelde1().getId()).orElseThrow(() -> {
            throw new RuntimeException("Não existe rebelde com este id: " + negociarItensRequestDto.getRebelde1().getId());
        });

        Rebelde rebelde2 = rebeldesRepository.findById(negociarItensRequestDto.getRebelde2().getId()).orElseThrow(() -> {
            throw new RuntimeException("Não existe rebelde com este id: " + negociarItensRequestDto.getRebelde2().getId());
        });

        checarRebeldeTraidor(rebelde1);
        checarRebeldeTraidor(rebelde2);

        List<Item> itensTrocaRebelde1 = negociarItensRequestDto.getRebelde1().getItens();
        List<Item> itensTrocaRebelde2 = negociarItensRequestDto.getRebelde2().getItens();

        checarSeRebeldePossuiItensParaNegocio(rebelde1, itensTrocaRebelde1);
        checarSeRebeldePossuiItensParaNegocio(rebelde2, itensTrocaRebelde2);

        checarQuantidadeDePontosTroca(itensTrocaRebelde1, itensTrocaRebelde2);

        atualizarInventario(rebelde1, itensTrocaRebelde1, itensTrocaRebelde2);
        atualizarInventario(rebelde2, itensTrocaRebelde2, itensTrocaRebelde1);
    }

    private void checarRebeldeTraidor(Rebelde rebelde) {
        if(rebelde.getTraidor()) {
            throw new RuntimeException("Não é possível fazer negociação com rebeldes traidores");
        }
    }

    private void checarSeRebeldePossuiItensParaNegocio(Rebelde rebelde, List<Item> listaTroca) {
        List<Item> listaItensRebelde = rebelde.getInventario().getItens();
        Integer quantidadeDeItensParaTroca = listaTroca.size();
        final Integer[] validador = {0};

        listaTroca.forEach(item -> {
            listaItensRebelde.forEach(itemRebelde -> {
                if (itemRebelde.getNome().equals(item.getNome())) {
                    if (itemRebelde.getQuantidade() < item.getQuantidade()) {
                        throw new RuntimeException("As informações de itens para troca foram inválidas.");
                    } else {
                        validador[0] += 1;
                    }
                }
            });
        });

        if (!quantidadeDeItensParaTroca.equals(validador[0])) {
            throw new RuntimeException("As informações de itens para troca foram inválidas.");
        }
    }

    private void checarQuantidadeDePontosTroca(List<Item> itensTrocaRebelde1, List<Item> itensTrocaRebelde2) {
        final Integer[] pontosTrocaRebelde1 = {0};
        final Integer[] pontosTrocaRebelde2 = {0};

        itensTrocaRebelde1.forEach(item -> pontosTrocaRebelde1[0] += (item.getQuantidade() * item.getQuantidadePontos(item.getNome())));
        itensTrocaRebelde2.forEach(item -> pontosTrocaRebelde2[0] += (item.getQuantidade() * item.getQuantidadePontos(item.getNome())));

        if (!pontosTrocaRebelde1[0].equals(pontosTrocaRebelde2[0])) {
            throw new RuntimeException("A quantidade de pontos para a troca dos itens não é igual.");
        }
    }

    private void atualizarInventario(Rebelde rebelde, List<Item> itensTrocaRebeldeRetirar, List<Item> itensTrocaRebeldeAdicionar) {
        List<Item> listaItensRebelde = rebelde.getInventario().getItens();

        itensTrocaRebeldeRetirar.forEach(item -> {
            listaItensRebelde.forEach(itemRebelde -> {
                if (itemRebelde.getNome().equals(item.getNome())) {
                    itemRebelde.setQuantidade(itemRebelde.getQuantidade() - item.getQuantidade());
                }
            });
        });

        itensTrocaRebeldeAdicionar.forEach(item -> {
            boolean[] possuiItem = {false};

            listaItensRebelde.forEach(itemRebelde -> {
                if (itemRebelde.getNome().equals(item.getNome())) {
                    possuiItem[0] = true;
                }
            });

            if (possuiItem[0]) {
                listaItensRebelde.forEach(itemRebelde -> {
                    if (itemRebelde.getNome().equals(item.getNome())) {
                        itemRebelde.setQuantidade(itemRebelde.getQuantidade() + item.getQuantidade());
                    }
                });
            } else {
                Item itemAdicionar = new Item(item.getNome(), item.getQuantidade());
                listaItensRebelde.add(itemAdicionar);
            }
        });

        rebelde.getInventario().setItens(listaItensRebelde);

        rebeldesRepository.save(rebelde);
    }

    private Rebelde criarEntidadeDoDto(CadastrarRebeldeDTO cadastrarRebeldeDTO) {
        Localizacao localizacao = new Localizacao(
                cadastrarRebeldeDTO.getLocalizacao().getLatitude(),
                cadastrarRebeldeDTO.getLocalizacao().getLongitude(),
                cadastrarRebeldeDTO.getLocalizacao().getNome());

        List<Item> itens = new ArrayList<>();

        cadastrarRebeldeDTO.getInventario().getItens().forEach(item -> {
            Item item1 = new Item(item.getNome(), item.getQuantidade());
            itens.add(item1);
        });

        Inventario inventario = new Inventario(itens);

        Rebelde rebelde = new Rebelde(cadastrarRebeldeDTO.getNome(),
                cadastrarRebeldeDTO.getIdade(),
                cadastrarRebeldeDTO.getGenero(),
                0,
                false,
                localizacao,
                inventario);

        return rebelde;
    }

    private void validarDadosDto(CadastrarRebeldeDTO cadastrarRebeldeDTO) {
        if (cadastrarRebeldeDTO.getNome().trim().isBlank()) {
            throw new RuntimeException("Não é possível criar um rebelde sem nome");
        }

        if (cadastrarRebeldeDTO.getGenero().trim().isBlank()) {
            throw new RuntimeException("Não é possível criar um rebelde sem gênero");
        }

        if (cadastrarRebeldeDTO.getLocalizacao().getLatitude().trim().isBlank()
                || cadastrarRebeldeDTO.getLocalizacao().getLongitude().trim().isBlank()
                || cadastrarRebeldeDTO.getLocalizacao().getNome().trim().isBlank()) {
            throw new RuntimeException("Não é possível criar um rebelde sem informações de localização");
        }

        if (cadastrarRebeldeDTO.getInventario().getItens().isEmpty()) {
            throw new RuntimeException("Não é possível criar um rebelde com inventário vazio");
        }

    }

}
