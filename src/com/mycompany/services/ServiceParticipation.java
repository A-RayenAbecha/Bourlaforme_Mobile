/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

/**
 *
 * @author obba
 */
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;

import com.mycomany.entities.Participation;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
/**
 *
 * @author Administrateur
 */
public class ServiceParticipation {
    
    public List<Participation> Participation;

    public static ServiceParticipation instance = null;
    public boolean resultOk;
    private Participation participation = new Participation();
    private ConnectionRequest req;
    
    
    
    public static ServiceParticipation getInstance()
    {
    if (instance==null)
        instance = new ServiceParticipation();
    return instance;
    
    }
    
    
    public ServiceParticipation()
    {
        req= new ConnectionRequest();
        
    
    }
    //crud ajout
    
    public void ajoutParticipation(Participation participation){
    
    String url=Statics.BASE_URL+"/participations/json/new?id_club="+participation.getId_club()+"&dateDebut="+participation.getDateDebut()+"&dateFin="+participation.getDateFin();//+article.getImage();
    req.setUrl(url);
    req.addResponseListener((e)-> {
    String str = new String(req.getResponseData());
    System.out.println("data =="+str);
    
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    
    
    
    
    }
    
    //crud affich
    
   
    
    //
    public List<Participation> parseParticipations(String jsonText) throws IOException{
          
        List<Participation> Participation = new ArrayList<>();
        //jsonText = jsonText.substring(0, jsonText.length()-1);
 //jsonText = jsonText.substring(1);

  //String jsonText2= jsonText.replace('=', ':');
   //
   

 
   
        JSONParser j = new JSONParser(); 
        
         Map<String,Object>       ParticipationListJson= j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
       
           
        List<Map<String,Object>> list = (List<Map<String,Object>>)ParticipationListJson.get("root");

              

        for(Map<String,Object> obj : list){
        
            // System.out.println("Lahneeeee ==");
            Participation a =  new Participation();
           // 
            int id = (int)Float.parseFloat(obj.get("id").toString());
            a.setId(id);
           
           
            String dateDebut = obj.get("dateDebut").toString();
            a.setDateDebut(dateDebut);
             String dateFin = obj.get("dateFin").toString();
            a.setDateFin(dateFin);
                    System.out.println("TESTTTTTTTTTTT==");
          
            Participation.add(a);
           
        }
          
        return Participation;
    }
     public boolean modifierParticipation(Participation participation) {
     
        String url = Statics.BASE_URL +"/participations/json/update?id="+participation.getId()+"&id_club="+participation.getId_club()+"&dateDebut="+participation.getDateDebut()+"&dateFin="+participation.getDateFin();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
     
     public boolean deleteParticipation(int id ) {
        String url = Statics.BASE_URL +"/participations/json/delete?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
   public List<Participation> getAllParticipations(){
   
        String url = Statics.BASE_URL+"/participation/json";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try{
                Participation = parseParticipations(new String(req.getResponseData()));
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  Participation;
    }
   public ArrayList<Participation>affichageParticipations() {
        ArrayList<Participation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/participation/json";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    String jsonText =  req.getResponseData().toString();
                      
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                   //   System.out.println("TESTTTTTTTTTTT== "+listOfMaps.get(1));
                    for(Map<String, Object> obj : listOfMaps) {
                   
                        Participation re =new Participation();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                       
                        float idClub = Float.parseFloat(obj.get("id_cllub").toString());
                       
                        
                        
                         String dateDebut = obj.get("date de debut").toString();
                         
                        //String description ="";// obj.get("description").toString();
                        
                        String dateFin = obj.get("date fin").toString();
                        
                      
                        
                        re.setId((int)id);
                        re.setId_club((int)idClub);     
                        re.setDateDebut(dateDebut);
                       // re.setDescription(description);
                        re.setDateFin(dateFin);
                       
                       
                        //Date 
                        /*
                        String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);*/
                    
                     
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }

    
 
}
