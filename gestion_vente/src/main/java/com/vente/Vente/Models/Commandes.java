package com.vente.Vente.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

/**
 * Entity representing a customer order in the sales system.
 * This entity stores local order information and integrates with
 * gestion_commercial and gestion_stock microservices.
 */
@Entity
@Table(name = "commandes")
public class Commandes {

    @Id
    @Column(name = "codecmd")
    private Long codeCmd;

    @Column(name = "client", nullable = false, length = 100)
    @NotBlank(message = "Client name is required")
    private String client;

    @Column(name = "codepdt", nullable = false)
    @NotNull(message = "Product code is required")
    private Integer codePdt;

    @Column(name = "nompdt", length = 100)
    private String nomPdt;

    @Column(name = "qtecmd", nullable = false)
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer qteCmd;

    @Column(name = "prixunitaire")
    private Integer prixUnitaire;

    @Column(name = "montanttotal")
    private Integer montantTotal;

    @Column(name = "datecmd", nullable = false)
    @NotNull(message = "Order date is required")
    private LocalDateTime dateCmd;

    @Column(name = "status", length = 20)
    private String status; // PENDING, COMPLETED, CANCELLED

    @Column(name = "createdby", length = 50)
    private String createdBy;

    public Commandes() {
    }

    public Commandes(Long codeCmd, String client, Integer codePdt, String nomPdt, Integer qteCmd, Integer prixUnitaire,
            Integer montantTotal, LocalDateTime dateCmd, String status, String createdBy) {
        this.codeCmd = codeCmd;
        this.client = client;
        this.codePdt = codePdt;
        this.nomPdt = nomPdt;
        this.qteCmd = qteCmd;
        this.prixUnitaire = prixUnitaire;
        this.montantTotal = montantTotal;
        this.dateCmd = dateCmd;
        this.status = status;
        this.createdBy = createdBy;
    }

    @PrePersist
    protected void onCreate() {
        if (dateCmd == null) {
            dateCmd = LocalDateTime.now();
        }
        if (status == null) {
            status = "PENDING";
        }
        if (montantTotal == null && prixUnitaire != null && qteCmd != null) {
            montantTotal = prixUnitaire * qteCmd;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (prixUnitaire != null && qteCmd != null) {
            montantTotal = prixUnitaire * qteCmd;
        }
    }

    public Long getCodeCmd() {
        return codeCmd;
    }

    public void setCodeCmd(Long codeCmd) {
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

    public String getNomPdt() {
        return nomPdt;
    }

    public void setNomPdt(String nomPdt) {
        this.nomPdt = nomPdt;
    }

    public Integer getQteCmd() {
        return qteCmd;
    }

    public void setQteCmd(Integer qteCmd) {
        this.qteCmd = qteCmd;
    }

    public Integer getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Integer prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Integer montantTotal) {
        this.montantTotal = montantTotal;
    }

    public LocalDateTime getDateCmd() {
        return dateCmd;
    }

    public void setDateCmd(LocalDateTime dateCmd) {
        this.dateCmd = dateCmd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
