package br.com.letscode.starwarsapi.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "INVENTARIO")
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

    public String getId() {
        return id;
    }

    public List<Item> getItens() {
        return itens;
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

    @Override
    public String toString() {
        return "Inventario{" +
                "id='" + id + '\'' +
                ", itens=" + itens +
                '}';
    }
}
