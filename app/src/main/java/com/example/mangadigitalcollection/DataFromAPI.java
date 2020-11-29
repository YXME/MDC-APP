package com.example.mangadigitalcollection;

import com.example.mangadigitalcollection.dataStorage.Editeur;
import com.example.mangadigitalcollection.dataStorage.Licence;
import com.example.mangadigitalcollection.dataStorage.Reference;
import com.example.mangadigitalcollection.dataStorage.Studio;
import com.example.mangadigitalcollection.dataStorage.User;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DataFromAPI {

    private static ConnexionRest connexionRest;
    private static String token;

    private static ArrayList<User> UserList = new ArrayList<User>();
    private static  ArrayList<Reference> ReferenceList = new ArrayList<Reference>();
    //private static ArrayList<Licence> LicenceList = new ArrayList<Licence>();
    //private static ArrayList<Studio> StudioList = new ArrayList<Studio>();
    //private static ArrayList<Editeur> EditeurList = new ArrayList<Editeur>();

    public static void FetchDataFromAPI()
    {
        try {
            String listJsonObjs;
            ConnexionRest connexionRest = new ConnexionRest();
            connexionRest.setToken(token);
            connexionRest.setAction("Reference");
            connexionRest.execute("GET");
            listJsonObjs = connexionRest.get();

            if (listJsonObjs != null) {
                ReferenceList = ArrayCreation.MakeReferenceList(listJsonObjs);
            }

            connexionRest.setAction("User");
            listJsonObjs = connexionRest.get();

            if (listJsonObjs != null) {
                UserList = ArrayCreation.MakeUserList(listJsonObjs);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setConnexionRest(ConnexionRest connexionRest) {
        DataFromAPI.connexionRest = connexionRest;
    }

    public static void setToken(String token) {
        DataFromAPI.token = token;
    }

    public ArrayList<User> getUserList() {
        return UserList;
    }

    public static ArrayList<Reference> getReferenceList() {
        return ReferenceList;
    }

  /*  public static ArrayList<Licence> getLicenceList() {
        return LicenceList;
    }

    public static ArrayList<Studio> getStudioList() {
        return StudioList;
    }

    public static ArrayList<Editeur> getEditeurList() {
        return EditeurList;
    }*/

}
