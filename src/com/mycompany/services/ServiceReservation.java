/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Reservation;
import com.mycomany.utils.Statics;
import java.util.List;

public class ServiceReservation {
    
    public List<Reservation> Reservation;

    public static ServiceReservation instance = null;
    public boolean resultOk;
    private Reservation reservation= new Reservation();
    private ConnectionRequest req;
    
    
    
    public static ServiceReservation getInstance()
    {
    if (instance==null)
        instance = new ServiceReservation();
    return instance;
    
    }
    
    
    public ServiceReservation()
    {
        req= new ConnectionRequest();
        
    
    }
    //crud ajout
    
    public void ajoutReservation(Reservation reservation){
    
    String url=Statics.BASE_URL+"/reservations/json/new?seance="+reservation.getSeance();
    req.setUrl(url);
    req.addResponseListener((e)-> {
    String str = new String(req.getResponseData());
    System.out.println("data =="+str);
    
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    
    
    
    
    }
public boolean deleteReservation(int id ) {
        String url = Statics.BASE_URL +"/reservations/json/delete?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }}