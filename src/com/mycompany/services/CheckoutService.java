/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;


import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.BillingAddress;
import com.mycomany.utils.Statics;



/**
 *
 * @author rayen
 */
public class CheckoutService {

    public static CheckoutService instance = null;
    public int resultCode;
    private ConnectionRequest cr;

    private CheckoutService() {
        cr = new ConnectionRequest();
    }

    public static CheckoutService getInstance() {
        if (instance == null) {
            instance = new CheckoutService();
        }
        return instance;
    }

    public int chekout() {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/ApIPlaceOrder/");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }

    public int chekout(BillingAddress ba) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/APIBillingAddress");
        cr.setHttpMethod("POST");

        cr.addArgument("nom", ba.getNom());
        cr.addArgument("email", ba.getEmail());
        cr.addArgument("address", ba.getAddress());
        cr.addArgument("description", ba.getDescription());
        cr.addArgument("phone", ba.getPhone() + "");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }

}
