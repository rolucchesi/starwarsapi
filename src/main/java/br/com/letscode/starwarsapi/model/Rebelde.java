package br.com.letscode.starwarsapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "REBELDES")
@Getter
@Setter
@ToString
public class Rebelde {

    @Id
    private String id;

    @Column
    private String nome;

    @Column
    private Integer idade;

    @Column
    private String genero;

    @Column
    private Integer contagemTraidor;

    @Column
    private Boolean traidor;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Localizacao localizacao;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Inventario inventario;

    public Rebelde() {
    }

    public Rebelde(final String nome,
                   final Integer idade,
                   final String genero,
                   final Integer contagemTraidor,
                   final Boolean traidor,
                   final Localizacao localizacao,
                   final Inventario inventario) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.contagemTraidor = contagemTraidor;
        this.traidor = traidor;
        this.localizacao = localizacao;
        this.inventario = inventario;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Rebelde)) return false;
        final Rebelde rebelde = (Rebelde) o;
        return Objects.equals(id, rebelde.id) && Objects.equals(nome, rebelde.nome) && Objects.equals(idade, rebelde.idade) && genero == rebelde.genero && Objects.equals(contagemTraidor, rebelde.contagemTraidor) && Objects.equals(traidor, rebelde.traidor) && Objects.equals(localizacao, rebelde.localizacao) && Objects.equals(inventario, rebelde.inventario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, idade, genero, contagemTraidor, traidor, localizacao, inventario);
    }
}
