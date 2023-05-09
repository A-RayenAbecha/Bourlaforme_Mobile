/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

/**
 *
 * @author Administrateur
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Article;
import com.mycomany.entities.Commentaire;
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
public class ServiceCommentaire {
    
    public List<Commentaire> Commentaire;

    public static ServiceCommentaire instance = null;
    public boolean resultOk;
    private Commentaire commentaire = new Commentaire();
    private ConnectionRequest req;
    
    
    
    public static ServiceCommentaire getInstance()
    {
    if (instance==null)
        instance = new ServiceCommentaire();
    return instance;
    
    }
    
    
    public ServiceCommentaire()
    {
        req= new ConnectionRequest();
        
    
    }
    //crud ajout
    
    public void ajoutCommentaire(Commentaire commentaire){
    
    String url=Statics.BASE_URL+"/commentaires/json/new?article="+commentaire.getArticle()+"&contenu="+commentaire.getContenu()+"&auteur="+commentaire.getAuteur();//+article.getImage();
    req.setUrl(url);
    req.addResponseListener((e)-> {
    String str = new String(req.getResponseData());
    System.out.println("data =="+str);
    
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    
    
    
    
    }
    
    //crud affich
    
   
    
    //
    public List<Commentaire> parseCommentaires(String jsonText) throws IOException{
          
        List<Commentaire> Commentaire = new ArrayList<>();
        //jsonText = jsonText.substring(0, jsonText.length()-1);
 //jsonText = jsonText.substring(1);

  //String jsonText2= jsonText.replace('=', ':');
   //
   

 
   
        JSONParser j = new JSONParser(); 
        
         Map<String,Object>       CommentaireListJson= j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
       
           
        List<Map<String,Object>> list = (List<Map<String,Object>>)CommentaireListJson.get("root");
 Map<String,Object> ar;
              

        for(Map<String,Object> obj : list){
        
            // System.out.println("Lahneeeee ==");
            Commentaire a =  new Commentaire();
           // 
            int id = (int)Float.parseFloat(obj.get("id").toString());
            a.setId(id);
              ar = (Map<String,Object>) obj.get("article");
                      
                double article=5;
        //     article = Float.parseFloat(ar.get("id").toString());
        if (ar!=null)
article=    (double) ar.get("id");
        else 
        article=0;
            a.setArticle((int)article);
            a.setId(id);
            String auteur = obj.get("auteur").toString();
            a.setAuteur(auteur);
            String contenu = obj.get("contenu").toString();
            a.setContenu(contenu);
           
            String date = obj.get("date").toString();
            a.setDate2(date);
          
          
            Commentaire.add(a);
           
        }
          
        return Commentaire;
    }
    
    public Commentaire parseCommentaire(String jsonText) throws IOException{
        JSONParser j;
         j= new JSONParser(); 
        jsonText = jsonText.substring(0, jsonText.length()-1);
  jsonText = jsonText.substring(1);
         //   System.out.println("Lahneeeee affichage wdkahba=="+  jsonText);

 
        
        Map<String,Object> commentaireJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
        Commentaire a = new Commentaire(); 
        float id = 5;
            System.out.println("Lahneeeee affichage =="+ commentaireJson.get("nom"));
              System.out.println("Lahneeeee xagivac ==");
            a.setId((int)id);
                System.out.println("Lahneeeee 22 ==");
            String description ="ccc"; //articleJson.get("description").toString();
        
          //  a.setAuteur(Auteur);
          //  String nom ="ggg";// articleJson.get("nom").toString();
        //    System.out.println("Lahneeeee 33 ==");
        //    a.setNom(nom);
        //    String etat ="desarchiver" ;//articleJson.get("etat").toString();
         //   a.setEtat(etat);
         //   String image = "";//articleJson.get("image").toString();
         //   a.setImage(image);
           /* int nbrreact = (int)Float.parseFloat(articleJson.get("nbrreact").toString());
            a.setNbrreact(nbrreact);
            Map<String,Object> user = (Map<String,Object>)articleJson.get("auteur");
            a.setAuteur((int)Float.parseFloat(user.get("id").toString()));*/
            return a;
    }

    public List<Commentaire> getAllCommentaire(){
   
        String url = Statics.BASE_URL+"/commentaires/json";
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
                Commentaire = parseCommentaires(new String(req.getResponseData()));
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  Commentaire;
        
        /*
        String url = Statics.BASE_URL+"/commentaires/json";
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
                Commentaire = parseCommentaires(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
catch(IOException ex){
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
     
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Commentaire;*/
    }
    
    
    //
    
 public boolean deleteCommentaire(int id ) {
        String url = Statics.BASE_URL +"/commentaires/json/delete?id="+id;
        
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
    
     public boolean modifierCommentaire(Commentaire Commentaire) {
     
        String url = Statics.BASE_URL +"/commentaires/json/update?id=?+article="+commentaire.getArticle()+"&contenu="+commentaire.getContenu()+"&auteur="+commentaire.getContenu();
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
    
    
    
    
    
    
    
 
}

