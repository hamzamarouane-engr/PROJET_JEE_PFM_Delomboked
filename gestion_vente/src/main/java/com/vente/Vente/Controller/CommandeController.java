package com.vente.Vente.Controller;

import com.vente.Vente.Dto.CommandeRequest;
import com.vente.Vente.Dto.CommandeResponse;
import com.vente.Vente.Dto.ProduitDTO;
import com.vente.Vente.Service.CommandeService;
import com.vente.Vente.Service.CommercialService;
import com.vente.Vente.Service.PDFService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/commandes")
public class CommandeController {

    private static final Logger log =
            LoggerFactory.getLogger(CommandeController.class);

    private final CommandeService commandeService;
    private final CommercialService commercialService;
    private final PDFService pdfService;

    public CommandeController(CommandeService commandeService,
                              CommercialService commercialService,
                              PDFService pdfService) {
        this.commandeService = commandeService;
        this.commercialService = commercialService;
        this.pdfService = pdfService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("commandes", commandeService.getAllCommandes());
        return "commandes/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        List<ProduitDTO> produits = commercialService.getAllProduits();
        model.addAttribute("produits", produits);
        model.addAttribute("commandeRequest", new CommandeRequest());
        return "commandes/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute CommandeRequest request,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("produits", commercialService.getAllProduits());
            return "commandes/create";
        }

        CommandeResponse commande = commandeService.createCommande(request);
        redirectAttributes.addFlashAttribute(
                "success", "Commande créée : " + commande.getCodeCmd());

        return "redirect:/commandes/" + commande.getCodeCmd();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("commande", commandeService.getCommandeById(id));
        return "commandes/detail";
    }

    @GetMapping("/{id}/invoice")
    public ResponseEntity<byte[]> invoice(@PathVariable Long id) {

        CommandeResponse commande = commandeService.getCommandeById(id);
        byte[] pdf = pdfService.generateInvoice(commande);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(
                "attachment", "facture_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }
}
