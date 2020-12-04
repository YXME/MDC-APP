package com.example.mangadigitalcollection;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mangadigitalcollection.dataStorage.Ads;
import com.example.mangadigitalcollection.dataStorage.Commentaire;
import com.example.mangadigitalcollection.dataStorage.Editeur;
import com.example.mangadigitalcollection.dataStorage.Licence;
import com.example.mangadigitalcollection.dataStorage.Liste;
import com.example.mangadigitalcollection.dataStorage.Reference;
import com.example.mangadigitalcollection.dataStorage.RelationListRef;
import com.example.mangadigitalcollection.dataStorage.Studio;
import com.example.mangadigitalcollection.dataStorage.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ArrayCreation {

    public static ArrayList<Reference> MakeReferenceList (String jsonobject) throws JSONException {
        final ArrayList<Reference> ReferenceList = new ArrayList<>();
        final JSONArray jProductArray = new JSONArray(jsonobject);
        for (int i = 0; i < jProductArray.length(); i++) {
            ReferenceList.add(new Reference(jProductArray.optJSONObject(i)));
        }
        return ReferenceList;
    }

    public static ArrayList<User> MakeUserList(String jsonobject) throws JSONException {
        final ArrayList<User> UserList = new ArrayList<>();
        final JSONArray jProductArray = new JSONArray(jsonobject);
        for (int i = 0; i < jProductArray.length(); i++) {
            UserList.add(new User(jProductArray.optJSONObject(i)));
        }
        return UserList;
    }

    public static ArrayList<Licence> MakeLicenceList(String jsonobject) throws JSONException {
        final ArrayList<Licence> LicenceList = new ArrayList<>();
        final JSONArray jProductArray = new JSONArray(jsonobject);
        for (int i = 0; i < jProductArray.length(); i++) {
            LicenceList.add(new Licence(jProductArray.optJSONObject(i)));
        }
        return LicenceList;
    }

    public static ArrayList<Studio> MakeStudioList(String jsonobject) throws JSONException{
        final ArrayList<Studio> StudioList = new ArrayList<>();
        final JSONArray jProductArray = new JSONArray(jsonobject);
        for (int i = 0; i < jProductArray.length(); i++) {
            StudioList.add(new Studio(jProductArray.optJSONObject(i)));
        }
        return StudioList;
    }

    public static ArrayList<Editeur> MakeEditeurList(String jsonobject) throws JSONException{
        final ArrayList<Editeur> EditeurList = new ArrayList<>();
        final JSONArray jProductArray = new JSONArray(jsonobject);
        for (int i = 0; i < jProductArray.length(); i++) {
            EditeurList.add(new Editeur(jProductArray.optJSONObject(i)));
        }
        return EditeurList;
    }

    public static ArrayList<Commentaire> MakeCommentaireList(String jsonobject) throws JSONException{
        final ArrayList<Commentaire> CommentaireList = new ArrayList<>();
        final JSONArray jProductArray = new JSONArray(jsonobject);
        for (int i = 0; i < jProductArray.length(); i++) {
            CommentaireList.add(new Commentaire(jProductArray.optJSONObject(i)));
        }
        return CommentaireList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<Liste> MakeListesList(String listHeader, String listData) throws JSONException{
        final ArrayList<Liste> ListesList = new ArrayList<>();
        final JSONArray jlistHeaderArray = new JSONArray(listHeader);

        ArrayList<RelationListRef> RelationListRefListe = MakeRelationListRefListe(listData);

        for (int i = 0; i < jlistHeaderArray.length(); i++) {
            Liste ToAdd = new Liste(jlistHeaderArray.optJSONObject(i));

            ArrayList<Reference> ReferenceList = DataFromAPI.getReferenceList();
            ArrayList<Integer> ReferenceIds = new ArrayList<Integer>();
            RelationListRefListe.forEach((x) ->{
                if(x.getListeId() == ToAdd.getId()) {
                    ReferenceIds.add(x.getReferenceId());
                }
            });

            final ArrayList<Reference> FinalReferenceList = new ArrayList<>();
            ReferenceList.forEach((a) -> {
                if (!ReferenceIds.contains(a.getId())){
                    FinalReferenceList.add(a);
                }
            });
            ToAdd.setListContent(FinalReferenceList);
            ListesList.add(ToAdd);
        }
        return ListesList;
    }

    public static ArrayList<RelationListRef> MakeRelationListRefListe(String listData) throws JSONException{
        final ArrayList<RelationListRef> RelationListRefListe = new ArrayList<>();
        final JSONArray jlistDataArray = new JSONArray(listData);
        for (int i = 0; i < jlistDataArray.length(); i++) {
            RelationListRefListe.add(new RelationListRef(jlistDataArray.optJSONObject(i)));
        }
        return RelationListRefListe;
    }

    public static ArrayList<Ads> MakeAdsList(String listJsonObjs) throws JSONException {
        final ArrayList<Ads> AdsListe = new ArrayList<>();
        final JSONArray jlistDataArray = new JSONArray(listJsonObjs);
        for (int i = 0; i < jlistDataArray.length(); i++) {
            AdsListe.add(new Ads(jlistDataArray.optJSONObject(i)));
        }
        return AdsListe;
    }
}
