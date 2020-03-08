package com.example.yourcar.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yourcar.R;
import com.example.yourcar.ui.activity.main.MainActivity;
import com.goodiebag.pinview.Pinview;
import com.google.firebase.auth.FirebaseAuth;


/**
 * this fragment is the last level of authorization
 */
public class LoginOtp extends Fragment implements View.OnClickListener{
    private FirebaseAuth mAuth;

    /// View from layout
    private Button button;
    private Pinview pinview;
    private View view;
    String password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
             password = getArguments().getString("password");

        }

        view = inflater.inflate(R.layout.verify_otp,container,false);
        initializeView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    private void initializeView(View view){

        pinview = view.findViewById(R.id.pinView);
        button = view.findViewById(R.id.buttonRegister);
        button.setOnClickListener(this);
    }


    // checking user phone password and our fireBase sms password, if there are true we are going to main activity who have main object
    @Override
    public void onClick(View v) {
        if(pinview.getValue().equals(password)){
            Toast.makeText(getContext(),
                    "Поздравляем",
                    Toast.LENGTH_SHORT)
                    .show();
            // replace
            Intent intent  = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);

        }

    }
}
