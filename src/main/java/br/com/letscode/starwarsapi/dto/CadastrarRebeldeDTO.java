package br.com.letscode.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CadastrarRebeldeDTO {

    private String nome;
    private Integer idade;
    private String genero;
    private LocalizacaoRequestDto localizacao;
    private InventarioRequestDto inventario;

    @JsonCreator
    public CadastrarRebeldeDTO(@JsonProperty("nome") String nome,
                               @JsonProperty("idade") Integer idade,
                               @JsonProperty("genero") String genero,
                               @JsonProperty("localizacao") LocalizacaoRequestDto localizacao,
                               @JsonProperty("inventario") InventarioRequestDto inventario) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.localizacao = localizacao;
        this.inventario = inventario;
    }
}

