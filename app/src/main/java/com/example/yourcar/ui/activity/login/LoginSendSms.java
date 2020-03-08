package com.example.yourcar.ui.activity.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yourcar.R;
import com.example.yourcar.util.SharedPreferencesManager;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.concurrent.TimeUnit;

/**
 * how wrote the class answering for send sms for phone by click
 */
public class LoginSendSms extends Fragment implements View.OnClickListener{


    private Button button;
    private EditText editText;
    private CountryCodePicker codePicker;
    private View root;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.design_login_activity, container, false);
        initialView(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFireBaseCallbacks();
        inputEditor();

        button.setEnabled(false);
    }

    /**
     * initialization View
     */
    private void initialView(View view) {

        codePicker = view.findViewById(R.id.codeCountry);
        editText = view.findViewById(R.id.numberEditText);
        button = view.findViewById(R.id.buttonRegister);

        button.setOnClickListener(this);

    }
    private void inputEditor(){

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            // checking , user phone is empty ?
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    button.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * @param smsCode send smsCode for next level authorization when we get true sms code from user phone
     */
    private void onComplete(String smsCode){

        Bundle bundle  = new Bundle();
        bundle.putString("password",smsCode);
        LoginOtp loginOtp = new LoginOtp();
        loginOtp.setArguments(bundle);


        /// replace fragment to next level
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, loginOtp,"LOGIN_SEND_SMS")
                .addToBackStack("LOGIN_SEND_SMS")
                .commit();
    }


    /**
     * SMS listening and sending
     */
    private void initFireBaseCallbacks() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Toast.makeText(getActivity(), "УСПЕХ", Toast.LENGTH_SHORT).show();
                onComplete(credential.getSmsCode()); // open new fragment and send VERIFICATION CODE


                // we are save phone number and sms code in SharedPreferences because registration is Completed
                SharedPreferencesManager.getInstance(getActivity().getApplicationContext())
                        .setLogin(editText.getText().toString(),
                        credential.getSmsCode());

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getActivity(), "ОШИБКА ДАННЫХ", Toast.LENGTH_SHORT).show();
            }


            // send sms succesfull
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(getActivity(), "Отправлено", Toast.LENGTH_SHORT).show();
                button.setEnabled(false);
            }
        };
    }


    /**
     * we are listening and sending our number for get sms if my number is number of KR
     */
    @Override
    public void onClick(View v) {

        String number = codePicker.getFullNumberWithPlus();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(number+editText.getText().toString(),
                1,
                TimeUnit.MINUTES,
                getActivity(),
                mCallbacks);
    }
}
