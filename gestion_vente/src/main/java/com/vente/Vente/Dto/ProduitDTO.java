package com.vente.Vente.Dto;

public class ProduitDTO {

    private Integer codeStock;
    private Integer codePdt;
    private Integer qtePdt;
    private Integer prixPdt;   // 🔥 OBLIGATOIRE

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
