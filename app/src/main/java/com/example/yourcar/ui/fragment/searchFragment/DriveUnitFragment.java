package com.example.yourcar.ui.fragment.searchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yourcar.R;

public class DriveUnitFragment extends Fragment implements View.OnClickListener{


    private TextView driveUnitTop, driveUnitBottom, driveUnitFull,driveUnitAny;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentManager = getFragmentManager();
        return inflater.inflate(R.layout.fragment_drive_unit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setView();
    }

    private void setView() {
        driveUnitTop.setOnClickListener(this);
        driveUnitBottom.setOnClickListener(this);
        driveUnitFull.setOnClickListener(this);
        driveUnitAny.setOnClickListener(this);
    }

    private void initView(View view) {
        driveUnitTop = view.findViewById(R.id.driveUnitTop);
        driveUnitBottom = view.findViewById(R.id.driveUnitBottom);
        driveUnitFull = view.findViewById(R.id.driveUnitFull);
        driveUnitAny = view.findViewById(R.id.driveUnitAny);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.driveUnitTop:
                changeFragment(driveUnitTop.getText().toString());
                break;
            case R.id.driveUnitBottom:
                changeFragment(driveUnitBottom.getText().toString());
                break;
            case R.id.driveUnitFull:
                changeFragment(driveUnitFull.getText().toString());
                break;
            case R.id.driveUnitAny:
                changeFragment(null);
                break;
        }
    }

    private void changeFragment(String driveUnit){

        SearchModel.getInstance().setDriveUnit(driveUnit);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.searchContainer,new SearchFragment())
                .commit();
    }
}

