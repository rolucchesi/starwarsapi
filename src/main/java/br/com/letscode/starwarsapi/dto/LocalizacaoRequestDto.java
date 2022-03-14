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
public class LocalizacaoRequestDto {

    private String latitude;
    private String longitude;
    private String nome;

    @JsonCreator
    public LocalizacaoRequestDto(@JsonProperty("latitude") String latitude,
                                 @JsonProperty("longitude") String longitude,
                                 @JsonProperty("nome") String nome) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
    }
}
