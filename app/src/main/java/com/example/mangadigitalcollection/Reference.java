package com.example.mangadigitalcollection;

public class Reference {
    private int Id;
    private String Name;
    private String Original_Name;
    private String IllustrationLink;
    private boolean IsManga;
    private int NbTomes;
    private int EditeurID;
    private boolean IsAnime;
    private int NbEpisodes;
    private int StudioID;
    private int LicenceID;

    public Reference(int id, String name, String original_Name, String illustrationLink, boolean isManga, int nbTomes, int editeurID, boolean isAnime, int nbEpisodes, int studioID, int licenceID) {
        this.Id = id;
        this.Name = name;
        this.Original_Name = original_Name;
        this.IllustrationLink = illustrationLink;
        this.IsManga = isManga;
        this.NbTomes = nbTomes;
        this.EditeurID = editeurID;
        this.IsAnime = isAnime;
        this.NbEpisodes = nbEpisodes;
        this.StudioID = studioID;
        this.LicenceID = licenceID;
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

    public int getNbEpisodes() {
        return NbEpisodes;
    }

    public int getStudioID() {
        return StudioID;
    }

    public int getLicenceID() {
        return LicenceID;
    }
}