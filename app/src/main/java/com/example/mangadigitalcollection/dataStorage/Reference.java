package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

import java.util.ArrayList;

public class Reference {
    private int Id;
    private String Name;
    private String Original_Name;
    private String IllustrationLink;
    private String Genre;
    private boolean IsManga;
    private int NbTomes;
    private int EditeurID;
    private boolean IsAnime;
    private int NbSaisons;
    private int NbEpisodesTotal;
    private int StudioID;
    private int LicenceID;
    private boolean isSponsorised;

    private ArrayList<Commentaire> Commentaires;

    public Reference(JSONObject optJSONObject) {
        this.Id = optJSONObject.optInt("id");
        this.Name = optJSONObject.optString("name");
        this.Original_Name = optJSONObject.optString("original_name");
        this.IllustrationLink = optJSONObject.optString("illustrationlink");
        this.IsManga = optJSONObject.optBoolean("isManga");
        this.Genre = optJSONObject.optString("genre");

        if(this.IsManga){
            this.NbTomes = optJSONObject.optInt("nbTomes");
            this.EditeurID = optJSONObject.optInt("edition");;
        }

        this.IsAnime = optJSONObject.optBoolean("isAnime");
        if(this.IsAnime){
            this.NbSaisons = optJSONObject.optInt("nbSaisons");
            this.NbEpisodesTotal = optJSONObject.optInt("nbEpisodesTotal");
            this.StudioID = optJSONObject.optInt("studio");
        }

        this.LicenceID = optJSONObject.optInt("licence_id");
        this.isSponsorised = optJSONObject.optBoolean("isSponsorised");
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getOriginal_Name() {
        return Original_Name;
    }

    public String getIllustrationLink() {
        return IllustrationLink;
    }

    public boolean isManga() {
        return IsManga;
    }

    public int getNbTomes() {
        return NbTomes;
    }

    public int getEditeurID() {
        return EditeurID;
    }

    public boolean isAnime() {
        return IsAnime;
    }

    public int getNbSaisons() {
        return NbSaisons;
    }

    public int getNbEpisodesTotal() {
        return NbEpisodesTotal;
    }

    public int getStudioID() {
        return StudioID;
    }

    public int getLicenceID() {
        return LicenceID;
    }

    public String getGenre() {
        return Genre;
    }

    public boolean isSponsorised() {
        return isSponsorised;
    }

    public ArrayList<Commentaire> getCommentaires() {
        return Commentaires;
    }

    public void setCommentaires(ArrayList<Commentaire> commentaires) {
        Commentaires = commentaires;
    }

    public void addCommentaire(Commentaire commentaire){
        this.Commentaires.add(commentaire);
    }

    public void remCommentaire(Commentaire commentaire){
        this.Commentaires.remove(commentaire);
    }
}