package com.example.mangadigitalcollection.ui;

import android.content.Intent;
import android.media.CamcorderProfile;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mangadigitalcollection.ConnexionRest;
import com.example.mangadigitalcollection.DataFromAPI;
import com.example.mangadigitalcollection.LoginRegisterActivity;
import com.example.mangadigitalcollection.MainActivity;
import com.example.mangadigitalcollection.R;
import com.example.mangadigitalcollection.SplashScreen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginFragment extends Fragment {

    private View view;
    private EditText Email;
    private EditText Password;
    private TextView ErrorMessage;
    private Button Validation;
    private ConnexionRest connectionRest;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
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

            connectionRest = new ConnexionRest();
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

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        Email = (EditText) view.findViewById(R.id.LoginMail);
        Password = (EditText) view.findViewById(R.id.LoginPassword);
        ErrorMessage = view.findViewById(R.id.LoginErrorMessage);
        Validation = view.findViewById(R.id.LoginButton);

        Validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.getText() == null || Password.getText() == null) {
                    ErrorMessage.setText("Adresse email ou mot de passe incorrect.");
                }

                String response = AuthentificateToAPI(Email.getText().toString(), Password.getText().toString());
                if (response == null) {
                    ErrorMessage.setText("Adresse email ou mot de passe incorrect.");
                } else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    DataFromAPI.setConnexionRest(connectionRest);
                    DataFromAPI.setToken(response);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        return view;
    }
}