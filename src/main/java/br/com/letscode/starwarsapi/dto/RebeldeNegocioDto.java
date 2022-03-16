package br.com.letscode.starwarsapi.dto;

import br.com.letscode.starwarsapi.model.Item;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RebeldeNegocioDto {

    private String id;
    private List<ItemRequestDto> itens;

    @JsonCreator
    public RebeldeNegocioDto(@JsonProperty("id") final String id,
                             @JsonProperty("itens") final List<ItemRequestDto> itens) {
        this.id = id;
        this.itens = itens;
    }

    public List<Item> converterItemRequestDtoParaItem(final List<ItemRequestDto> itemRequestDtoList) {
        return itemRequestDtoList
                .stream()
                .map(itemDto -> new Item(itemDto.getNome(), itemDto.getQuantidade()))
                .collect(Collectors.toList());
    }
}
