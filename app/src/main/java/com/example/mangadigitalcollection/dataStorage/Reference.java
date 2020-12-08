package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

import java.util.ArrayList;

public class Reference {
    private final int Id;
    private final String Name;
    private final String Original_Name;
    private final String IllustrationLink;
    private final String Genre;
    private final boolean IsManga;
    private int NbTomes;
    private int EditeurID;
    private final boolean IsAnime;
    private int NbSaisons;
    private int NbEpisodesTotal;
    private int StudioID;
    private final int LicenceID;
    private final boolean isSponsorised;

    private final ArrayList<Commentaire> Commentaires = new ArrayList<>();

    public Reference(JSONObject optJSONObject) {
        this.Id = optJSONObject.optInt("id");
        this.Name = optJSONObject.optString("name");
        this.Original_Name = optJSONObject.optString("original_name");
        this.IllustrationLink = optJSONObject.optString("illustrationlink");
        this.Genre = optJSONObject.optString("genre");

        this.IsManga = optJSONObject.optInt("isManga") == 1;

        if(this.IsManga){
            this.NbTomes = optJSONObject.optInt("nbTomes");
            this.EditeurID = optJSONObject.optInt("edition");
        }

        this.IsAnime = optJSONObject.optInt("IsAnime") == 1;

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

    public void addCommentaire(Commentaire commentaire){
        this.Commentaires.add(commentaire);
    }

}