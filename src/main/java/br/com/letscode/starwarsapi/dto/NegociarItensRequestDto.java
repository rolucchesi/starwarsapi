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
public class NegociarItensRequestDto {

    private RebeldeNegocioDto rebelde1;
    private RebeldeNegocioDto rebelde2;

    @JsonCreator
    public NegociarItensRequestDto(@JsonProperty("rebelde1") RebeldeNegocioDto rebelde1,
                                   @JsonProperty("rebelde2") RebeldeNegocioDto rebelde2) {
        this.rebelde1 = rebelde1;
        this.rebelde2 = rebelde2;
    }
}
