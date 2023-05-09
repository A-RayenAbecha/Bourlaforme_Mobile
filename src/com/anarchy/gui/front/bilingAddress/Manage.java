/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anarchy.gui.front.bilingAddress;

import com.anarchy.entities.BillingAddress;
import com.anarchy.services.CheckoutService;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author rayen
 */
public class Manage extends Form {

    Label nomLabel, descriptionLabel, emailLabel, addressLabel, phoneLabel;
    TextField nomTF, descriptionTF, emailTF, addressTF, phoneTF;

    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super("Ajouter");
        this.previous = previous;
        addGUIs();
        addActions();
        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

        nomLabel = new Label("nom ");
        descriptionLabel = new Label("description ");
        emailLabel = new Label("email ");
        addressLabel = new Label("address ");
        phoneLabel = new Label("phone ");

        nomTF = new TextField();
        nomTF.setHint("Tapez le type");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez le type");
        emailTF = new TextField();
        emailTF.setHint("Tapez le type");
        addressTF = new TextField();
        addressTF.setHint("Tapez le type");
        phoneTF = new TextField();
        phoneTF.setHint("Tapez le type");

        manageButton = new Button("Ajouter");

        manageButton.setUIID("buttonMain");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
                nomLabel, nomTF,
                descriptionLabel, descriptionTF,
                addressLabel, addressTF,
                emailLabel, emailTF,
                phoneLabel, phoneTF,
                manageButton
        );
        this.addAll(container);
    }

    private void addActions() {

        manageButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                BillingAddress ba = new BillingAddress();
                ba.setAddress(addressTF.getText());
                ba.setDescription(descriptionTF.getText());
                ba.setEmail(emailTF.getText());
                ba.setNom(nomTF.getText());
                ba.setPhone((int) Float.parseFloat(phoneTF.getText()));
                int responseCode = CheckoutService.getInstance().chekout(ba);
                if (responseCode == 200) {
                    new com.anarchy.gui.front.panier.Payment(this).show();
                } else {
                    Dialog.show("Erreur", "errur d'ajout de billing address. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });

    }

    private void showBackAndRefresh() {
        ((com.anarchy.gui.front.panier.ShowAll) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {

        if (addressTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le type", new Command("Ok"));
            return false;
        }
        if (descriptionTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le type", new Command("Ok"));
            return false;
        }
        if (emailTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le type", new Command("Ok"));
            return false;
        }
        if (nomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le type", new Command("Ok"));
            return false;
        }

        try {
            Float.parseFloat(phoneTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", phoneTF.getText() + " n'est pas un nombre valide (duree)", new Command("Ok"));
            return false;
        }

        return true;
    }
}
