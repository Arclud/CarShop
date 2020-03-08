package com.example.yourcar.ui.fragment.searchFragment;

/**
 * простая моделька котораю связывает фрагменты, через эту модельку записывается и читаются машинные данные
 */
public class SearchModel {

    private String mark;
    private String model;
    private String steeringWhile;
    private String fuel;
    private String driveUnit;
    private String yearFrom;
    private String yearTo;
    private String transmission;
    private static SearchModel searchModel = null;

    public static SearchModel getSearchModel() {
        return searchModel;
    }

    public static void setSearchModel(SearchModel searchModel) {
        SearchModel.searchModel = searchModel;
    }


    public  static SearchModel getInstance() {
        if (searchModel == null){
            searchModel = new SearchModel();
        }
        return searchModel;
    }



    public String getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(String yearFrom, String yearTo) {
        this.yearFrom = yearFrom;
        this.yearTo = yearTo;
    }


    // коробка передач!
    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
    // марка машины
    public String getMark() {
        return mark;
    }
    // установка марки машины
    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSteeringWhile() {
        return steeringWhile;
    }

    public void setSteeringWhile(String steeringWhile) {
        this.steeringWhile = steeringWhile;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getDriveUnit() {
        return driveUnit;
    }

    public void setDriveUnit(String driveUnit) {
        this.driveUnit = driveUnit;
    }

    public String getYearTo() {
        return yearTo;
    }
}
