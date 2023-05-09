package com.anarchy.gui.back;

import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;


 public class AccueilBack extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;

    public AccueilBack() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
    }

    private void addGUIs() {
        ImageViewer userImage = new ImageViewer(theme.getImage("default.jpg").fill(200, 200));
        userImage.setUIID("candidatImage");
        label = new Label("Admin"/*MainApp.getSession().getEmail()*/);
        label.setUIID("links");
        Button btnDeconnexion = new Button();
        btnDeconnexion.setUIID("buttonLogout");
        btnDeconnexion.setMaterialIcon(FontImage.MATERIAL_ARROW_LEFT);
        btnDeconnexion.addActionListener(actionConf -> {
            new com.anarchy.gui.front.AccueilFront().show();
        });
        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.WEST, userImage);
        userContainer.add(BorderLayout.CENTER, label);
        userContainer.add(BorderLayout.EAST, btnDeconnexion);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makeCommandeButton()
        );

        this.add(menuContainer);
    }

    private Button makeCommandeButton() {
        Button button = new Button("Commande");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.anarchy.gui.back.commande.ShowAll(this).show());
        return button;
    }

//
//    private Button makeCourButton() {
////        Button button = new Button("Cour");
////        button.setUIID("buttonMenu");
////        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
////        button.addActionListener(action -> new com.anarchy.gui.back.cour.ShowAll(this).show());
////        return button;
//    }

}
