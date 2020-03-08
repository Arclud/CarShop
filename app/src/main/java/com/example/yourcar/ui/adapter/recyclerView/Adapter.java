package com.example.yourcar.ui.adapter.recyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourcar.R;
import com.example.yourcar.ui.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * This adapter used how RecyclerView
 */
public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private ListenerAdapter click;

    /**
     * list for data
     */
    private List<Car> list = new ArrayList<>();

    /**
     * @param click is part of MainActivity , from MainActivity we send initial object ListenerAdapter
     */
    public Adapter(ListenerAdapter click) {
        this.click = click;
    }

    /**
     * @param parent you can read on the web
     * @param viewType you can read on the web
     * @return you can read on the web
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_main_activity, parent, false);
        // we send part of ListenerAdapter to ViewHolder
        return new ViewHolder(view,click);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    /**
     * @param position is
     * @return data from list by Adapter, like first data(image? text?), second data.....
     */
    private Car getItem(int position) {
        return list.get(position);
    }


    /**
     * @return all data from list who is send from MainActivity
     */
    @Override
    public int getItemCount() {
        return list.size();
    }


    // update our data

    public void updataData(List<Car> list){
        this.list = list;

        // перезаполнение
        notifyDataSetChanged();
    }
}
