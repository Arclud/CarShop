package com.example.yourcar.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.yourcar.R;
import com.example.yourcar.ui.activity.main.MainActivity;
import com.google.firebase.auth.PhoneAuthProvider;

/**
 *  The fragment for first Login Authorization , where we have logo , register button and close image
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private final static String LOGIN_TAG = "LOGIN_FRAGMENT";

    // object from FireBase
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FragmentManager fragmentManager;
    // View from layout
    private Button button;
    private ImageView imageView;
    // Fragment object
    private FragmentTransaction fragmentTransaction;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        fragmentManager = getFragmentManager();
    }


    /**
     * @param view initialization View
     */
    private void initView(View view) {
        imageView = view.findViewById(R.id.imgCloseReg);
        button = view.findViewById(R.id.buttonRegister);

        imageView.setOnClickListener(this);
        button.setOnClickListener(this);
    }


    /**
     * This function is listening user click and do something
     * @param v have id in layout
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgCloseReg:

                // close the activity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;


                // lets go next level of authorization
            case R.id.buttonRegister:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, new LoginSendSms(), LOGIN_TAG).addToBackStack(LOGIN_TAG)
                        .commit();
                break;
                default:

        }
    }

}


