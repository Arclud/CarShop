package com.example.yourcar.ui.adapter.recyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourcar.R;

import java.util.List;

public class AdapterCarModel extends RecyclerView.Adapter<ViewHolderCarModel> {

    private List<String> list;
    private ListenerCarModel listenerCarModel;

    public AdapterCarModel(ListenerCarModel listenerCarModel) {
        this.listenerCarModel = listenerCarModel;
    }

    @NonNull
    @Override
    public ViewHolderCarModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_of_carmodel,parent,false);
        return new ViewHolderCarModel(view, listenerCarModel);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCarModel holder, int position) {
        holder.bind(getPosition(position));
        Log.d("LINE",getPosition(position));

    }


    private String getPosition(int position) {
    return list.get(position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
