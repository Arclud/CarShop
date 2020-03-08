package com.example.yourcar.ui.activity.login;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.yourcar.R;

/**
 * This activity call Fragment LoginFragment which have registration View and another data
 */
public class LoginActivity  extends AppCompatActivity  implements Interface {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // get Fragment manager to click , you can read in official site
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        }
    }

    /**
     * @param position is not using anything
     */
    @Override
    public void succesfull(String position) {

    }
}

