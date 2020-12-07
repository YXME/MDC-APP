package com.example.mangadigitalcollection;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mangadigitalcollection.dataStorage.Ads;
import com.example.mangadigitalcollection.dataStorage.Commentaire;
import com.example.mangadigitalcollection.dataStorage.Editeur;
import com.example.mangadigitalcollection.dataStorage.Licence;
import com.example.mangadigitalcollection.dataStorage.Liste;
import com.example.mangadigitalcollection.dataStorage.Reference;
import com.example.mangadigitalcollection.dataStorage.Studio;
import com.example.mangadigitalcollection.dataStorage.User;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DataFromAPI {

    private static String token;
    private static int CurrentUserID;

    private static ArrayList<User> UserList = new ArrayList<User>();
    private static ArrayList<Reference> ReferenceList = new ArrayList<Reference>();
    private static ArrayList<Licence> LicenceList = new ArrayList<Licence>();
    private static ArrayList<Studio> StudioList = new ArrayList<Studio>();
    private static ArrayList<Editeur> EditeurList = new ArrayList<Editeur>();
    private static ArrayList<Commentaire> CommentairesList = new ArrayList<Commentaire>();
    private static ArrayList<Liste> ListesList = new ArrayList<Liste>();
    private static ArrayList<Ads> AdsList = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void FetchDataFromAPI()
    {
        //TODO : Remplacer par la valeur récupéré sur l'API à l'authentification.
        CurrentUserID = 1;
        //////////////////
        FetchReferenceFromAPI();
        FetchUserFromAPI();
        FetchLicenceFromAPI();
        FetchStudioFromAPI();
        FetchEditeurFromAPI();
        FetchAdsFromAPI();
        FetchCommentairesFromAPI();
        FetchListesFromAPI();
    }

    public static void FetchReferenceFromAPI(){
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
        }
        catch (InterruptedException | ExecutionException | JSONException e)
        {
            e.printStackTrace();
        }
    }

    public static void FetchUserFromAPI(){
        try {
            String listJsonObjs;
            ConnexionRest connexionRest = new ConnexionRest();
            connexionRest.setToken(token);
            connexionRest.setAction("User");
            connexionRest.execute("GET");
            listJsonObjs = connexionRest.get();

            if (listJsonObjs != null) {
                UserList = ArrayCreation.MakeUserList(listJsonObjs);
            }
        }
        catch (InterruptedException | ExecutionException | JSONException e)
        {
            e.printStackTrace();
        }
    }

    public static void FetchLicenceFromAPI(){
        try {
            String listJsonObjs;
            ConnexionRest connexionRest = new ConnexionRest();
            connexionRest.setToken(token);
            connexionRest.setAction("Licence");
            connexionRest.execute("GET");
            listJsonObjs = connexionRest.get();

            if (listJsonObjs != null) {
                LicenceList = ArrayCreation.MakeLicenceList(listJsonObjs);
            }
        }
        catch (InterruptedException | ExecutionException | JSONException e)
        {
            e.printStackTrace();
        }
    }

    public static void FetchStudioFromAPI(){
        try {
            String listJsonObjs;
            ConnexionRest connexionRest = new ConnexionRest();
            connexionRest.setToken(token);
            connexionRest.setAction("Studio");
            connexionRest.execute("GET");
            listJsonObjs = connexionRest.get();

            if (listJsonObjs != null) {
                StudioList = ArrayCreation.MakeStudioList(listJsonObjs);
            }
        }
        catch (InterruptedException | ExecutionException | JSONException e)
        {
            e.printStackTrace();
        }
    }

    public static void FetchEditeurFromAPI(){
        try {
            String listJsonObjs;
            ConnexionRest connexionRest = new ConnexionRest();
            connexionRest.setToken(token);
            connexionRest.setAction("Editeur");
            connexionRest.execute("GET");
            listJsonObjs = connexionRest.get();

            if (listJsonObjs != null) {
                EditeurList = ArrayCreation.MakeEditeurList(listJsonObjs);
            }
        }
        catch (InterruptedException | ExecutionException | JSONException e)
        {
            e.printStackTrace();
        }
    }

    public static void FetchAdsFromAPI(){
        try {
            String listJsonObjs;
            ConnexionRest connexionRest = new ConnexionRest();
            connexionRest.setToken(token);
            connexionRest.setAction("Ads");
            connexionRest.execute("GET");
            listJsonObjs = connexionRest.get();

            if (listJsonObjs != null) {
                AdsList = ArrayCreation.MakeAdsList(listJsonObjs);
            }
        }
        catch (InterruptedException | ExecutionException | JSONException e)
        {
            e.printStackTrace();
        }
    }

    public static void FetchCommentairesFromAPI(){
        try {
            String listJsonObjs;
            ConnexionRest connexionRest = new ConnexionRest();
            connexionRest.setToken(token);
            connexionRest.setAction("Commentaire");
            connexionRest.execute("GET");
            listJsonObjs = connexionRest.get();

            if (listJsonObjs != null) {
                CommentairesList = ArrayCreation.MakeCommentaireList(listJsonObjs);
            }

            if(CommentairesList.size() != 0)
            {
                for (int i = 0; i < ReferenceList.size(); i++) {
                    for (int j = 0; j < CommentairesList.size(); j++) {
                        if (CommentairesList.get(j).getReferenceId() == ReferenceList.get(i).getId()) {
                            ReferenceList.get(i).addCommentaire(CommentairesList.get(j));
                        }
                    }
                }
            }
        }
        catch (InterruptedException | ExecutionException | JSONException e)
        {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void FetchListesFromAPI(){
        try {
            String listJsonObjs;
            ConnexionRest connexionRest = new ConnexionRest();
            connexionRest.setToken(token);
            connexionRest.setAction("Listes");
            connexionRest.execute("GET");
            String ListHeader = connexionRest.get();
            String ListData =  FetchRelationListeRefFromAPI();

            if (ListHeader != null) {
                ListesList = ArrayCreation.MakeListesList(ListHeader,ListData);
            }
        }
        catch (InterruptedException | ExecutionException | JSONException e)
        {
            e.printStackTrace();
        }
    }

    public static String FetchRelationListeRefFromAPI() {
        try {
            ConnexionRest connexionRest = new ConnexionRest();
            connexionRest.setToken(token);
            connexionRest.setAction("RelationListReference");
            connexionRest.execute("GET");
            String ListData = connexionRest.get();

            if (ListData != null) {
                return ListData;
            }
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static int getCurrentUserID() {
        return CurrentUserID;
    }

    public static void setCurrentUserID(int currentUserID) {
        CurrentUserID = currentUserID;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        DataFromAPI.token = token;
    }

    public static ArrayList<User> getUserList() {
        return UserList;
    }

    public static ArrayList<Reference> getReferenceList() {
        return ReferenceList;
    }

    public static ArrayList<Licence> getLicenceList() {
        return LicenceList;
    }

    public static ArrayList<Studio> getStudioList() {
        return StudioList;
    }

    public static ArrayList<Editeur> getEditeurList() {
        return EditeurList;
    }

    public static ArrayList<Commentaire> getCommentairesList() {
        return CommentairesList;
    }

    public static ArrayList<Liste> getListesList() {
        return ListesList;
    }

    public static ArrayList<Ads> getAdsList() {
        return AdsList;
    }

    public static void reset() {
        token = null;
        CurrentUserID = -1;
        UserList.clear();
        ReferenceList.clear();
        LicenceList.clear();
        StudioList.clear();
        EditeurList.clear();
        CommentairesList.clear();
        ListesList.clear();
        AdsList.clear();
    }
}
