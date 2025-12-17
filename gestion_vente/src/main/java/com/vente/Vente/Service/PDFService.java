package com.vente.Vente.Service;

import com.vente.Vente.Dto.CommandeResponse;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PDFService {

    private static final Logger log =
            LoggerFactory.getLogger(PDFService.class);

    public byte[] generateInvoice(CommandeResponse commande) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("FACTURE"));
        document.add(new Paragraph("Commande N°: " + commande.getCodeCmd()));
        document.add(new Paragraph("Client: " + commande.getClient()));
        document.add(new Paragraph("Produit: " + commande.getNomPdt()));
        document.add(new Paragraph("Quantité: " + commande.getQteCmd()));
        document.add(new Paragraph("Prix unitaire: " + commande.getPrixUnitaire()));
        document.add(new Paragraph("Total: " + commande.getMontantTotal()));

        document.close();

        return baos.toByteArray();
    }
}
