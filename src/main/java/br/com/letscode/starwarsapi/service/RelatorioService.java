package br.com.letscode.starwarsapi.service;

import br.com.letscode.starwarsapi.dto.RelatorioResponseDto;
import br.com.letscode.starwarsapi.model.Rebelde;
import br.com.letscode.starwarsapi.repository.RebeldesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private RebeldesRepository rebeldesRepository;

    public RelatorioResponseDto gerarRelatorio() {
        Integer quantidadeRebeldesCadastrados = rebeldesRepository.findAll().size();
        List<Rebelde> traidores = rebeldesRepository.findAllByTraidor(true);
        List<Rebelde> rebeldes = rebeldesRepository.findAllByTraidor(false);

        Double porcentagemTraidores = calcularPorcentagemTraidores(traidores, quantidadeRebeldesCadastrados);
        Double porcentagemRebeldes = calcularPorcentagemRebeldes(rebeldes, quantidadeRebeldesCadastrados);
        RelatorioResponseDto.QuantidadeMediaRecursosDto quantidadeMediaRecursosDto = calcularMediaRecursosRebeldes(rebeldes);
        Integer pontosPerdidosTraidores = calcularPontosPerdidosTraidores(traidores);

        RelatorioResponseDto relatorio = new RelatorioResponseDto(
                porcentagemTraidores,
                porcentagemRebeldes,
                quantidadeMediaRecursosDto,
                pontosPerdidosTraidores
        );

        return relatorio;
    }

    private Integer calcularPontosPerdidosTraidores(List<Rebelde> traidores) {
        final Integer[] pontosPerdidos = {0};

        traidores.forEach(traidor -> {
            traidor.getInventario().getItens().forEach(item -> {
                pontosPerdidos[0] += (item.getQuantidade() * item.getPontos());
            });
        });

        return pontosPerdidos[0];
    }

    private RelatorioResponseDto.QuantidadeMediaRecursosDto calcularMediaRecursosRebeldes(List<Rebelde> rebeldes) {
        final Integer[] quantidadeArma = {0};
        final Integer[] quantidadeMunicao = {0};
        final Integer[] quantidadeAgua = {0};
        final Integer[] quantidadeComida = {0};
        double mediaArma = 0.0;
        double mediaMunicoes = 0.0;
        double mediaAguas = 0.0;
        double mediaComidas = 0.0;

        rebeldes.forEach(rebelde -> {
            rebelde.getInventario().getItens().forEach(item -> {
                if(item.getNome().equals("Arma")) {
                    quantidadeArma[0] += item.getQuantidade();
                }

                if(item.getNome().equals("Munição")) {
                    quantidadeMunicao[0] += item.getQuantidade();
                }

                if(item.getNome().equals("Água")) {
                    quantidadeAgua[0] += item.getQuantidade();
                }

                if(item.getNome().equals("Comida")) {
                    quantidadeComida[0] += item.getQuantidade();
                }
            });
        });

        Double quantidadeRebeldes = (double) rebeldes.size();

        if(quantidadeRebeldes != 0.0) {
            mediaArma = Double.valueOf(quantidadeArma[0]) / quantidadeRebeldes;
            mediaMunicoes = Double.valueOf(quantidadeMunicao[0]) / quantidadeRebeldes;
            mediaAguas = Double.valueOf(quantidadeAgua[0]) / quantidadeRebeldes;
            mediaComidas = Double.valueOf(quantidadeComida[0]) / quantidadeRebeldes;
        }

        RelatorioResponseDto.QuantidadeMediaRecursosDto dto = new RelatorioResponseDto.QuantidadeMediaRecursosDto(
                mediaArma,
                mediaMunicoes,
                mediaAguas,
                mediaComidas
        );

        return dto;
    }

    private Double calcularPorcentagemTraidores(List<Rebelde> traidores, Integer quantidadeRebeldesCadastrados) {

        if (quantidadeRebeldesCadastrados == 0) {
           return 0.0;
        } else {
            return (traidores.size() / Double.valueOf(quantidadeRebeldesCadastrados));
        }

    }

    private Double calcularPorcentagemRebeldes(List<Rebelde> rebeldes, Integer quantidadeRebeldesCadastrados) {

        if (quantidadeRebeldesCadastrados == 0) {
            return 0.0;
        } else {
            return (rebeldes.size() / Double.valueOf(quantidadeRebeldesCadastrados));
        }

    }

}
