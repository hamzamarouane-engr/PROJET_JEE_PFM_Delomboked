package com.vente.Vente.Dto;

import java.time.LocalDateTime;

public class CommandeRequest {

    private Integer codePdt;
    private Integer qteCmd;
    private String client;
    private LocalDateTime dateCmd;

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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public LocalDateTime getDateCmd() {
        return dateCmd;
    }

    public void setDateCmd(LocalDateTime dateCmd) {
        this.dateCmd = dateCmd;
    }
}
