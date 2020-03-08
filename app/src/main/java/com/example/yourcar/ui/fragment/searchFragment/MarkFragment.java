package com.example.yourcar.ui.fragment.searchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yourcar.R;


public class MarkFragment extends Fragment implements View.OnClickListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TextView carLexus, carHonda, carBmw, carMersedez, carToyota;
    private SearchModel searchModel;

    /**
     *
     *
     *
     * Стандартный класс , создает view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_mark, container, false);
    }

    /**
     * @param view прослушка и инициализация view
     *
     */
    private void initView(View view) {

        carHonda = view.findViewById(R.id.carHonda);
        carBmw = view.findViewById(R.id.carBmw);
        carToyota = view.findViewById(R.id.carToyota);
        carLexus = view.findViewById(R.id.Lexus);
        carMersedez = view.findViewById(R.id.carMersedez);


        carHonda.setOnClickListener(this); // говорим view кого ему слушаться
        carBmw.setOnClickListener(this);
        carToyota.setOnClickListener(this);
        carLexus.setOnClickListener(this);
        carMersedez.setOnClickListener(this);
    }

    /**
     *
     * стандартный класс, вызывается после создания view подробнее можно прочитать в офиц документах
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        fragmentManager = getFragmentManager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.carHonda: // слушает нажата ли эта view
                sendSelectedItem("honda");
                break;
            case R.id.carBmw:   // ил этот view
                sendSelectedItem("bmw");
                break;
            case R.id.carToyota:
                sendSelectedItem("toyota");
                break;
            case R.id.Lexus:
                sendSelectedItem("lexus");
                break;
            case R.id.carMersedez:
                sendSelectedItem("mersedez_benz");
                break;
        }
    }

    /**
     * @param carMark метод вносит данные в модельку по клику user а
     *                потом меняет свой фрагмент передав старому фрагменту вносимые изменения
     */
    private void sendSelectedItem(String carMark) {

        SearchModel.getInstance().setMark(carMark); // вносит изменения в модельку

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.searchContainer, new SearchFragment()).commit();

    }
}
