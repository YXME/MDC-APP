package com.example.mangadigitalcollection;

import com.example.mangadigitalcollection.dataStorage.Reference;
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
}
