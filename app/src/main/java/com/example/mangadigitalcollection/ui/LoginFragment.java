package com.example.mangadigitalcollection.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mangadigitalcollection.MainActivity;
import com.example.mangadigitalcollection.R;
import com.example.mangadigitalcollection.api.ConnexionRest;
import com.example.mangadigitalcollection.api.DataFromAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class LoginFragment extends Fragment {

    private EditText Email;
    private EditText Password;
    private TextView ErrorMessage;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public String AuthentificateToAPI(String Email, String Password) {
        try {
            JSONObject jsonAuth = new JSONObject();
            jsonAuth.put("email", Email);
            jsonAuth.put("password", Password);
            jsonAuth.put("app", "MNA");

            ConnexionRest connectionRest = new ConnexionRest();
            connectionRest.setObj(jsonAuth);
            connectionRest.setAction("auth");
            connectionRest.execute("POST");

            // Log.v("Login", userEmail + " : " + userPassword);

            String response = connectionRest.get();

            // Error Message
            if (response.charAt(0) == '{') {
                Log.v("Loggin Activity", response);
                return null;
            } else {
                Log.v("Loggin Success", response);
                return response;
            }

        } catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Email = view.findViewById(R.id.LoginMail);
        Password = view.findViewById(R.id.LoginPassword);
        ErrorMessage = view.findViewById(R.id.LoginErrorMessage);
        Button validation = view.findViewById(R.id.LoginButton);


        validation.setOnClickListener(v -> {
            if (Email.getText() == null || Password.getText() == null) {
                ErrorMessage.setText("Adresse email ou mot de passe incorrect.");
            }

            String response = AuthentificateToAPI(Email.getText().toString(), Password.getText().toString());
            if (response == null) {
                ErrorMessage.setText("Adresse email ou mot de passe incorrect.");
            } else {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                DataFromAPI.setToken(response);
                DataFromAPI.setCurrentUserID(Email.getText().toString());
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
            }
        });
        return view;
    }
}