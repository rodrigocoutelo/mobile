package com.example.recoin.Util;

import android.content.Context;
import android.widget.Toast;

public class MyToast {

    private Context context;
    //private String msg;

    public MyToast (Context context) {
        this.context = context;
    }
    public void msgL(String msg) {
        Toast.makeText(this.context, msg, Toast.LENGTH_LONG).show();
    }
    public void msgS(String msg) {
        Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show();
    }
}
