package com.example.yourcar.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yourcar.R;
import com.example.yourcar.ui.activity.main.MainActivity;
import com.example.yourcar.ui.fragment.searchFragment.SearchFragment;
import com.example.yourcar.ui.fragment.searchFragment.SearchModel;

public class SearchActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        if (savedInstanceState == null){
            initView();
            getFragment();
        }
    }

    /// replaced fragment
    private void getFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.searchContainer,new SearchFragment())
                .commit();
    }

    // initalization View from layout
    private void initView() {
        toolbar = findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);
    }

    /**
     *  отдает ответ в предыдущему активити что он должен список обновить по новым условиям
     */
    public void sendResult() {
        //проверяю выбрана ли год выпуска машины или нет
        if (SearchModel.getInstance().getYearTo() == null || SearchModel.getInstance().getYearFrom() == null){
            Toast.makeText(this,"Вы не выбрали 'ГОД ВЫПУСКА' транспорта",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("result", true);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }
}
