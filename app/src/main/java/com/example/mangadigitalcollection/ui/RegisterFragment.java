package com.example.mangadigitalcollection.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.mangadigitalcollection.ConnexionRest;
import com.example.mangadigitalcollection.DataFromAPI;
import com.example.mangadigitalcollection.MainActivity;
import com.example.mangadigitalcollection.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class RegisterFragment extends Fragment {

    private EditText Username;
    private EditText Email;
    private EditText Password;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Email = view.findViewById(R.id.RegisterMail);
        Password = view.findViewById(R.id.RegisterPassword);
        Username = view.findViewById(R.id.RegisterUsername);
        Button validation = view.findViewById(R.id.RegisterButton);

        validation.setOnClickListener(v -> {
            try {
                ConnexionRest connectionRest = new ConnexionRest();
                JSONObject jsonAuthentification = new JSONObject();
                jsonAuthentification.put("name", Username.getText());
                jsonAuthentification.put("email", Email.getText());
                jsonAuthentification.put("password", Password.getText());
                jsonAuthentification.put("licence", "MNA-1A-5U-1");
                connectionRest.setObj(jsonAuthentification);
                connectionRest.execute("CREATE_USER");
                String token = connectionRest.get();

                if(token.charAt(0)=='{') {
                    Log.v("LoginActivity", token);
                }
                else {
                    ConnexionRest connexionRest = new ConnexionRest();
                    JSONObject jsonUserData = new JSONObject();
                    jsonUserData.put("email", Email.getText());
                    jsonUserData.put("username", Username.getText());
                    jsonUserData.put("profile_pic_url","http://54.38.187.95/images/user/default.jpg");
                    jsonUserData.put("biographie", "Je viens de créer mon compte !");
                    connexionRest.setAction("User");
                    connexionRest.setToken(token);
                    connexionRest.setObj(jsonUserData);
                    connexionRest.execute("POST");
                    connectionRest.get();

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    DataFromAPI.setToken(token);
                    DataFromAPI.setCurrentUserID(Email.getText().toString());
                    startActivity(intent);
                    Objects.requireNonNull(getActivity()).finish();
                }
            } catch (JSONException | InterruptedException | ExecutionException e) {
                Log.v("TAG", "[JSONException] e : " + e.getMessage());
            }
        });
        return view;
    }
}