package com.example.yourcar.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourcar.R;
import com.example.yourcar.ui.adapter.recyclerView.Adapter;
import com.example.yourcar.ui.adapter.recyclerView.IListenerHelper;
import com.example.yourcar.ui.adapter.recyclerView.ListenerAdapter;
import com.example.yourcar.ui.fragment.searchFragment.SearchModel;
import com.example.yourcar.ui.model.Car;
import com.example.yourcar.ui.model.ModelCar;
import com.example.yourcar.util.JsonResourceManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * главный класс, фрагмент
 */
public class HomeFragment extends Fragment implements ListenerAdapter {

    private IListenerHelper listenerHelper;

    private ModelCar modelCar;
    private final String CARS_JSON = "cars.json";
    private RecyclerView recyclerView;
    private Adapter adapter;
    private final static String LOGIN_TAG = "HOME_FRAGMENT";

    // объекты фрагментов
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    public HomeFragment(IListenerHelper listenerHelper) {
        this.listenerHelper = listenerHelper;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Легковые");
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getInstance();
        initRecyclerView(view);
    }

    /**
     * если modelCar пустой то инициализируем его
     */
    private void getInstance() {
        if (modelCar == null) {
            modelCar = readFromAssets();
        }
    }


    /**
     * установка recycLerView
     */
    private void initRecyclerView(View view) {
        adapter = new Adapter(this);                                        // инициализация нешего view
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext() // устанавливаем разметку для нашего recyclerView
                , LinearLayoutManager.VERTICAL,
                false);
        recyclerView = view.findViewById(R.id.homeRecyclerView); // находим view
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);                          // передаем ему адаптер наш
        adapter.updataData(modelCar.toyota().avensis());           // обновляем данные

    }

    /**
     * @return метод читает и возвращает модельку типа ModelCar
     */
    private ModelCar readFromAssets() {
        String json = JsonResourceManager.readFileFromAssets(getContext(), CARS_JSON);
        return new Gson().fromJson(json, ModelCar.class);
    }

    /**
     * слушатель, имплементировали Интерфейс и его метод
     */
    @Override
    public void onClick(Car item) {

        OpenHomeFragment openHomeFragment = new OpenHomeFragment();    // инициализация нашего фрагмента на которое будет переданы данные с модельки
        Bundle bundle = new Bundle();
        bundle.putParcelable(openHomeFragment.key, item); // привязываем нащ объект к bundle
        openHomeFragment.setArguments(bundle);            // передаем наш привязванный объект


        //  переход, замена существующего фрагмента на новый
        fragmentManager = getFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, openHomeFragment)
                .addToBackStack(LOGIN_TAG)
                .commit();
    }


    /**
     * функция которая проверяет данные пользователя и сортирует список создавая новые список из новых условий
     */
    public void updateListBySearchFragment() {

        List<Car> list = new ArrayList<>();

        for (int i = 0; i < modelCar.toyota().avensis().size(); ++i) {

            String temp = modelCar.toyota().getToyota().get(i).getShortName();

            if (modelCar.toyota().getToyota().get(i).getShortName() == null ||
                    temp.equals(SearchModel.getInstance().getMark())) {

                if (SearchModel.getInstance().getModel() == null ||
                        SearchModel.getInstance().getModel().equals(modelCar.toyota().getToyota().get(i).getModel())) {

                    if (SearchModel.getInstance().getSteeringWhile() == null ||
                            SearchModel.getInstance().getSteeringWhile().equals(modelCar.toyota().getToyota().get(i).getSteeringWheel())) {
                        if (Integer.valueOf(SearchModel.getInstance().getYearFrom()) <= Integer.valueOf(modelCar.toyota().getToyota().get(i).getYear())
                                && Integer.valueOf(SearchModel.getInstance().getYearTo()) >= Integer.valueOf(modelCar.toyota().getToyota().get(i).getYear())) {
                            if (SearchModel.getInstance().getTransmission() == null
                            || SearchModel.getInstance().getTransmission().equals(modelCar.toyota().getToyota().get(i).getTransmission()))
                                if (SearchModel.getInstance().getDriveUnit() == null
                                || SearchModel.getInstance().getDriveUnit().equals(modelCar.toyota().getToyota().get(i).getDriveUnit())){
                                    list.add(modelCar.toyota().avensis().get(i)); // ПЕРЕДАЕТ ОТСОРТИРОВАННЫЕ ДАННЫЕ
                                }
                        }
                    }
                }
            }
        }
        adapter.updataData(list);
    }



    /*    private void setListLize(int id) {

            List<Verses> list = new ArrayList<>();

            for (int i = 0; i < homeModel.verses.size(); i++) {
                if (homeModel.verses.get(i).getBookNumber() == id) {
                    list.add(homeModel.verses.get(i));
                }
            }
            versesAdapter.update(list);
        }*/


}
