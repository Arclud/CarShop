package com.example.yourcar.ui.adapter.recyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourcar.R;
import com.example.yourcar.ui.fragment.searchFragment.SearchModel;

public class ViewHolderCarModel extends RecyclerView.ViewHolder {
    private ImageView imageVie;
    private TextView textView;
    private ListenerCarModel listenerCarModel;
    private String carModel;


    ViewHolderCarModel(@NonNull View itemView, final ListenerCarModel listenerCarModel) {
        super(itemView);
        this.listenerCarModel = listenerCarModel;

        textView = itemView.findViewById(R.id.carModelTextView);
        imageVie = itemView.findViewById(R.id.imageCarModel);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerCarModel.onclick(carModel);

            }
        });
    }

    void bind(final String carModel) {
        this.carModel = carModel;
        textView.setText(carModel);
        imageVie.setImageResource(getImageById());


    }


    @DrawableRes
    private int getImageById() {
        switch (SearchModel.getInstance().getMark()){
            case "toyota":
                return R.drawable.toyota;
            case "honda":
                return R.drawable.honda;
            case "bmw":
                return R.drawable.bmw;
            case "lexus":
                return R.drawable.lexus;
            case "mersedez_benz":
                return R.drawable.mersedez;
        }
        return 0;
    }

}
