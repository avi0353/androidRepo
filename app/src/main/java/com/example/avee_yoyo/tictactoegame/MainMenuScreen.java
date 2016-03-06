package com.example.avee_yoyo.tictactoegame;

/**
 * Created by avee-yoyo on 21/1/16.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
public class MainMenuScreen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);


        findViewById(R.id.one_player).setOnClickListener(new OnClickListener() {
            public void onClick(View V) {
                Log.d("DEBUG", "One Player Button Pressed!");
                Intent intent = new Intent(MainMenuScreen.this, login.class);
                //Intent intent = new Intent(MainMenuScreen.this, TicTacToeActivity.class);
                intent.putExtra("gameType", true);
                startActivityForResult(intent, 0);
            }
        });
        findViewById(R.id.two_player).setOnClickListener(new OnClickListener() {
            public void onClick(View V) {
                Log.d("DEBUG", "Two Player Button Pressed!");
                Intent intent = new Intent(MainMenuScreen.this, login1.class);
                //Intent intent = new Intent(MainMenuScreen.this, TicTacToeActivity.class);

                intent.putExtra("gameType", false);
                startActivityForResult(intent, 0);
            }
        });
        findViewById(R.id.exit_game).setOnClickListener(new OnClickListener() {
            public void onClick(View V) {
                Log.d("DEBUG", "Exit Button Pressed!");
                AlertDialog.Builder builder = new AlertDialog.Builder(MainMenuScreen.this);
                builder.setMessage("Thankyou for your time :)")
                        .setCancelable(false)
                        .setPositiveButton("Okay!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainMenuScreen.this.finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

}


