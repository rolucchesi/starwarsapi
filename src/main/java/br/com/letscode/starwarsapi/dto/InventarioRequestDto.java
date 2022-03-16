package br.com.letscode.starwarsapi.dto;

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
public class InventarioRequestDto {

    private List<ItemRequestDto> itens;

    @JsonCreator
    public InventarioRequestDto(@JsonProperty("itens") List<ItemRequestDto> itens) {
        this.itens = itens;
    }
}
