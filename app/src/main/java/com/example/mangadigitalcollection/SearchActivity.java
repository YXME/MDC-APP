package com.example.mangadigitalcollection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mangadigitalcollection.api.DataFromAPI;
import com.example.mangadigitalcollection.dataStorage.Reference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    EditText SearchQuery;
    Button SearchAction;
    TextView ErrorMessage;
    TableLayout ReferenceTab;

    ArrayList<Reference> ReferencesFromAPI = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        ErrorMessage = findViewById(R.id.SearchErrorMessage);
        SearchQuery = findViewById(R.id.searchData);
        SearchAction = findViewById(R.id.Search);
        ReferenceTab = findViewById(R.id.searchContainer);

        SearchAction.setOnClickListener(v -> {
            ErrorMessage.setText("");
            ReferencesFromAPI.clear();
            ReferenceTab.removeAllViews();
            DataFromAPI.getReferenceList().forEach(ref -> {
                if(ref.getName().toLowerCase().startsWith(SearchQuery.getText().toString().toLowerCase()))
                    ReferencesFromAPI.add(ref);
            });

            if(!ReferencesFromAPI.isEmpty()){
                for(int i = 0; i < ReferencesFromAPI.size(); i++) {
                    TableRow row = new TableRow(SearchActivity.this);
                    LinearLayout layout = new LinearLayout(SearchActivity.this);
                    ImageView illustration = new ImageView(SearchActivity.this);

                    TextView title = new TextView(SearchActivity.this);

                    row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                    layout.setOrientation(LinearLayout.HORIZONTAL);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    title.setLayoutParams(params);

                    Picasso.get().load(ReferencesFromAPI.get(i).getIllustrationLink()).resize(700, 1000).into(illustration);

                    illustration.setLayoutParams(params);
                    illustration.getLayoutParams().width = 350;
                    illustration.getLayoutParams().height = 500;

                    title.setLayoutParams(params);
                    title.setGravity(Gravity.CENTER);
                    title.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                    title.getLayoutParams().width = 430;
                    title.setMaxLines(3);
                    title.setEllipsize(TextUtils.TruncateAt.END);
                    title.setText(ReferencesFromAPI.get(i).getName());
                    title.setTextColor(Color.WHITE);

                    layout.addView(illustration);
                    layout.addView(title);
                    row.addView(layout);
                    ReferenceTab.addView(row);

                    int finalI = i;
                    row.setOnClickListener(v1 -> {
                        Intent intent = new Intent(getApplicationContext(), ReferenceActivity.class);
                        intent.putExtra("REFERENCE_ID", ReferencesFromAPI.get(finalI).getId());
                        intent.putExtra("FROM", 2);
                        startActivity(intent);
                        SearchActivity.this.finish();
                    });
                }
            }

            else {
                ErrorMessage.setText("Aucun résultat trouvé.");
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.setSelectedItemId(R.id.action_recherche);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_accueil:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    this.finish();
                    return true;
                case R.id.action_random:
                    startActivity(new Intent(getApplicationContext(), RandomActivity.class));
                    overridePendingTransition(0,0);
                    this.finish();
                    return true;
                case R.id.action_profil:
                    startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
                    overridePendingTransition(0,0);
                    this.finish();
                    return true;
            }
            return false;
        });
    }
}