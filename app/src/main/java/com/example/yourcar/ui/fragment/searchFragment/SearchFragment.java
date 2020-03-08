package com.example.yourcar.ui.fragment.searchFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yourcar.R;
import com.example.yourcar.ui.activity.SearchActivity;

/**
 * этот фрагмент вызывается вместе SearchActivity ,
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    private static final String SEARCH_FRAGMENT = "SEARCH_FRAGMENT";


    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;


    private String variable;
    // View

    private LinearLayout clickMark, clickModel, clickSteeringWheel,
            clickOil, clickDriveUnit, clickSpeed, clickVolume, clickTransmission, carYear;
    private NumberPicker datePicker;
    private TextView checkButton, carBrand, carModel, carSteeringWheel, carDriveUnit, carVolume, carTransmisson;

    // стандартный класс
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmetn_search, container, false);
    }

    /**
     * стандартный класс
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            initView(view);
            isClickable();
            setView();
            checkCarOption();
        }

        if (fragmentManager == null) {
            fragmentManager = getFragmentManager();
        }
    }

    /**
     * @param view инициализация view и установление ему прослушки клика на View.OnClickListener
     */
    private void initView(View view) {

        carBrand = view.findViewById(R.id.carBrand);
        carModel = view.findViewById(R.id.carModel);
        carSteeringWheel = view.findViewById(R.id.carSteeringWheel);
        carDriveUnit = view.findViewById(R.id.carDriveUnit);
        carVolume = view.findViewById(R.id.carVolume);
        carTransmisson = view.findViewById(R.id.carTransmission);
        carYear = view.findViewById(R.id.carYear);
        checkButton = view.findViewById(R.id.checkButton);

        clickMark = view.findViewById(R.id.clickMark);


        clickMark = view.findViewById(R.id.clickMark);
        clickModel = view.findViewById(R.id.clickModel);
        clickSteeringWheel = view.findViewById(R.id.clickSteeringWheel);
        clickOil = view.findViewById(R.id.clickOil);
        clickDriveUnit = view.findViewById(R.id.clickDriveUnit);
        clickSpeed = view.findViewById(R.id.clickSpeed);
        clickVolume = view.findViewById(R.id.clickVolume);
        clickTransmission = view.findViewById(R.id.carKPP);

        carYear.setOnClickListener(this);
        checkButton.setOnClickListener(this);
        clickMark.setOnClickListener(this);
        clickModel.setOnClickListener(this);
        clickSteeringWheel.setOnClickListener(this);
        clickOil.setOnClickListener(this);
        clickDriveUnit.setOnClickListener(this);
        clickSpeed.setOnClickListener(this);
        clickVolume.setOnClickListener(this);
        clickTransmission.setOnClickListener(this);
//        datePicker.setOnClickListener(this);


       /* datePicker.setMinValue(1990);
        datePicker.setMaxValue(2020);*/
    }

    private void checkCarOption() {

    }

    /**
     * слушатель, если марка машины не выбрана то идет блокировка выбора кнопки модель машины
     */
    private void isClickable() {
        if (SearchModel.getInstance().getMark() == null) {
            clickModel.setClickable(false);
            clickModel.setAlpha(0.5f);
        } else {
            clickModel.setClickable(true);
            clickModel.setAlpha(1);
        }
    }


    // слушатель для вью
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkButton:
                SearchActivity searchActivity = (SearchActivity) getActivity();
                searchActivity.sendResult(); // вызываем метод из активити
                break;
            case R.id.clickMark: // кнопка марка машины
                changeFragmentByClick(new MarkFragment()); // пердает нужный ему фрагмент
                break;
            case R.id.clickModel: // кнопка модель машины
                changeFragmentByClick(new ModelFragment());
                break;
            case R.id.clickSteeringWheel:
                changeFragmentByClick(new SteeringWheelFragment());
                break;
            case R.id.clickOil:
                break;
            case R.id.clickDriveUnit:
                changeFragmentByClick(new DriveUnitFragment());
                break;
            case R.id.clickSpeed:
                break;
            case R.id.clickVolume:
                break;
            case R.id.carKPP:          // кнопка КПП (коробка передач)
                changeFragmentByClick(new TransmissionFragment());
                break;
            case R.id.carYear:
                showAlertDialog();

        }
    }

    /**
     * @param fragment получает нужный фрагмент и меняет его
     */
    private void changeFragmentByClick(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.searchContainer, fragment)
                .addToBackStack(SEARCH_FRAGMENT)
                .commit();

    }

    /**
     * Установка данных для view из модельки
     */
    private void setView() {

        if (SearchModel.getInstance().getMark() != null) {       // проверяет не равно нулю,
            // то есть есть текст в модельке ?
            //                                                      под названием Марка машины
            carBrand.setText(SearchModel.getInstance().getMark());// <------- getMark ^
        }
        if (SearchModel.getInstance().getTransmission() != null) {
            carTransmisson.setText(SearchModel.getInstance().getTransmission());
        }

        if (SearchModel.getInstance().getDriveUnit() != null) {
            carDriveUnit.setText(SearchModel.getInstance().getDriveUnit());
        }
        if (SearchModel.getInstance().getModel() != null) {
            carModel.setText(SearchModel.getInstance().getModel());
        }
        if (SearchModel.getInstance().getSteeringWhile() != null) {
            carSteeringWheel.setText(SearchModel.getInstance().getSteeringWhile());
        }
        if (SearchModel.getInstance().getYearTo() != null && SearchModel.getInstance().getYearFrom() != null) {
            carVolume.setText(SearchModel.getInstance().getYearFrom() + " , " + SearchModel.getInstance().getYearTo());
        }
    }

    /**
     * обеспечивет
     */
    private void showAlertDialog() {


        final View customLayout = getLayoutInflater().inflate(R.layout.alert_dialog, null);

        final NumberPicker numberPickerFrom = customLayout.findViewById(R.id.numberPickerFrom);
        final NumberPicker numberPickerTo = customLayout.findViewById(R.id.numberPickerTo);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setView(customLayout).setTitle("Выберите год выпуска");


        // устанока цифр для первой даты
        numberPickerFrom.setMinValue(1995);
        numberPickerFrom.setClickable(false); // отключение view
        numberPickerFrom.setMaxValue(2020);

        // устанока цифр для первой даты

        numberPickerTo.setMaxValue(2020);
        numberPickerTo.setMinValue(1995);
        numberPickerTo.setClickable(false);
        numberPickerFrom.setFocusable(false);

        // установка первоначальной даты
        numberPickerTo.setValue(2020);
        numberPickerFrom.setValue(1995);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SearchModel.getInstance().setYearFrom(String.valueOf(numberPickerFrom.getValue()), String.valueOf(numberPickerTo.getValue()));
//                Toast.makeText(getContext(),String.valueOf(numberPickerFrom.getValue()),Toast.LENGTH_SHORT).show();
                setView();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
