package com.artifucio.demomvp.presentanción.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.artifucio.demomvp.R;

public class ProgressDialogCustom extends ProgressDialog {


    public ProgressDialogCustom(Context context, String text) {
        super(context);
        setIndeterminate(true);
        setMessage(text);
        setProgressStyle(ProgressDialog.STYLE_SPINNER);
        setCancelable(false);
    }
}
