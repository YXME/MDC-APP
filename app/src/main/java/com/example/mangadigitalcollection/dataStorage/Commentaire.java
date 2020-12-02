package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

public class Commentaire {

    private int Id;
    private int UserId;
    private int ReferenceId;
    private int Note;
    private String Avis;

    public Commentaire(JSONObject optJSONObject) {
        this.Id = optJSONObject.optInt("id");
        this.UserId = optJSONObject.optInt("UserId");
        this.ReferenceId = optJSONObject.optInt("ReferenceId");
        this.Note = optJSONObject.optInt("ReferenceId");
        this.Avis = optJSONObject.optString("name");
    }

    public int getId() {
        return Id;
    }

    public int getUserId() {
        return UserId;
    }

    public int getReferenceId() {
        return ReferenceId;
    }

    public int getNote() {
        return Note;
    }

    public String getAvis() {
        return Avis;
    }
}
