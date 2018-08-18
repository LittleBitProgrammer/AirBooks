package it.corelab.studios.airbooks.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import it.corelab.studios.airbooks.R;

/**
 * Created by Roberto_Vecchio on 07/03/18.
 */

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    private int layout;
    private String firstColor;

    public CustomDialogClass(Activity activity,int layout, String firstColor) {
        super(activity, R.style.CustomDialog);
        this.layout = layout;
        this.firstColor = firstColor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layout);

        LinearLayout header = findViewById(R.id.header_custom_dialog_home);
        Button cancel = findViewById(R.id.cancel_button_home);
        RecyclerView recyclerView = findViewById(R.id.recycler_custom_dialog);
        header.setBackgroundColor(Color.parseColor("#" + firstColor));

        recyclerView.setItemViewCacheSize(10);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);

        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.cancel_button_home: dismiss();
        }
    }
}
