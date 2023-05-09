package com.anarchy.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

public class WelcomePage extends Form {

    public static Form loginForm;

    public WelcomePage() {
        super("Welcome", new BoxLayout(BoxLayout.Y_AXIS));
        loginForm = this;
        addGUIs();
    }

    private void addGUIs() {


        Button frontendBtn = new Button("Utilisateur");
        frontendBtn.addActionListener(l -> new ListReclamationFront(this).show());
        this.add(frontendBtn);


        Button backendBtn = new Button("Administrateur");
        backendBtn.addActionListener(l -> new ListReclamationAdmin(this).show());

        this.add(backendBtn);
    }

}
