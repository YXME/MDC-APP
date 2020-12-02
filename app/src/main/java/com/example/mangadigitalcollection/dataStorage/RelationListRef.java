package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

public class RelationListRef {
    private int Id;
    private int ListeId;
    private int ReferenceId;

    public RelationListRef(JSONObject optJSONObject) {
        this.Id = optJSONObject.optInt("id");
        this.ListeId = optJSONObject.optInt("listeId");
        this.ReferenceId = optJSONObject.optInt("referenceId");
    }

    public int getId() {
        return Id;
    }

    public int getListeId() {
        return ListeId;
    }

    public int getReferenceId() {
        return ReferenceId;
    }
}
