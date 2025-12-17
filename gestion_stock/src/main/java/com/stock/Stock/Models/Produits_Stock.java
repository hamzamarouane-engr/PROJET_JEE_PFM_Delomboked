package com.stock.Stock.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Entity class representing products in stock
 * Table: produits_stock in database G_Stock
 */
@Entity
@Table(name = "produits_stock")
public class Produits_Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codestock")
    private Integer codeStock;

    @Column(name = "codepdt")
    @NotNull(message = "Code produit is required")
    private Integer codePdt;

    @Column(name = "qtepdt")
    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be zero or positive")
    private Integer qtePdt;
    
    @Column(name = "prixpdt")
    @NotNull(message = "Prix produit is required")
    @PositiveOrZero(message = "Prix must be zero or positive")
    private Integer prixPdt;


    public Produits_Stock() {
    }

    public Produits_Stock(Integer codeStock, Integer codePdt, Integer qtePdt) {
        this.codeStock = codeStock;
        this.codePdt = codePdt;
        this.qtePdt = qtePdt;
    }

    public Integer getCodeStock() {
        return codeStock;
    }

    public void setCodeStock(Integer codeStock) {
        this.codeStock = codeStock;
    }

    public Integer getCodePdt() {
        return codePdt;
    }

    public void setCodePdt(Integer codePdt) {
        this.codePdt = codePdt;
    }

    public Integer getQtePdt() {
        return qtePdt;
    }

    public void setQtePdt(Integer qtePdt) {
        this.qtePdt = qtePdt;
    }
    
    public Integer getPrixPdt() {
        return prixPdt;
    }

    public void setPrixPdt(Integer prixPdt) {
        this.prixPdt = prixPdt;
    }
}
