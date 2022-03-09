package br.com.letscode.starwarsapi.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ITENS")
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

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getPontos() {
        return pontos;
    }

    public Integer getQuantidade() {
        return quantidade;
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

    private Integer getQuantidadePontos(final String nome) {
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        final Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(nome, item.nome) && Objects.equals(pontos, item.pontos) && Objects.equals(quantidade, item.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, pontos, quantidade);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", pontos=" + pontos +
                ", quantidade=" + quantidade +
                '}';
    }
}
