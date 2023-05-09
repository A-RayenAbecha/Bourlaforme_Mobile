package com.anarchy.gui;


import com.anarchy.entities.Reclamation;
import com.anarchy.services.ReclamationService;
import com.anarchy.utils.AlertUtils;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class GestionReclamationAdmin extends Form {


    Reclamation currentReclamation;

    TextField userIdTF;
    TextField coachIdTF;
    TextField clubIdTF;
    TextField articleIdTF;
    TextField etatTF;
    TextField reponseTF;
    TextField typeTF;
    TextField messageTF;
    Label userIdLabel;
    Label coachIdLabel;
    Label clubIdLabel;
    Label articleIdLabel;
    Label etatLabel;
    Label reponseLabel;
    Label typeLabel;
    Label messageLabel;
    PickerComponent dateTF;


    Button manageButton;

    Form previous;

    public GestionReclamationAdmin(Form previous) {
        super(ListReclamationAdmin.currentReclamation == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentReclamation = ListReclamationAdmin.currentReclamation;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {


        userIdLabel = new Label("UserId : ");
        userIdLabel.setUIID("labelDefault");
        userIdTF = new TextField();
        userIdTF.setHint("Tapez le userId");


        coachIdLabel = new Label("CoachId : ");
        coachIdLabel.setUIID("labelDefault");
        coachIdTF = new TextField();
        coachIdTF.setHint("Tapez le coachId");


        clubIdLabel = new Label("ClubId : ");
        clubIdLabel.setUIID("labelDefault");
        clubIdTF = new TextField();
        clubIdTF.setHint("Tapez le clubId");


        articleIdLabel = new Label("ArticleId : ");
        articleIdLabel.setUIID("labelDefault");
        articleIdTF = new TextField();
        articleIdTF.setHint("Tapez le articleId");


        dateTF = PickerComponent.createDate(null).label("Date");


        etatLabel = new Label("Etat : ");
        etatLabel.setUIID("labelDefault");
        etatTF = new TextField();
        etatTF.setHint("Tapez le etat");


        reponseLabel = new Label("Reponse : ");
        reponseLabel.setUIID("labelDefault");
        reponseTF = new TextField();
        reponseTF.setHint("Tapez le reponse");


        typeLabel = new Label("Type : ");
        typeLabel.setUIID("labelDefault");
        typeTF = new TextField();
        typeTF.setHint("Tapez le type");


        messageLabel = new Label("Message : ");
        messageLabel.setUIID("labelDefault");
        messageTF = new TextField();
        messageTF.setHint("Tapez le message");


        if (currentReclamation == null) {


            manageButton = new Button("Ajouter");
        } else {
            userIdTF.setText(String.valueOf(currentReclamation.getUserId()));
            coachIdTF.setText(String.valueOf(currentReclamation.getCoachId()));
            clubIdTF.setText(String.valueOf(currentReclamation.getClubId()));
            articleIdTF.setText(String.valueOf(currentReclamation.getArticleId()));
            dateTF.getPicker().setDate(currentReclamation.getDate());
            etatTF.setText(currentReclamation.getEtat());
            reponseTF.setText(currentReclamation.getReponse());
            typeTF.setText(currentReclamation.getType());
            messageTF.setText(currentReclamation.getMessage());


            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(

                userIdLabel, userIdTF,
                coachIdLabel, coachIdTF,
                clubIdLabel, clubIdTF,
                articleIdLabel, articleIdTF,
                dateTF,
                etatLabel, etatTF,
                reponseLabel, reponseTF,
                typeLabel, typeTF,
                messageLabel, messageTF,

                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        if (currentReclamation == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ReclamationService.getInstance().add(
                            new Reclamation(


                                    (int) Float.parseFloat(userIdTF.getText()),
                                    (int) Float.parseFloat(coachIdTF.getText()),
                                    (int) Float.parseFloat(clubIdTF.getText()),
                                    (int) Float.parseFloat(articleIdTF.getText()),
                                    dateTF.getPicker().getDate(),
                                    "traité",
                                    reponseTF.getText(),
                                    typeTF.getText(),
                                    messageTF.getText()
                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Reclamation ajouté avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de reclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ReclamationService.getInstance().edit(
                            new Reclamation(
                                    currentReclamation.getId(),


                                    (int) Float.parseFloat(userIdTF.getText()),
                                    (int) Float.parseFloat(coachIdTF.getText()),
                                    (int) Float.parseFloat(clubIdTF.getText()),
                                    (int) Float.parseFloat(articleIdTF.getText()),
                                    dateTF.getPicker().getDate(),
                                    etatTF.getText(),
                                    reponseTF.getText(),
                                    typeTF.getText(),
                                    messageTF.getText()

                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Reclamation modifié avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de reclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((ListReclamationAdmin) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (userIdTF.getText().equals("")) {
            Dialog.show("Avertissement", "UserId vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(userIdTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", userIdTF.getText() + " n'est pas un nombre valide (userId)", new Command("Ok"));
            return false;
        }


        if (coachIdTF.getText().equals("")) {
            Dialog.show("Avertissement", "CoachId vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(coachIdTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", coachIdTF.getText() + " n'est pas un nombre valide (coachId)", new Command("Ok"));
            return false;
        }


        if (clubIdTF.getText().equals("")) {
            Dialog.show("Avertissement", "ClubId vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(clubIdTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", clubIdTF.getText() + " n'est pas un nombre valide (clubId)", new Command("Ok"));
            return false;
        }


        if (articleIdTF.getText().equals("")) {
            Dialog.show("Avertissement", "ArticleId vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(articleIdTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", articleIdTF.getText() + " n'est pas un nombre valide (articleId)", new Command("Ok"));
            return false;
        }


        if (dateTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la date", new Command("Ok"));
            return false;
        }


        if (etatTF.getText().equals("")) {
            Dialog.show("Avertissement", "Etat vide", new Command("Ok"));
            return false;
        }


        if (reponseTF.getText().equals("")) {
            Dialog.show("Avertissement", "Reponse vide", new Command("Ok"));
            return false;
        }


        if (typeTF.getText().equals("")) {
            Dialog.show("Avertissement", "Type vide", new Command("Ok"));
            return false;
        }


        if (messageTF.getText().equals("")) {
            Dialog.show("Avertissement", "Message vide", new Command("Ok"));
            return false;
        }


        return true;
    }
}