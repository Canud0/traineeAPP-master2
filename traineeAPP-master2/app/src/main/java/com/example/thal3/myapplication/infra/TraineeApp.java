package com.example.thal3.myapplication.infra;
import android.app.Application;
import android.content.Context;

public class TraineeApp extends Application {
    private static Context mContext;
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
    public static Context getContext() {
        return mContext;
    }
}
