package com.example.yourcar.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * класс используется для хранения данных пользователя такие как логин и sms из телефона
 */
public class SharedPreferencesManager {


    private final static String PASSWORD_OF_OTP = "PASSWORD";
    private final static String NUMBER_OTP_LOGIN = "LOGIN";
    private final static String APP_NAME = "com.example.yourcar";

    private static SharedPreferencesManager sIntance;
    private static SharedPreferences sharedPreferences;


    public SharedPreferencesManager(Context context) {
        sharedPreferences = getSharedPreferences(context.getApplicationContext());
    }

    private SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
    }

    /**
     * @param context паттерн используем ООП чтобы используемый объект класса был одним и темжым
     * @return возвращает объект класса
     */
    public static SharedPreferencesManager getInstance(Context context){
        if (sIntance == null){
            sIntance = new SharedPreferencesManager(context);
        }
        return sIntance;
    }

    /**
     * @param number устанавливаем логин
     * @param password устанавливаем пароль
     */
    public void setLogin(String number, String password){
        sharedPreferences.edit().putString(NUMBER_OTP_LOGIN,number).apply();
        sharedPreferences.edit().putString(PASSWORD_OF_OTP,password).apply();

    }

    /**
     * @return получаем логин, номер
     */
    public String getLogin(){
        return sharedPreferences.getString(NUMBER_OTP_LOGIN,null);
    }


    /**
     * @return получаем пароль, смс код
     */
    public String getPassword(){
        return sharedPreferences.getString(PASSWORD_OF_OTP,null);
    }

    /**
     * @return возвращает true если user зареган false если нет
     */
    public boolean checkLogin(){
        if (getLogin() == null || getPassword() == null){
            return false;
        }
        else {
            return true;
        }

    }
}
