/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anarchy.gui.back.commande;

import com.anarchy.entities.Commande;
import com.anarchy.services.CommandeService;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author rayen
 */
public class ShowAll extends Form {

    public static Commande currentCommande = null;
    Label idLabel, montantLabel, dateLabel, ConfirmeAdminLabel;
    Button modifBtn, deleteBtn;
    Container btnsContainer;

    public ShowAll(Form previous) {
        super("Commande", new BoxLayout(BoxLayout.Y_AXIS));

        addGUIs();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        this.refreshTheme();
    }

    private void addGUIs() {

        ArrayList<Commande> listCommandes = CommandeService.getInstance().getAll();
        if (listCommandes.size() > 0) {
            for (Commande commande : listCommandes) {
                this.add(makeCommandeModel(commande));
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private Component makeCommandeModel(Commande commande) {
        Container commandeModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        commandeModel.setUIID("containerRounded");

        idLabel = new Label("id : " + commande.getId());
        idLabel.setUIID("labelDefault");
        dateLabel = new Label("date : " + commande.getDate());
        dateLabel.setUIID("labelDefault");
        montantLabel = new Label("Montant : " + commande.getMontant());
        montantLabel.setUIID("labelDefault");
        ConfirmeAdminLabel = new Label("confirme admin : " + commande.isConfirmeAdmin());
        ConfirmeAdminLabel.setUIID("labelDefault");

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        modifBtn = new Button("modifier confirmation");
        modifBtn.setUIID("buttonMain");
        modifBtn.addActionListener(action -> {
            Mange.currentCommande = commande;
            new Mange(this).show();
        });
        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonDanger");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce commande ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = CommandeService.getInstance().deleteCommande(commande.getId());
                if (responseCode == 200) {
                    currentCommande = null;
                    dlg.dispose();
                    commandeModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du commande. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.CENTER, modifBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        commandeModel.addAll(
                idLabel,
                dateLabel,
                montantLabel,
                ConfirmeAdminLabel,
                btnsContainer
        );

        return commandeModel;
    }
}
