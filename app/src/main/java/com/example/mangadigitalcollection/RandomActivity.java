package com.example.mangadigitalcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mangadigitalcollection.dataStorage.Reference;

import java.util.ArrayList;

public class RandomActivity extends AppCompatActivity {

    ArrayList<Reference> ToRandomize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        ToRandomize = DataFromAPI.getReferenceList();


    }
}