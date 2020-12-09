package com.example.mangadigitalcollection;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mangadigitalcollection.api.ConnexionRest;
import com.example.mangadigitalcollection.api.DataFromAPI;
import com.example.mangadigitalcollection.dataStorage.Liste;
import com.example.mangadigitalcollection.dataStorage.Reference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    TextView TitreListe;
    TableLayout ReferenceTab;

    Button AddToList;

    Liste SelectedListe;

    ArrayList<Reference> ListReference = new ArrayList<>();

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        DataFromAPI.FetchDataFromAPI();
        DataFromAPI.getListesList().forEach(a -> {
            if(a.getId() == getIntent().getIntExtra("SELECTED_LISTE", 1)){
                SelectedListe = a;
            }
        });

        ReferenceTab = findViewById(R.id.ListeContainer);
        TitreListe = findViewById(R.id.ListeTitre);

        TitreListe.setText(SelectedListe.getName());

        ListReference = SelectedListe.getListContent();

        AddToList = findViewById(R.id.addToList);

        if(!ListReference.isEmpty())
            for(int i = 0; i < ListReference.size(); i++) {
            TableRow row = new TableRow(ListeActivity.this);
            LinearLayout layout = new LinearLayout(ListeActivity.this);
            ImageView illustration = new ImageView(ListeActivity.this);

            TextView title = new TextView(ListeActivity.this);

            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            layout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            title.setLayoutParams(params);

            Picasso.get().load(ListReference.get(i).getIllustrationLink()).resize(700, 1000).into(illustration);

            illustration.setLayoutParams(params);
            illustration.getLayoutParams().width = 350;
            illustration.getLayoutParams().height = 500;

            title.setLayoutParams(params);
            title.setGravity(Gravity.CENTER);
            title.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            title.getLayoutParams().width = 500;
            title.setMaxLines(3);
            title.setEllipsize(TextUtils.TruncateAt.END);
            title.setText(ListReference.get(i).getName());
            title.setTextColor(Color.WHITE);

            layout.addView(illustration);
            layout.addView(title);
            row.addView(layout);
            ReferenceTab.addView(row);

            int finalI = i;
            row.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), ReferenceActivity.class);
                intent.putExtra("REFERENCE_ID", ListReference.get(finalI).getId());
                intent.putExtra("FROM", 4);
                startActivity(intent);
                ListeActivity.this.finish();
            });
        }

        if(DataFromAPI.getCurrentUserID().equals(DataFromAPI.getUserList().get(SelectedListe.getUserId() - 1).getEmail())){
            AddToList.setVisibility(View.VISIBLE);
            AddToList.setOnClickListener(v -> {
                Spinner AllReferences = new Spinner(ListeActivity.this);

                ArrayList<String> ReferencesNames = new ArrayList<>();

                DataFromAPI.getReferenceList().forEach(a -> ReferencesNames.add(a.getName()));


                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ListeActivity.this, android.R.layout.simple_spinner_item, ReferencesNames);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                AllReferences.setAdapter(arrayAdapter);

                AlertDialog.Builder builder = new AlertDialog.Builder(ListeActivity.this);
                builder.setTitle("Que souhaitez-vous ajouter ?");
                builder.setView(AllReferences);
                builder.setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    Reference SelectedReference = DataFromAPI.getReferenceList().get(AllReferences.getSelectedItemPosition());

                    ConnexionRest connexionRest = new ConnexionRest();
                    JSONObject DataToAdd = new JSONObject();
                    try {
                        DataToAdd.put("listeId", SelectedListe.getId());
                        DataToAdd.put("referenceId", SelectedReference.getId());
                        connexionRest.setObj(DataToAdd);
                        connexionRest.setToken(DataFromAPI.getToken());
                        connexionRest.setAction("RelationListReference");
                        connexionRest.execute("POST");
                        DataFromAPI.FetchDataFromAPI();

                        Intent intent = new Intent(getApplicationContext(), ListeActivity.class);
                        intent.putExtra("SELECTED_LISTE", SelectedListe.getId());
                        intent.putExtra("FROM", 4);
                        startActivity(intent);
                        this.finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
                builder.setNegativeButton("Annuler", null);
                builder.setIcon(android.R.drawable.btn_plus);
                builder.show();
            });
        }

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.setSelectedItemId(R.id.action_profil);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_accueil:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.action_recherche:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.action_random:
                    startActivity(new Intent(getApplicationContext(), RandomActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.action_profil:
                    startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
    }
}