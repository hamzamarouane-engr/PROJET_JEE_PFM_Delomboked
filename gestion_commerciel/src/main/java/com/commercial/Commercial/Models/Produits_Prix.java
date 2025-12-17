package com.commercial.Commercial.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name = "produits_prix") // optional but recommended
public class Produits_Prix {

    @Id
    @Column(name = "codepdt")
    @NotNull(message = "Code produit is required")
    private Integer codePdt;

    @Column(name = "nompdt", length = 20)
    @NotBlank(message = "Nom produit is required")
    private String nomPdt;

    @Column(name = "descpdt", length = 200)
    private String descPdt;

    @Column(name = "prixpdt")
    @NotNull(message = "Prix produit is required")
    @Positive(message = "Prix must be positive")
    private Integer prixPdt;

    public Produits_Prix() {
    }

    public Produits_Prix(Integer codePdt, String nomPdt, String descPdt, Integer prixPdt) {
        this.codePdt = codePdt;
        this.nomPdt = nomPdt;
        this.descPdt = descPdt;
        this.prixPdt = prixPdt;
    }

    public Integer getCodePdt() {
        return codePdt;
    }

    public void setCodePdt(Integer codePdt) {
        this.codePdt = codePdt;
    }

    public String getNomPdt() {
        return nomPdt;
    }

    public void setNomPdt(String nomPdt) {
        this.nomPdt = nomPdt;
    }

    public String getDescPdt() {
        return descPdt;
    }

    public void setDescPdt(String descPdt) {
        this.descPdt = descPdt;
    }

    public Integer getPrixPdt() {
        return prixPdt;
    }

    public void setPrixPdt(Integer prixPdt) {
        this.prixPdt = prixPdt;
    }
}