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

/**
 * принцип работы схожий с классом TransmissionFragment, разница только в передаваемой в модельку текста
 */
public class SteeringWheelFragment extends Fragment implements View.OnClickListener{


    private TextView steeringWheelRight, getSteeringWheelleft, any;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentManager = getFragmentManager();
        return inflater.inflate(R.layout.fragment_steering_wheel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setView();
    }

    private void setView() {
        steeringWheelRight.setOnClickListener(this);
        getSteeringWheelleft.setOnClickListener(this);
        any.setOnClickListener(this);
    }

    private void initView(View view) {
        steeringWheelRight = view.findViewById(R.id.steeringWhileRight);
        getSteeringWheelleft = view.findViewById(R.id.steeringWhileLeft);
        any = view.findViewById(R.id.steeringWhileAny);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.steeringWhileAny:
                changeFragment(null);
                break;
            case R.id.steeringWhileLeft:
                changeFragment(getSteeringWheelleft.getText().toString());
                break;
            case R.id.steeringWhileRight:
                changeFragment(steeringWheelRight.getText().toString());
                break;
        }
    }

    private void changeFragment(String steeringWheel){

        SearchModel.getInstance().setSteeringWhile(steeringWheel);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.searchContainer,new SearchFragment())
                .commit();
    }
}
