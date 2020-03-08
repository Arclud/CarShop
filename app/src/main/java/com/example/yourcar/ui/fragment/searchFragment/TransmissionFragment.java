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

public class TransmissionFragment extends Fragment implements View.OnClickListener {

    private TextView transmissionMechanic, transmissionAuto, transmissionCVT, transmissionRobot, transmissionAny; // простые vieq
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentManager = getFragmentManager();
        return inflater.inflate(R.layout.fragment_transmisson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setView();
    }

    /**
     * @param v есть слушатель, то есть слушает
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // id в xml, слушаем нажата ли этот view или нет
            case R.id.transmissionMechanic:
                changeFragment(transmissionMechanic.getText().toString());
                break;
            case R.id.transmissionAuto:
                changeFragment(transmissionAuto.getText().toString());
                break;
            case R.id.transmissionCVT:
                changeFragment(transmissionCVT.getText().toString());
                break;
            case R.id.transmissionRobot:
                changeFragment(transmissionRobot.getText().toString());
                break;
            case R.id.transmissionAny:
                changeFragment(null);
                break;
        }
    }

    /**
     * @param view инициализация view
     */
    private void initView(View view) {
        transmissionMechanic = view.findViewById(R.id.transmissionMechanic);
        transmissionAuto = view.findViewById(R.id.transmissionAuto);
        transmissionCVT = view.findViewById(R.id.transmissionCVT);
        transmissionRobot = view.findViewById(R.id.transmissionRobot);
        transmissionAny = view.findViewById(R.id.transmissionAny);
    }

    /**
     * говорим что слушателем является имплементированный View.OnClickListener на который мы даем ссылку через this
     */
    private void setView() {
        transmissionMechanic.setOnClickListener(this);
        transmissionAuto.setOnClickListener(this);
        transmissionCVT.setOnClickListener(this);
        transmissionRobot.setOnClickListener(this);
        transmissionAny.setOnClickListener(this);
    }


    /**
     * @param transmisson передается в transmisson его имя, то есть текст приписанный в xml
     *                    после происходит замена фрагмента
     *                    transmission - коробка передач, user выбирает автомат, маханика,электрон и эти данные передаются в модельку
     */
    private void changeFragment(String transmisson) {

        SearchModel.getInstance().setTransmission(transmisson); // устанавливаем нашу transmission в модельку чтобы мы могли работать с ним в других модельках

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.searchContainer, new SearchFragment()) // замена фрагмента
                .commit();
    }
}
