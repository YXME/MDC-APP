package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

public class Reference {
    private int Id;
    private String Name;
    private String Original_Name;
    private String IllustrationLink;
    private boolean IsManga;
    private int NbTomes;
    private int EditeurID;
    private boolean IsAnime;
    private int NbSaisons;
    private int NbEpisodesTotal;
    private int StudioID;
    private int LicenceID;

    public Reference(int id, String name, String original_Name, String illustrationLink, boolean isManga, int nbTomes, int editeurID, boolean isAnime, int nbSaisons, int nbEpisodesTotal, int studioID, int licenceID) {
        this.Id = id;
        this.Name = name;
        this.Original_Name = original_Name;
        this.IllustrationLink = illustrationLink;
        this.IsManga = isManga;
        this.NbTomes = nbTomes;
        this.EditeurID = editeurID;
        this.IsAnime = isAnime;
        this.NbSaisons = nbSaisons;
        this.NbEpisodesTotal = nbEpisodesTotal;
        this.StudioID = studioID;
        this.LicenceID = licenceID;
    }

    public Reference(JSONObject optJSONObject) {
        this.Id = optJSONObject.optInt("id");
        this.Name = optJSONObject.optString("name");
        this.Original_Name = optJSONObject.optString("original_name");
        this.IllustrationLink = optJSONObject.optString("illustrationlink");
        this.IsManga = optJSONObject.optBoolean("isManga");

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

        this.LicenceID = optJSONObject.optInt("licence_id");;
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
}