/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anarchy.gui.back.commande;

import com.anarchy.entities.Commande;
import com.anarchy.services.CommandeService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author rayen
 */
public class Mange extends Form {

    public static Commande currentCommande = null;

    Label ConfirmLable;
    ComboBox<String> confirmCB;

    Button manageButton;

    Form previous;

    public Mange(Form previous) {
        super("Modifier");
        confirmCB = new ComboBox<>("YES", "NO");
        if (currentCommande.isConfirmeAdmin().equals("true")) {
            confirmCB.setSelectedIndex(0);
        } else {
            confirmCB.setSelectedIndex(1);
        }

        this.previous = previous;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

        ConfirmLable = new Label("ConfirmAdmin : ");
        ConfirmLable.setUIID("labelDefault");

        manageButton = new Button("Modifier");
        manageButton.setUIID("buttonMain");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
                ConfirmLable, confirmCB,
                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        manageButton.addActionListener(action -> {
            Commande commande = new Commande();
            if (confirmCB.getSelectedItem().equals("YES")) {
                currentCommande.setConfirmeAdmin("true");
            } else {
                currentCommande.setConfirmeAdmin("false");
            }
            int responseCode = CommandeService.getInstance().edit(currentCommande);

            if (responseCode == 200) {
                Dialog.show("Succés", "commande modifié avec succes", new Command("Ok"));
                showBackAndRefresh();
            } else {
                Dialog.show("Erreur", "Erreur de modification de commande. Code d'erreur : " + responseCode, new Command("Ok"));
            }
        });

    }

    private void showBackAndRefresh() {
        previous.showBack();
        ((com.anarchy.gui.back.commande.ShowAll) previous).refresh();
        previous.refreshTheme();
    }

}
