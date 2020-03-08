package com.example.yourcar.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.yourcar.R;
import com.example.yourcar.ui.model.Car;

/**
 * класс отвечает за открытие вьюшки в полном его размере
 */
public class OpenHomeFragment extends Fragment {


    // простые вьюхи из xml
    protected final String key = "OPEN_HOME_FRAGMENT";
    private TextView tvOil, tvTransmisson, tvRun, tvYear, tvSteeringWhile,
            tvDriveUnit, tvPower;
    private ImageView imageView;
    private TextView carName, cost;
    private Car car;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        car = getArguments().getParcelable(key);                                            // получаем данные по key
        return inflater.inflate(R.layout.open_home_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setView(view);
    }


    /**
     * @param view через него получаем доступ к вьюшкам а также
     *             устанавливаем его параметры по клику, все данные
     *             вытаскиваются через модельку которое передается с класса HomeModel
     */
    private void setView(View view) {

        // устанавливаем верхний текст по нажатию
        ((AppCompatActivity)getActivity()).getSupportActionBar(). setTitle(car.getName());

        tvOil.setText(car.getFuel());
        tvTransmisson.setText(car.getTransmission());
        tvRun.setText(car.getRun());
        tvYear.setText(car.getYear());
        tvSteeringWhile.setText(car.getSteeringWheel());
        tvDriveUnit.setText(car.getDriveUnit());
        tvPower.setText(car.getPower());

        // инициализация view
        carName = view.findViewById(R.id.carName);
        cost = view.findViewById(R.id.cost);
        // установка праметров к view
        cost.setText(car.getCost());
        carName.setText(car.getName());

        // загрузка рисунка с сети и установка его к ImageView

        Glide
                .with(imageView)
                .load(car.getUrl())
                .into(imageView);
    }

    /**
     * @param view через view мы просто инициализируем
     */
    private void initView(View view) {

        imageView = view.findViewById(R.id.favouriteImage);

        tvOil = view.findViewById(R.id.printOil);
        tvTransmisson = view.findViewById(R.id.printCarTransmission);
        tvRun = view.findViewById(R.id.printRun);
        tvYear = view.findViewById(R.id.printYear);
        tvSteeringWhile = view.findViewById(R.id.printsSteeringWhile);
        tvDriveUnit = view.findViewById(R.id.printdriveUnit);
        tvPower = view.findViewById(R.id.printPower);
    }

}
