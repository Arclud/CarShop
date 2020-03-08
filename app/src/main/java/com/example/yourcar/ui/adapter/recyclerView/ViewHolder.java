package com.example.yourcar.ui.adapter.recyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yourcar.R;
import com.example.yourcar.ui.model.Avensis;
import com.example.yourcar.ui.model.Car;


/**
 *  In this class we will  fill data for RecyclerView
 */
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private Avensis avensis;

    private ImageView imgMain;
    private TextView tvCarOil,
            tvCarTransmission, tvCarname,
            tvCost, carVolume, carDrive, year;

    private ListenerAdapter click;

    // this is the model for fill data
    private Car car;


    public ViewHolder(@NonNull View itemView, ListenerAdapter click) {
        super(itemView);
        this.click = click;
        initView(itemView);

        // its listener for user click
        itemView.setOnClickListener(this);
    }

    /**
     * @param itemView is parent view through which we found our View from layout
     */
    private void initView(View itemView) {

        imgMain = itemView.findViewById(R.id.imageView);

        tvCarOil = itemView.findViewById(R.id.carOil);
        tvCarTransmission = itemView.findViewById(R.id.carTransmission);
        tvCarname = itemView.findViewById(R.id.carName);
        tvCost = itemView.findViewById(R.id.cost);
        carVolume = itemView.findViewById(R.id.carVolume);
        carDrive = itemView.findViewById(R.id.carDrive);
        year = itemView.findViewById(R.id.yearFrom);

    }


    /**
     * @param item через модельку заполняем данные, модельку получаем через Адаптер
     */
    void bind(final Car item) {

        car = item;
        tvCarOil.setText(item.getFuel());
        tvCarTransmission.setText(item.getTransmission());
        tvCarname.setText(item.getName());
        tvCost.setText(item.getCost());
        carVolume.setText(item.getVolume());
        carDrive.setText(item.getDriveUnit());
        year.setText(item.getYear());

        Glide.with(imgMain).load(item.getUrl()).into(imgMain);

    }

    /**
     * never used
     */
    public void unUnbind(){
        Glide.with(imgMain).clear(imgMain);
    }

    @Override
    public void onClick(View v) {
        // отправляем нашу модельку в метод по клику
        click.onClick(car);
    }
}
