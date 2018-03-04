package it.corelab.airbooks.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import it.corelab.airbooks.R;

public class AddDescription extends AppCompatActivity {

    private ImageButton leftArrow;
    private ImageButton dismiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description);

        final Intent returnIntent = new Intent(getApplicationContext(),Categories.class);
        final Intent dismissIntent = new Intent(getApplicationContext(),MainActivity.class);

        leftArrow = findViewById(R.id.left_arrow_add_description);
        dismiss = findViewById(R.id.dismiss_button_description);


        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View.OnClickListener returnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent.setFlags(returnIntent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(returnIntent);
                overridePendingTransition(R.anim.intent_from_right_in, R.anim.intent_from_right_out);
            }
        };

        View.OnClickListener dismissListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissIntent.setFlags(dismissIntent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(dismissIntent);
                overridePendingTransition(R.anim.intent_from_top_in, R.anim.intent_from_top_out);
            }
        };

        leftArrow.setOnClickListener(returnListener);
        dismiss.setOnClickListener(dismissListner);
    }
}
