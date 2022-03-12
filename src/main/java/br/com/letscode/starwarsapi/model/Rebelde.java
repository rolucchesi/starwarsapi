package br.com.letscode.starwarsapi.model;

import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "REBELDES")
@Setter
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

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getContagemTraidor() {
        return contagemTraidor;
    }

    public Boolean getTraidor() {
        return traidor;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public Inventario getInventario() {
        return inventario;
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

    @Override
    public String toString() {
        return "Rebelde{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", genero=" + genero +
                ", contagemTraidor=" + contagemTraidor +
                ", traidor=" + traidor +
                ", localizacao=" + localizacao +
                ", inventario=" + inventario +
                '}';
    }
}
