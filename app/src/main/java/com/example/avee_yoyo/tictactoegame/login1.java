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
public class login1 extends AppCompatActivity {
    public String Name1;
    public String Name2;
    boolean flag;
    private Button rtn;
    private  EditText edit_text1;
    private EditText edit_text2;
    private Button back;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login1);
        rtn = (Button) findViewById(R.id.button);
        edit_text1 = (EditText) findViewById(R.id.first_player);
        edit_text2 = (EditText) findViewById(R.id.second_player);
        back=(Button)findViewById(R.id.back);
        rtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {


                        if (edit_text1 != null && edit_text1.getText().length() != 0 && edit_text2!=null && edit_text2.getText().length()!=0) {
                            Name1 = edit_text1.getText().toString();
                            Name2 = edit_text2.getText().toString();
                        }
                        //If name is not entered, String 'Guest' is assigned to name variable
                        else {
                            Name1 = "Player1";
                            Name2 = "Player2";
                        }

                        Intent intObj = new Intent(login1.this, TicTacToeActivity.class);

                        intObj.putExtra("USERNAME1", Name1);
                        intObj.putExtra("USERNAME2",Name2);
                        flag = getIntent().getExtras().getBoolean("gameType");
                        intObj.putExtra("gameType", flag);
                        startActivity(intObj);
                    }
                });

        back.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        login1.this.finish();

                    }
                });

    }
}
