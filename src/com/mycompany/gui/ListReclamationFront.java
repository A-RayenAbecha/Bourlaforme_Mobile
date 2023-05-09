package com.mycompany.gui;

import com.mycomany.entities.Reclamation;
import com.mycompany.services.ReclamationService;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListReclamationFront extends Form {

    Form previous;

    public static Reclamation currentReclamation = null;
    Button addBtn;

    TextField searchTF;
    ArrayList<Component> componentModels;


    public ListReclamationFront(Form previous) {
        super("Reclamations", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);


        ArrayList<Reclamation> listReclamations = ReclamationService.getInstance().getAll();
        componentModels = new ArrayList<>();

        searchTF = new TextField("", "Chercher reclamation par Message");
        searchTF.addDataChangedListener((d, t) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            for (Reclamation reclamation : listReclamations) {
                if (reclamation.getMessage().toLowerCase().startsWith(searchTF.getText().toLowerCase())) {
                    Component model = makeReclamationModel(reclamation);
                    this.add(model);
                    componentModels.add(model);
                }
            }
            this.revalidate();
        });
        this.add(searchTF);


        if (listReclamations.size() > 0) {
            for (Reclamation reclamation : listReclamations) {
                Component model = makeReclamationModel(reclamation);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentReclamation = null;
            new GestionReclamationFront(this).show();
        });

    }

    Label userIdLabel, coachIdLabel, clubIdLabel, articleIdLabel, dateLabel, etatLabel, reponseLabel, typeLabel, messageLabel;


    private Container makeModelWithoutButtons(Reclamation reclamation) {
        Container reclamationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        reclamationModel.setUIID("containerRounded");


        userIdLabel = new Label("UserId : " + reclamation.getUserId());
        userIdLabel.setUIID("labelDefault");

        coachIdLabel = new Label("CoachId : " + reclamation.getCoachId());
        coachIdLabel.setUIID("labelDefault");

        clubIdLabel = new Label("ClubId : " + reclamation.getClubId());
        clubIdLabel.setUIID("labelDefault");

        articleIdLabel = new Label("ArticleId : " + reclamation.getArticleId());
        articleIdLabel.setUIID("labelDefault");

        dateLabel = new Label("Date : " + new SimpleDateFormat("dd-MM-yyyy").format(reclamation.getDate()));
        dateLabel.setUIID("labelDefault");

        etatLabel = new Label("Etat : " + reclamation.getEtat());
        etatLabel.setUIID("labelDefault");

        reponseLabel = new Label("Reponse : " + reclamation.getReponse());
        reponseLabel.setUIID("labelDefault");

        typeLabel = new Label("Type : " + reclamation.getType());
        typeLabel.setUIID("labelDefault");

        messageLabel = new Label("Message : " + reclamation.getMessage());
        messageLabel.setUIID("labelDefault");


        reclamationModel.addAll(

                userIdLabel, coachIdLabel, clubIdLabel, articleIdLabel, dateLabel, etatLabel, reponseLabel, typeLabel, messageLabel
        );

        return reclamationModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeReclamationModel(Reclamation reclamation) {

        Container reclamationModel = makeModelWithoutButtons(reclamation);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentReclamation = reclamation;
            new GestionReclamationFront(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce reclamation ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = ReclamationService.getInstance().delete(reclamation.getId());

                if (responseCode == 200) {
                    currentReclamation = null;
                    dlg.dispose();
                    reclamationModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du reclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        reclamationModel.add(btnsContainer);

        return reclamationModel;
    }

}