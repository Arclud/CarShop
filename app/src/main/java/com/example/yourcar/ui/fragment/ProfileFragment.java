package com.example.yourcar.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.yourcar.R;
import com.example.yourcar.ui.activity.login.LoginActivity;
import com.example.yourcar.util.SharedPreferencesManager;

/**
 * пустой класс
 */
public class ProfileFragment extends Fragment {


    // инициализация view
    private Button button;
    private final String title = "Профиль";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar(). setTitle(title);

        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView(view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                /// если пользователь не реган то по нажатию кнокпи переходим к стартовой активити
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView(View view) {
        button = view.findViewById(R.id.buttonRegister);
        if (!(SharedPreferencesManager.getInstance(getContext()).checkLogin())) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.GONE);

        }

    }

}