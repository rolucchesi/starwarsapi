package br.com.letscode.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LocalizacaoRequestDto {

    @NotNull
    private String latitude;
    @NotNull
    private String longitude;
    @NotNull
    private String nome;

    @JsonCreator
    public LocalizacaoRequestDto(@JsonProperty("latitude") final String latitude,
                                 @JsonProperty("longitude") final String longitude,
                                 @JsonProperty("nome") final String nome) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
    }
}
