package com.example.mangadigitalcollection;

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
}
