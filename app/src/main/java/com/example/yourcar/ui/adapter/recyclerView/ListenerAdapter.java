package com.example.yourcar.ui.adapter.recyclerView;

import com.example.yourcar.ui.model.Car;

public interface ListenerAdapter {
    // метод для извлечения данных из адаптера, viewHolder
    void onClick(Car position);
}
