package com.argdev.mypetslife.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.argdev.mypetslife.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PetInfoActivity extends AppCompatActivity {


    FloatingActionButton btn_addReminder;
    GridLayout gl_addReminder;



    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);


        btn_addReminder = findViewById(R.id.addReminder);
        gl_addReminder = findViewById(R.id.gl_addReminder);



        btn_addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(gl_addReminder.getVisibility() == View.GONE){

                    btn_addReminder.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
                    btn_addReminder.setImageResource(R.drawable.ic_close_24);
                    gl_addReminder.setVisibility(View.VISIBLE);
                }
                else
                {
                    btn_addReminder.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF03DAC5")));
                    btn_addReminder.setImageResource(R.drawable.ic_add_24);
                    gl_addReminder.setVisibility(View.GONE);
                }

            }



        });



    }
}