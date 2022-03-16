package br.com.letscode.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioResponseDto {

    private Double porcentagemTraidores;
    private Double porcentagemRebeldes;
    private QuantidadeMediaRecursosDto quantidadeMediaRecursos;
    private Integer pontosPerdidosTraidores;

    @JsonCreator
    public RelatorioResponseDto(@JsonProperty("porcentagemTraidores") final Double porcentagemTraidores,
                                @JsonProperty("porcentagemRebeldes") final Double porcentagemRebeldes,
                                @JsonProperty("quantidadeMediaRecursos") final QuantidadeMediaRecursosDto quantidadeMediaRecursos,
                                @JsonProperty("pontosPerdidosTraidores") final Integer pontosPerdidosTraidores) {
        this.porcentagemTraidores = porcentagemTraidores;
        this.porcentagemRebeldes = porcentagemRebeldes;
        this.quantidadeMediaRecursos = quantidadeMediaRecursos;
        this.pontosPerdidosTraidores = pontosPerdidosTraidores;
    }

    @Getter
    @Setter
    public static class QuantidadeMediaRecursosDto {
        private Double mediaArmas;
        private Double mediaMunicoes;
        private Double mediaAguas;
        private Double mediaComidas;

        @JsonCreator
        public QuantidadeMediaRecursosDto(@JsonProperty("mediaArmas") final Double mediaArmas,
                                          @JsonProperty("mediaMunicoes") final Double mediaMunicoes,
                                          @JsonProperty("mediaAguas") final Double mediaAguas,
                                          @JsonProperty("mediaComidas") final Double mediaComidas) {
            this.mediaArmas = mediaArmas;
            this.mediaMunicoes = mediaMunicoes;
            this.mediaAguas = mediaAguas;
            this.mediaComidas = mediaComidas;
        }
    }
}
