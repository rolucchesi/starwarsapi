package br.com.letscode.starwarsapi.entity;

import br.com.letscode.starwarsapi.util.RebeldeGenero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RebeldeEntity {
    private Long id;
    private String nome;
    private Integer idade;
    private RebeldeGenero genero;
    //private Localizacao localizacao;
    //private Inventario inventario;
}
