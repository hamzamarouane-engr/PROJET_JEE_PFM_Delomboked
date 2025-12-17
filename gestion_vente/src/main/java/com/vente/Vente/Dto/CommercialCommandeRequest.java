package com.vente.Vente.Dto;

import java.time.LocalDate;

public class CommercialCommandeRequest {
    private Long codeCmd;
    private String client;
    private Integer codePdt;
    private Integer qteCmd;
    private LocalDate dateCmd;

    public CommercialCommandeRequest() {
    }

    public CommercialCommandeRequest(Long codeCmd, String client, Integer codePdt, Integer qteCmd, LocalDate dateCmd) {
        this.codeCmd = codeCmd;
        this.client = client;
        this.codePdt = codePdt;
        this.qteCmd = qteCmd;
        this.dateCmd = dateCmd;
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
