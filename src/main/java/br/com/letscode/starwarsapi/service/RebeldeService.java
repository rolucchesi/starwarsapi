package br.com.letscode.starwarsapi.service;

import br.com.letscode.starwarsapi.dto.CadastrarRebeldeDTO;
import br.com.letscode.starwarsapi.dto.LocalizacaoRequestDto;
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

    public Rebelde criarRebelde(final CadastrarRebeldeDTO cadastrarRebeldeDTO) {
        validarDadosDto(cadastrarRebeldeDTO);

        final Rebelde rebelde = criarEntidadeDoDto(cadastrarRebeldeDTO);
        rebeldesRepository.save(rebelde);

        return rebelde;
    }

    public List<Rebelde> listarRebeldes() {
        return rebeldesRepository.findAll();
    }

    public Rebelde editarLocalizacao(final String id, final LocalizacaoRequestDto localizacaoRequestDto) {
        final Rebelde rebelde = rebeldesRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Não existe rebelde com este id: " + id);
        });

        final Localizacao localizacaoNova = new Localizacao(
                localizacaoRequestDto.getLatitude(),
                localizacaoRequestDto.getLongitude(),
                localizacaoRequestDto.getNome());

        rebelde.setLocalizacao(localizacaoNova);

        rebeldesRepository.save(rebelde);

        return rebelde;
    }

    public void acusarTraidor(final String id) {
        final Rebelde rebelde = rebeldesRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Não existe rebelde com este id: " + id);
        });

        rebelde.setContagemTraidor(rebelde.getContagemTraidor() + 1);

        if(rebelde.getContagemTraidor() >= 3) {
            rebelde.setTraidor(Boolean.TRUE);
        }

        rebeldesRepository.save(rebelde);
    }

    public void negociarItens(final NegociarItensRequestDto negociarItensRequestDto) {
        final Rebelde rebelde1 = rebeldesRepository.findById(negociarItensRequestDto.getRebelde1().getId()).orElseThrow(() -> {
            throw new RuntimeException("Não existe rebelde com este id: " + negociarItensRequestDto.getRebelde1().getId());
        });

        final Rebelde rebelde2 = rebeldesRepository.findById(negociarItensRequestDto.getRebelde2().getId()).orElseThrow(() -> {
            throw new RuntimeException("Não existe rebelde com este id: " + negociarItensRequestDto.getRebelde2().getId());
        });

        checarRebeldeTraidor(rebelde1);
        checarRebeldeTraidor(rebelde2);

        final List<Item> itensTrocaRebelde1 = negociarItensRequestDto.getRebelde1().getItens();
        final List<Item> itensTrocaRebelde2 = negociarItensRequestDto.getRebelde2().getItens();

        checarSeRebeldePossuiItensParaNegocio(rebelde1, itensTrocaRebelde1);
        checarSeRebeldePossuiItensParaNegocio(rebelde2, itensTrocaRebelde2);

        checarQuantidadeDePontosTroca(itensTrocaRebelde1, itensTrocaRebelde2);

        atualizarInventario(rebelde1, itensTrocaRebelde1, itensTrocaRebelde2);
        atualizarInventario(rebelde2, itensTrocaRebelde2, itensTrocaRebelde1);
    }

    private void checarRebeldeTraidor(final Rebelde rebelde) {
        if(rebelde.getTraidor().equals(Boolean.TRUE)) {
            throw new RuntimeException("Não é possível fazer negociação com rebeldes traidores");
        }
    }

    private void checarSeRebeldePossuiItensParaNegocio(final Rebelde rebelde, final List<Item> listaTroca) {
        final List<Item> listaItensRebelde = rebelde.getInventario().getItens();
        final Integer quantidadeDeItensParaTroca = listaTroca.size();
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

    private void checarQuantidadeDePontosTroca(final List<Item> itensTrocaRebelde1, final List<Item> itensTrocaRebelde2) {
        final Integer[] pontosTrocaRebelde1 = {0};
        final Integer[] pontosTrocaRebelde2 = {0};

        itensTrocaRebelde1.forEach(item -> pontosTrocaRebelde1[0] += (item.getQuantidade() * item.getQuantidadePontos(item.getNome())));
        itensTrocaRebelde2.forEach(item -> pontosTrocaRebelde2[0] += (item.getQuantidade() * item.getQuantidadePontos(item.getNome())));

        if (!pontosTrocaRebelde1[0].equals(pontosTrocaRebelde2[0])) {
            throw new RuntimeException("A quantidade de pontos para a troca dos itens não é igual.");
        }
    }

    private void atualizarInventario(final Rebelde rebelde, final List<Item> itensTrocaRebeldeRetirar, final List<Item> itensTrocaRebeldeAdicionar) {
        final List<Item> listaItensRebelde = rebelde.getInventario().getItens();

        itensTrocaRebeldeRetirar.forEach(item -> {
            listaItensRebelde.forEach(itemRebelde -> {
                if (itemRebelde.getNome().equals(item.getNome())) {
                    itemRebelde.setQuantidade(itemRebelde.getQuantidade() - item.getQuantidade());
                }
            });
        });

        itensTrocaRebeldeAdicionar.forEach(item -> {
            final boolean[] possuiItem = {false};

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
                final Item itemAdicionar = new Item(item.getNome(), item.getQuantidade());
                listaItensRebelde.add(itemAdicionar);
            }
        });

        rebelde.getInventario().setItens(listaItensRebelde);

        rebeldesRepository.save(rebelde);
    }

    private Rebelde criarEntidadeDoDto(final CadastrarRebeldeDTO cadastrarRebeldeDTO) {
        final Localizacao localizacao = new Localizacao(cadastrarRebeldeDTO.getLocalizacao());
        final Inventario inventario = new Inventario(cadastrarRebeldeDTO.getInventario());

        final Rebelde rebelde = new Rebelde(cadastrarRebeldeDTO.getNome(),
                cadastrarRebeldeDTO.getIdade(),
                cadastrarRebeldeDTO.getGenero(),
                0,
                false,
                localizacao,
                inventario);

        return rebelde;
    }

    private void validarDadosDto(final CadastrarRebeldeDTO cadastrarRebeldeDTO) {
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
