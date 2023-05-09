/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anarchy.gui.front.panier;

import com.anarchy.entities.Article;
import com.anarchy.services.CommandeService;
import com.anarchy.services.PanierService;
import com.anarchy.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;


/**
 *
 * @author rayen
 */
public class ShowAll extends Form {

    public static Article currentArticle = null;
    Label nameLabel, priceLabel, quantityLabel, totaleLabel;
    Button plusBtn, minesBtn, deleteBtn, addBtn;
    Container btnsContainer;
    Resources theme = UIManager.initFirstTheme("/theme");
    float totale = 0;

    public ShowAll(Form previous) {
        super("Panier", new BoxLayout(BoxLayout.Y_AXIS));
        topModel();
        addGUIs();
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void topModel() {
        totale = 0;
        Container topModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ArrayList<Article> listArticle = PanierService.getInstance().getAll();
        if (listArticle.size() > 0) {
            for (Article article : listArticle) {
                totale += article.getPrix() * article.getQuantity();
            }
        }
        topModel.setUIID("containerRounded");
        topModel.add(new Label("Totale: " + totale));
        this.add(topModel);
    }

    public void refresh() {
        this.removeAll();
        topModel();
        addGUIs();
        this.refreshTheme();
    }

    private void addGUIs() {
        totale = 0;
        ArrayList<Article> listArticle = PanierService.getInstance().getAll();
        if (listArticle.size() > 0) {
            for (Article article : listArticle) {
                this.add(makeCommandeModel(article));
                totale += article.getPrix() * article.getQuantity();
            }
            addBtn = new Button("Checkout");
            addBtn.setUIID("buttonWhiteCenter");
            this.add(addBtn);
            addActions();

        } else {
            this.add(new Label("Aucune donnée"));
        }

    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("confirmer"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                Payment.prix = totale;
                new com.anarchy.gui.front.bilingAddress.Manage(this).show();

                dlg.dispose();
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);

        });

    }

    private Component makeCommandeModel(Article article) {
        ImageViewer imageIV;
        System.out.println(article);

        Container commandeModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        commandeModel.setUIID("containerRounded");

        nameLabel = new Label("nom : " + article.getNom());
        nameLabel.setUIID("labelDefault");
        priceLabel = new Label("prix : " + article.getPrix());
        priceLabel.setUIID("labelDefault");
        quantityLabel = new Label("quantite : " + article.getQuantity());
        quantityLabel.setUIID("labelDefault");

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        plusBtn = new Button("+");
        plusBtn.setUIID("buttonMain");
        plusBtn.addActionListener(action -> {
            PanierService.getInstance().manage(article, false);
            refresh();
        });

        minesBtn = new Button("-");
        minesBtn.setUIID("buttonMain");
        minesBtn.addActionListener(action -> {
            PanierService.getInstance().deleteOne(article.getId());
            refresh();
        });
        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonDanger");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce article ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = CommandeService.getInstance().delete(article.getId());
                if (responseCode == 200) {
                    currentArticle = null;
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

        if (article.getImage() != null) {
            String url = Statics.ARTICLE_IMAGE_URL + article.getImage();
            Image image = URLImage.createToStorage(
                    EncodedImage.createFromImage(theme.getImage("default.jpg").fill(1100, 500), false),
                    url,
                    url,
                    URLImage.RESIZE_SCALE
            );
            imageIV = new ImageViewer(image);
        } else {
            imageIV = new ImageViewer(theme.getImage("default.jpg").fill(1100, 500));
        }
        imageIV.setFocusable(false);

        btnsContainer.add(BorderLayout.WEST, minesBtn);
        btnsContainer.add(BorderLayout.CENTER, plusBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        commandeModel.addAll(
                imageIV,
                nameLabel,
                priceLabel,
                quantityLabel,
                btnsContainer
        );

        return commandeModel;
    }
}
