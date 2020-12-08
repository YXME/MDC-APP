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

import com.example.mangadigitalcollection.dataStorage.Liste;
import com.example.mangadigitalcollection.dataStorage.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfilActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    //Button ThemeButton;
    //TableRow ThemeButtonContainer;

    TableRow UsernameContainer, BioContainer;
    Button DisconnectButton, CreateList;
    TextView Pseudonyme, Biographie;
    User ThisUser;

    TableLayout UserListesContainer;

    ImageView ProfilePicture;

    ArrayList<Liste> UserListes = new ArrayList<>();

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        int UserIdProfile = getIntent().getIntExtra("USER_ID", -1);
        if (UserIdProfile == -1)
            UserIdProfile = (DataFromAPI.getUserList().stream().filter(a -> (a.getEmail().equals(DataFromAPI.getCurrentUserID()))).findFirst().orElse(null)).getId();

        ThisUser = DataFromAPI.getUserList().get(UserIdProfile - 1);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        ProfilePicture = findViewById(R.id.profilePicture);
        Picasso.get().load(ThisUser.getPictureUrl()).resize(500, 500).into(ProfilePicture);

        TableRow.LayoutParams textParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );

        UsernameContainer = findViewById(R.id.usernameContainer);
        BioContainer = findViewById(R.id.bioContainer);

        UsernameContainer.setLayoutParams(textParams);
        BioContainer.setLayoutParams(textParams);

        Pseudonyme = findViewById(R.id.pseudonyme);

        Pseudonyme.setLayoutParams(textParams);
        Pseudonyme.setText(ThisUser.getUsername());

        Biographie = findViewById(R.id.biographie);
        Biographie.setLayoutParams(textParams);
        Biographie.setText(ThisUser.getBiographie());
        Biographie.setMaxLines(3);
        Biographie.setEllipsize(TextUtils.TruncateAt.END);

        Biographie.getLayoutParams().width = 680;
        Biographie.getLayoutParams().height = 500;

        UserListesContainer = findViewById(R.id.userListesContainer);

        DataFromAPI.getListesList().forEach(lis -> {
            if(lis.getUserId() == ThisUser.getId()){
                UserListes.add(lis);
            }
        });

        if(DataFromAPI.getCurrentUserID().equals(ThisUser.getEmail())){

            if(!UserListes.isEmpty()){
                for(int i = 0; i < UserListes.size(); i++) {
                    TableRow row = new TableRow(ProfilActivity.this);
                    LinearLayout layout = new LinearLayout(ProfilActivity.this);
                    TextView title = new TextView(ProfilActivity.this);
                    Button DeleteButton = new Button(ProfilActivity.this);
                    row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                    layout.setOrientation(LinearLayout.HORIZONTAL);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    title.setLayoutParams(params);


                    title.setLayoutParams(params);
                    title.setGravity(Gravity.CENTER);
                    title.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                    title.getLayoutParams().width = 540;
                    title.setMaxLines(3);
                    title.setEllipsize(TextUtils.TruncateAt.END);
                    title.setText(UserListes.get(i).getName());
                    title.setTextColor(Color.WHITE);

                    DeleteButton.setLayoutParams(params);
                    DeleteButton.setText("Supprimer");
                    DeleteButton.setTextColor(Color.WHITE);

                    int finalI = i;
                    DeleteButton.setOnClickListener(v -> {
                        try {
                            ConnexionRest connexionRest = new ConnexionRest();
                            JSONObject DataToDelete = new JSONObject();
                            DataToDelete.put("id", UserListes.get(finalI).getId());
                            connexionRest.setObj(DataToDelete);
                            connexionRest.setToken(DataFromAPI.getToken());
                            connexionRest.setAction("Listes");
                            connexionRest.execute("DELETE");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        DataFromAPI.getListesList().remove(UserListes.get(finalI));
                        UserListes.remove(UserListes.get(finalI));

                        ProfilActivity.this.recreate();
                    });

                    row.setOnClickListener(v -> {
                        Intent intent = new Intent(getApplicationContext(), ListeActivity.class);
                        intent.putExtra("SELECTED_LISTE", UserListes.get(finalI).getId());
                        intent.putExtra("FROM", 4);
                        startActivity(intent);
                    });

                    layout.addView(title);
                    layout.addView(DeleteButton);
                    row.addView(layout);
                    UserListesContainer.addView(row);
                }
            }

            CreateList = findViewById(R.id.createList);
            CreateList.setVisibility(View.VISIBLE);

            DisconnectButton = findViewById(R.id.disconnectButton);
            DisconnectButton.setVisibility(View.VISIBLE);

            DisconnectButton.setOnClickListener(v -> {
                DataFromAPI.reset();
                Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                startActivity(intent);
                finishAffinity();
            });

            int finalUserIdProfile = UserIdProfile;
            CreateList.setOnClickListener(v -> {
                final EditText input = new EditText(ProfilActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);

                new AlertDialog.Builder(ProfilActivity.this)
                        .setTitle("Nommez votre nouvelle liste : ")
                        .setMessage("Maybe it will be JoJo... or JoJo... or JoJo... or Jojo...")
                        .setView(input)
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            if(input.getText() == null){
                                return;
                            }
                            ConnexionRest connexionRest = new ConnexionRest();
                            JSONObject DataToAdd = new JSONObject();
                            try {
                                DataToAdd.put("name", input.getText());
                                DataToAdd.put("userId", finalUserIdProfile);
                                connexionRest.setObj(DataToAdd);
                                connexionRest.setToken(DataFromAPI.getToken());
                                connexionRest.setAction("Listes");
                                connexionRest.execute("POST");
                                DataFromAPI.FetchDataFromAPI();

                                Intent intent = new Intent(getApplicationContext(), ListeActivity.class);
                                intent.putExtra("SELECTED_LISTE", DataFromAPI.getListesList().size());
                                intent.putExtra("FROM", 4);
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .setIcon(android.R.drawable.btn_plus)
                        .show();
            });
        }

        else {
            if (!UserListes.isEmpty())
                for (int i = 0; i < UserListes.size(); i++) {
                    TableRow row = new TableRow(ProfilActivity.this);
                    LinearLayout layout = new LinearLayout(ProfilActivity.this);
                    TextView title = new TextView(ProfilActivity.this);

                    row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                    layout.setOrientation(LinearLayout.HORIZONTAL);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    title.setLayoutParams(params);

                    title.setLayoutParams(params);
                    title.setGravity(Gravity.CENTER);
                    title.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                    title.getLayoutParams().width = 430;
                    title.setMaxLines(3);
                    title.setEllipsize(TextUtils.TruncateAt.END);
                    title.setText(UserListes.get(i).getName());
                    title.setTextColor(Color.WHITE);

                    layout.addView(title);
                    row.addView(layout);
                    UserListesContainer.addView(row);

                    int finalI = i;
                    row.setOnClickListener(v -> {
                        Intent intent = new Intent(getApplicationContext(), ListeActivity.class);
                        intent.putExtra("SELECTED_LISTE", UserListes.get(finalI).getId());
                        intent.putExtra("FROM", 4);
                        startActivity(intent);
                    });
                }
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
            }
            return false;
        });
    }
}