package br.com.letscode.starwarsapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ITENS")
@Getter
@Setter
@ToString
public class Item {

    @Id
    private String id;

    @Column
    private String nome;

    @Column
    private Integer pontos;

    @Column
    private Integer quantidade;

    public Item() {
    }

    public Item(final String nome, final Integer quantidade) {
        this.id = UUID.randomUUID().toString();
        this.nome = validaNomeItem(nome);
        this.quantidade = quantidade;
        this.pontos = getQuantidadePontos(this.nome);
    }

    private String validaNomeItem(final String nome) {
        if (nome.equalsIgnoreCase("arma")) {
            return "Arma";
        } else if (nome.equalsIgnoreCase("munição")) {
            return "Munição";
        } else if (nome.equalsIgnoreCase("água")) {
            return "Água";
        } else if (nome.equalsIgnoreCase("comida")) {
            return "Comida";
        } else {
            throw new RuntimeException("O item de nome: " + nome + " não existe! Por favor insira um item válido.");
        }
    }

    public Integer getQuantidadePontos(final String nome) {
        switch (nome) {
            case "Arma":
                return 4;
            case "Munição":
                return 3;
            case "Água":
                return 2;
            case "Comida":
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(nome, item.nome) && Objects.equals(pontos, item.pontos) && Objects.equals(quantidade, item.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, pontos, quantidade);
    }
}
