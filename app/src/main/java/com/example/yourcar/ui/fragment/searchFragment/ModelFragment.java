package com.example.yourcar.ui.fragment.searchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourcar.R;
import com.example.yourcar.ui.adapter.recyclerView.AdapterCarModel;
import com.example.yourcar.ui.adapter.recyclerView.ListenerCarModel;

import java.util.ArrayList;
import java.util.List;

public class ModelFragment extends Fragment implements ListenerCarModel {

    private RecyclerView recyclerView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_model, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            initView(view);
            fragmentManager = getFragmentManager();

    }

    /**
     * @param view инициализация view
     */
    private void initView(View view) {
        AdapterCarModel adapterCarModel = new AdapterCarModel(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);

        recyclerView= view.findViewById(R.id.carRecyclerView);  // found recyclerView
        recyclerView.setLayoutManager(linearLayoutManager);     // установка параметра
        recyclerView.setAdapter(adapterCarModel);               // установка адаптера
        adapterCarModel.update(getList(SearchModel.getInstance().getMark())); // обновляем данные
    }

    /**
     * @param line простая мини моделька
     * @return возвращает лист данных по передвавемому ему данных
     * например toyota значит функция передаст все что есть по имени toyota
     */
    private List<String> getList(String line){
        List<String> list  = new ArrayList<>();
        switch (line){
            case "bmw":
                list.add("x5");
                list.add("x6");
                list.add("m1");
                list.add("z3");
                return list;
            case "mersedez_benz":
                list.add("a_class");
                list.add("amg_gt");
                list.add("e_class");
                list.add("g_class");
                list.add("s_class");
                return list;
            case "lexus":
                list.add("rx");
                list.add("gx");
                list.add("es");
                list.add("ct");
                return list;
            case "honda":
                list.add("fit");
                list.add("accord");
                list.add("crv");
                list.add("jazz");
                list.add("odyssei");
                return list;
            case "toyota":
                list.add("avensis");
                list.add("camry");
                list.add("wish");
                list.add("land_cruiser_prado");
                return list;

        }
        return list;
    }


    /**
     * @param position прослушка
     */


    @Override
    public void onclick(String position) {
          SearchModel.getInstance().setModel(position);  // передаем данные в модельку
          fragmentTransaction = fragmentManager.beginTransaction(); // замена фрагмента
          fragmentTransaction.replace(R.id.searchContainer,new SearchFragment())
                  .commit();
    }
}
