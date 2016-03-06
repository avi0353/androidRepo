package com.example.avee_yoyo.tictactoegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by avee-yoyo on 5/3/16.
 */
public class login extends AppCompatActivity {
    public String Name;
    boolean flag;
    private Button rtn;
    private Button back;
     private  EditText edit_text;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
         rtn = (Button) findViewById(R.id.start_game);
        edit_text = (EditText) findViewById(R.id.name);
        back=(Button)findViewById(R.id.back);
        rtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {


                        if (edit_text != null && edit_text.getText().length() != 0) {
                            Name = edit_text.getText().toString();
                        }
                        //If name is not entered, String 'Guest' is assigned to name variable
                        else {
                            Name = "Your";
                        }

                        Intent intObj = new Intent(login.this, TicTacToeActivity.class);

                        intObj.putExtra("USERNAME", Name);
                        flag = getIntent().getExtras().getBoolean("gameType");
                        intObj.putExtra("gameType", flag);
                        startActivity(intObj);
                    }
                });

        back.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        login.this.finish();
                    }
                });

    }
}
