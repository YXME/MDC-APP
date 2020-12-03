package com.example.mangadigitalcollection.ui;

import android.content.Intent;
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
import com.example.mangadigitalcollection.MainActivity;
import com.example.mangadigitalcollection.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RegisterFragment extends Fragment {

    private View view;
    private EditText Username;
    private EditText Email;
    private EditText Password;
    private TextView ErrorMessage;
    private Button Validation;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        Email = (EditText) view.findViewById(R.id.RegisterMail);
        Password = (EditText) view.findViewById(R.id.RegisterPassword);
        Username = (EditText) view.findViewById(R.id.RegisterUsername);
        ErrorMessage = view.findViewById(R.id.RegisterErrorMessage);
        Validation = view.findViewById(R.id.RegisterButton);

        Validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        connectionRest.setObj(jsonAuthentification);

                        connectionRest.execute("POST");
                        connectionRest.get();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        DataFromAPI.setToken(token);
                        startActivity(intent);
                        getActivity().finish();
                    }
                } catch (JSONException e1) {
                    Log.v("TAG", "[JSONException] e : " + e1.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    public void SetUerInDatabase(){

    }
}