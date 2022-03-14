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
    public RelatorioResponseDto(@JsonProperty("porcentagemTraidores") Double porcentagemTraidores,
                                @JsonProperty("porcentagemRebeldes") Double porcentagemRebeldes,
                                @JsonProperty("quantidadeMediaRecursos") QuantidadeMediaRecursosDto quantidadeMediaRecursos,
                                @JsonProperty("pontosPerdidosTraidores") Integer pontosPerdidosTraidores) {
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
        public QuantidadeMediaRecursosDto(@JsonProperty("mediaArmas") Double mediaArmas,
                                          @JsonProperty("mediaMunições") Double mediaMunicoes,
                                          @JsonProperty("mediaÁguas") Double mediaAguas,
                                          @JsonProperty("mediaComidas") Double mediaComidas) {
            this.mediaArmas = mediaArmas;
            this.mediaMunicoes = mediaMunicoes;
            this.mediaAguas = mediaAguas;
            this.mediaComidas = mediaComidas;
        }
    }
}
