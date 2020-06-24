package com.test2.cars.entities;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    @Size(max = 255)
    private String nome;

    // TODO create a custom annotation which validate based on an Enum
    @Column(name = "type")
    @NotNull
    @Pattern(regexp = "^(Luxo|Esportivo|Clássicos)")
    private String tipo;

    @Column(name = "description")
    @NotEmpty
    @NotNull
    private String descricao;

    @Column(name = "imageUrl")
    @URL
    private String urlFoto;

    public void from(CarEntity otherCar) {
        if (otherCar.getNome() != null) {
            this.setNome(otherCar.getNome());
        }

        if (otherCar.getTipo() != null) {
            this.setTipo(otherCar.getTipo());
        }

        if (otherCar.getDescricao() != null) {
            this.setDescricao(otherCar.getDescricao());
        }

        if (otherCar.getUrlFoto() != null) {
            this.setUrlFoto(otherCar.getUrlFoto());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarEntity)) return false;
        CarEntity carEntity = (CarEntity) o;
        return getId().equals(carEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
