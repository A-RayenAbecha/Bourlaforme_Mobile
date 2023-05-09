package com.mycompany.gui.front;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.ListReclamationFront;

public class AccueilFront extends Form {

    Resources theme = UIManager.initFirstTheme("/theme1");
    Label label;

    public AccueilFront() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
    }

    private void addGUIs() {

        label = new Label("Menu");
        label.setUIID("links");

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.CENTER, label);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makePanierButton(),
                makeArticleButton(),
                makeReclamationButton()
             
                
        );

        this.add(menuContainer);
    }

    private Button makePanierButton() {
        Button button = new Button("Panier");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.mycompany.gui.front.panier.ShowAll(this).show());
        return button;
    }

    private Button makeArticleButton() {
        Button button = new Button("article");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        
        button.addActionListener(action -> new com.mycompany.gui.front.article.ShowAll(this).show());
        return button;
    }
    private Button makeReclamationButton() {
        Button button = new Button("Reclamation");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.mycompany.gui.WelcomePage(this).show());
        return button;
    }



}
