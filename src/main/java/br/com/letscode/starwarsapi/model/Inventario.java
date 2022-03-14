package br.com.letscode.starwarsapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "INVENTARIO")
@Getter
@Setter
@ToString
public class Inventario {

    @Id
    private String id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> itens;

    public Inventario() {
    }

    public Inventario(final List<Item> itens) {
        this.id = UUID.randomUUID().toString();
        this.itens = itens;
    }

    public Integer getQuantidadeItemEspecifico(final String nome) {
        final Integer[] quantidade = {0};

        this.itens.forEach(item -> {
            if(item.getNome().equals(nome)) {
                quantidade[0] = item.getQuantidade();
            }
        });

        return quantidade[0];
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventario)) return false;
        final Inventario that = (Inventario) o;
        return Objects.equals(id, that.id) && Objects.equals(itens, that.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itens);
    }
}
