package com.example.mangadigitalcollection;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    String token = null;
    List<Reference> New = new ArrayList<Reference>();
    List<Reference> Recommended = new ArrayList<Reference>();
    TextView Text;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        Text = findViewById(R.id.testText);

        try {
            ConnexionRest connexionRest = new ConnexionRest();
            token = getIntent().getStringExtra("TOKEN");
            connexionRest.setToken(token);
            connexionRest.setAction("Reference");
            connexionRest.execute("GET");
            String listJsonObjs = connexionRest.get();

            if (listJsonObjs != null) {
                New = ArrayCreation.MakeReferenceList(listJsonObjs).subList(0, 1);
                New.forEach(Ref ->
                        Text.setText(Ref.toString())
                        );
            }
        }

        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}