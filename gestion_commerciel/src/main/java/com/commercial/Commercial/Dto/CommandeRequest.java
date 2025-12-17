package com.commercial.Commercial.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class CommandeRequest {

    @NotNull(message = "Code commande is required")
    private Integer codeCmd;

    @NotBlank(message = "Client name is required")
    private String client;

    @NotNull(message = "Code produit is required")
    private Integer codePdt;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer qteCmd;

    @NotNull(message = "Date is required")
    private LocalDate dateCmd;

    public CommandeRequest() {
    }

    public CommandeRequest(Integer codeCmd, String client, Integer codePdt, Integer qteCmd, LocalDate dateCmd) {
        this.codeCmd = codeCmd;
        this.client = client;
        this.codePdt = codePdt;
        this.qteCmd = qteCmd;
        this.dateCmd = dateCmd;
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