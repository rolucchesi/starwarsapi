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
public class ItemRequestDto {

    private String nome;
    private Integer quantidade;

    @JsonCreator
    public ItemRequestDto(@JsonProperty("nome") String nome,
                          @JsonProperty("quantidade") Integer quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }
}
