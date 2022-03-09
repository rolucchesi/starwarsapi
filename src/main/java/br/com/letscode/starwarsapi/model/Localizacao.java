package br.com.letscode.starwarsapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "LOCALIZACAO")
public class Localizacao {

    @Id
    private String id;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @Column
    private String nome;

    public Localizacao() {
    }

    public Localizacao(final String latitude,
                       final String longitude,
                       final String nome) {
        this.id = UUID.randomUUID().toString();
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Localizacao)) return false;
        final Localizacao that = (Localizacao) o;
        return Objects.equals(id, that.id) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude, nome);
    }

    @Override
    public String toString() {
        return "Localizacao{" +
                "id='" + id + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
