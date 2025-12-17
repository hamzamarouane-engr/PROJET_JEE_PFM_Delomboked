package com.commercial.Commercial.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name = "tous_commandes") // recommandé
public class Tous_Commandes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codetouscmd")
    private Integer codeTousCmd;

    @Column(name = "codecmd")
    @NotNull(message = "Code commande is required")
    private Integer codeCmd;

    @Column(name = "client", length = 20)
    @NotBlank(message = "Client name is required")
    private String client;

    @Column(name = "codepdt")
    @NotNull(message = "Code produit is required")
    private Integer codePdt;

    @Column(name = "qtecmd")
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer qteCmd;

    @Column(name = "datecmd")
    @NotNull(message = "Date is required")
    private LocalDate dateCmd;

    public Tous_Commandes() {
    }

    public Tous_Commandes(Integer codeTousCmd, Integer codeCmd, String client, Integer codePdt, Integer qteCmd,
            LocalDate dateCmd) {
        this.codeTousCmd = codeTousCmd;
        this.codeCmd = codeCmd;
        this.client = client;
        this.codePdt = codePdt;
        this.qteCmd = qteCmd;
        this.dateCmd = dateCmd;
    }

    public Integer getCodeTousCmd() {
        return codeTousCmd;
    }

    public void setCodeTousCmd(Integer codeTousCmd) {
        this.codeTousCmd = codeTousCmd;
    }

    public Integer getCodeCmd() {
        return codeCmd;
    }

    public void setCodeCmd(Integer codeCmd) {
        this.codeCmd = codeCmd;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Integer getCodePdt() {
        return codePdt;
    }

    public void setCodePdt(Integer codePdt) {
        this.codePdt = codePdt;
    }

    public Integer getQteCmd() {
        return qteCmd;
    }

    public void setQteCmd(Integer qteCmd) {
        this.qteCmd = qteCmd;
    }

    public LocalDate getDateCmd() {
        return dateCmd;
    }

    public void setDateCmd(LocalDate dateCmd) {
        this.dateCmd = dateCmd;
    }
}
