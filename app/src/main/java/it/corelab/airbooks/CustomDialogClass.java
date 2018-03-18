package it.corelab.airbooks;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * Created by Roberto_Vecchio on 07/03/18.
 */

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;


    public CustomDialogClass(Activity a) {
        super(a,R.style.CustomDialog);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_book);


    }

    @Override
    public void onClick(View v) {

        dismiss();
    }
}
