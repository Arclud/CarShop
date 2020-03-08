package com.example.yourcar.ui.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yourcar.R;
import com.example.yourcar.ui.activity.SearchActivity;
import com.example.yourcar.ui.adapter.recyclerView.IListenerHelper;
import com.example.yourcar.ui.fragment.AddFragment;
import com.example.yourcar.ui.fragment.FavouriteFragment;
import com.example.yourcar.ui.fragment.HomeFragment;
import com.example.yourcar.ui.fragment.MotoFragment;
import com.example.yourcar.ui.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

/**
 * This class is calling how firs activity
 * The View.OnclickListener is working for catch click user
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener
                                                                , IListenerHelper {

    private final String TAG = "MAIN_ACTIVITY";
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private TextView textView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private boolean updatelist = false;
    private HomeFragment homeFragment;

    /**
     * @param savedInstanceState created for first action load
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            setAnimation();
            initToolBarBottom();
            initHomeFragment();

        }
    }


    /**
     * animation when we came from SplachActivity
     */
    public void setAnimation() {
        if (Build.VERSION.SDK_INT > 20) {
            getWindow().setEnterTransition(new AutoTransition());
        }
    }

    /**
     * replace empty container
     */
    private void initHomeFragment() {
        if (homeFragment == null){
            homeFragment = new HomeFragment(this);
        }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, homeFragment)
                .commit();

    }

    /**
     * in this case we will be initialed our View
     */
    private void initToolBarBottom() {
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        textView = findViewById(R.id.searchTextView);
        textView.setOnClickListener(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            /**
             * @param menuItem get item from menu how located in folder menu like R.id.home_menu:
             * @return fragment which will be replaced by click
             */
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    // we pass to to home page
                    case R.id.home_menu:
                        initHomeFragment();
                        return true;
                    // we pass to to user profile
                    case R.id.home_profile:
                        fragmentManager.beginTransaction()
                                .addToBackStack("home_profile")
                                .replace(R.id.main_container, new ProfileFragment())
                                .commit();
                        return true;

                    // for example we replace this form to MOTO because moto was clicked
                    case R.id.home_moto:
                        fragmentManager.beginTransaction()
                                .addToBackStack("home_moto")
                                .replace(R.id.main_container, new MotoFragment())
                                .commit();
                        return true;
                    case R.id.home_add:
                        fragmentManager.beginTransaction()
                                .addToBackStack("home_add")
                                .replace(R.id.main_container, new AddFragment())
                                .commit();
                        return true;
                    case R.id.home_favourite:
                        fragmentManager.beginTransaction()
                                .addToBackStack("home_favourite")
                                .replace(R.id.main_container, new FavouriteFragment())
                                .commit();
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * @param v is @return view ID from layout
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchTextView:
                setupSearchFragment();
                break;
        }
    }


    /**
     * this method transition another activity - > exactly by click up button
     */
    private void setupSearchFragment() {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean temp = false;
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                updateListByNewData(data.getBooleanExtra("result", temp));
            }
        }
    }

    /**
     * @param b вызываем метод из HomeFragment чтобы обновить наши данные по выбору пользователя
     */
    private void updateListByNewData(boolean b) {
        homeFragment.updateListBySearchFragment();

    }


    /**
     * I wrote this method if you want communicate HomeFragment with Activity
     *
     * Note: You do not need to comminicate activity with fragment because we have object of HomeFragment
     */
    @Override
    public void updateList() {
        // this function isn't do something
    }
}
