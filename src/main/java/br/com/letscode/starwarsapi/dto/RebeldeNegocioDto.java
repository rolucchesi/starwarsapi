package br.com.letscode.starwarsapi.dto;

import br.com.letscode.starwarsapi.model.Item;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RebeldeNegocioDto {

    private String id;
    private List<Item> itens;

    @JsonCreator
    public RebeldeNegocioDto(@JsonProperty("id") String id,
                             @JsonProperty("itens") List<Item> itens) {
        this.id = id;
        this.itens = itens;
    }
}
