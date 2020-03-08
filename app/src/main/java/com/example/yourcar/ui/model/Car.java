package com.example.yourcar.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Car implements Parcelable {

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getRun() {
        return run;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getVolume() {
        return volume;
    }

    public String getFuel() {
        return fuel;
    }

    public String getPower() {
        return power;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getSteeringWheel() {
        return steeringWheel;
    }

    public String getDriveUnit() {
        return driveUnit;
    }

    public String getCost() {
        return cost;
    }

    public Car(String url, String name, String shortName, String model, String year, String run, String bodyType, String volume, String fuel, String power, String transmission, String steeringWheel, String driveUnit, String cost) {
        this.url = url;
        this.name = name;
        this.shortName = shortName;
        this.model = model;
        this.year = year;
        this.run = run;
        this.bodyType = bodyType;
        this.volume = volume;
        this.fuel = fuel;
        this.power = power;
        this.transmission = transmission;
        this.steeringWheel = steeringWheel;
        this.driveUnit = driveUnit;
        this.cost = cost;
    }

    private String url;
    private String name;
    @SerializedName("short_name")
    private String shortName;
    private String model;
    private String year;
    private String run;
    @SerializedName("body_type")
    private String bodyType;
    private String volume;
    private String fuel;
    private String power;
    private String transmission;
    @SerializedName("steering_wheel")
    private String steeringWheel;
    @SerializedName("drive_unit")
    private String driveUnit;
    private String cost;

    protected Car(Parcel in) {
        url = in.readString();
        name = in.readString();
        shortName = in.readString();
        model = in.readString();
        year = in.readString();
        run = in.readString();
        bodyType = in.readString();
        volume = in.readString();
        fuel = in.readString();
        power = in.readString();
        transmission = in.readString();
        steeringWheel = in.readString();
        driveUnit = in.readString();
        cost = in.readString();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(name);
        dest.writeString(shortName);
        dest.writeString(model);
        dest.writeString(year);
        dest.writeString(run);
        dest.writeString(bodyType);
        dest.writeString(volume);
        dest.writeString(fuel);
        dest.writeString(power);
        dest.writeString(transmission);
        dest.writeString(steeringWheel);
        dest.writeString(driveUnit);
        dest.writeString(cost);
    }
}
