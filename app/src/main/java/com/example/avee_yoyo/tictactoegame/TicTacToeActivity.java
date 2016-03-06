package com.example.avee_yoyo.tictactoegame;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
  public class TicTacToeActivity extends AppCompatActivity {

            private TicTacToeGame mGame;
            private Button mBoardButtons[];
            private String name1;
            private String name2;
            private TextView mInfoTextView;
            private TextView mPlayerOneCount;
             private TextView mTieCount;
               private TextView mPlayerTwoCount;
                  private int mPlayerOneCounter = 0;
                  private int mTieCounter = 0;
                  private int mPlayerTwoCounter = 0;
                  private TextView mPlayerOneText;
                  private TextView mPlayerTwoText;
                  private boolean mPlayerOneFirst = true;
                  private boolean mIsSinglePlayer =false;
                  private boolean mIsPlayerOneTurn =true;
                  private boolean mGameOver = false;
                  private boolean mGameType;
                  private boolean Name;

                  /** Called when the activity is first created. */
            @Override
    public void onCreate(Bundle savedInstanceState){
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                super.onCreate(savedInstanceState);
                 setContentView(R.layout.main);

                 mGameType = getIntent().getExtras().getBoolean("gameType");
                 if(mGameType)
                 {
                     name1=getIntent().getExtras().getString("USERNAME");
                 }
                else {
                     name1=getIntent().getExtras().getString("USERNAME1");
                     name2=getIntent().getExtras().getString("USERNAME2");
                 }
                  mBoardButtons = new Button[mGame.getBOARD_SIZE()];
                  mBoardButtons[0] = (Button) findViewById(R.id.one);
                  mBoardButtons[1] = (Button) findViewById(R.id.two);
                  mBoardButtons[2] = (Button) findViewById(R.id.three);
                  mBoardButtons[3] = (Button) findViewById(R.id.four);
                  mBoardButtons[4] = (Button) findViewById(R.id.five);
                  mBoardButtons[5] = (Button) findViewById(R.id.six);
                  mBoardButtons[6] = (Button) findViewById(R.id.seven);
                  mBoardButtons[7] = (Button) findViewById(R.id.eight);
                  mBoardButtons[8] = (Button) findViewById(R.id.nine);

                mInfoTextView = (TextView) findViewById(R.id.information);
                mPlayerOneCount = (TextView) findViewById(R.id.humanCount);
                mTieCount = (TextView) findViewById(R.id.tiesCount);

                mPlayerTwoCount = (TextView) findViewById(R.id.androidCount);
                mPlayerOneText = (TextView)findViewById(R.id.human);
                mPlayerTwoText = (TextView)findViewById(R.id.android);

                mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
                //mPlayerOneCount.setText(mPlayerOneCounter+"");
                mTieCount.setText(Integer.toString(mTieCounter));
                //mTieCount.setText(mTieCounter+"");
                 mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
                //mPlayerTwoCount.setText(mPlayerTwoCounter+"");

               mGame = new TicTacToeGame();
                startNewGame(mGameType,name1,name2);

               }
      @Override
         public boolean onCreateOptionsMenu(Menu menu)
       {
                MenuInflater inflater = getMenuInflater();
                 inflater.inflate(R.menu.game_menu, menu);
                return true;
       }
                @Override
        public boolean onOptionsItemSelected(MenuItem item)
        {
                  switch(item.getItemId())
                  {
                    case R.id.newGame:
                            startNewGame(mIsSinglePlayer,name1,name2);
                        break;
                      case R.id.exitGame:
                               TicTacToeActivity.this.finish();
                               break;
                  }

                  return true;
        }

      private void startNewGame(boolean IsSingle,String name1,String name2){
          Button exit_game= (Button) findViewById(R.id.exit_game);
          Button new_game = (Button) findViewById(R.id.new_game);
          exit_game.setVisibility(View.INVISIBLE);
          new_game.setVisibility(View.INVISIBLE);
            this.mIsSinglePlayer = IsSingle;
                    mGame.clearBoard();

                  for (int i = 0; i < mBoardButtons.length; i++)
                        {
                              mBoardButtons[i].setText("");
                              mBoardButtons[i].setEnabled(true);
                              mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
                              //mBoardButtons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.media_button_background));
                        }
          if(mIsSinglePlayer)
          {
              mPlayerOneText.setText(name1);
              mPlayerTwoText.setText(" Mine:");
              if (mPlayerOneFirst)
              {
                  mInfoTextView.setText(R.string.first_human);
                  mPlayerOneFirst = false;
              }
              else
              {
                  mInfoTextView.setText(R.string.turn_computer);
                  int move = mGame.getComputerMove();
                  setMove(mGame.PLAYER_TWO, move);
                  mPlayerOneFirst = true;
              }
          }
          else
          {

              mPlayerOneText.setText(name1+":");
              mPlayerTwoText.setText(name2+":");
              if (mPlayerOneFirst)
              {
                  mInfoTextView.setText(name1+"'s Turn !");
                  mPlayerOneFirst = false;
              }
              else
              {
                  mInfoTextView.setText(name2+"'s Turn !");
             //     int move = mGame.getComputerMove();
              //    setMove(mGame.PLAYER_TWO, move);
                  mPlayerOneFirst = true;
              }
          }
            mGameOver=false;

                }

      private class ButtonClickListener implements View.OnClickListener
                    {
                    int location;
                    public ButtonClickListener(int location)
                    {
                             this.location = location;
                         }
                     public void onClick(View view)
                        {
                            if (!mGameOver)
                                 {
                                       if (mBoardButtons[location].isEnabled())
                                           {
                                               if(mIsSinglePlayer)
                                               {
                                                   setMove(mGame.PLAYER_ONE, location);

                                                   int winner = mGame.checkForWinner();

                                                   if(winner == 0)
                                                   {
                                                       mInfoTextView.setText(R.string.turn_computer);
                                                       int move = mGame.getComputerMove();
                                                       setMove(mGame.PLAYER_TWO,move);
                                                       winner = mGame.checkForWinner();
                                                   }

                                                   if (winner == 0)
                                                       mInfoTextView.setText(R.string.turn_human);
                                                   else {
                                                       if (winner == 1) {
                                                           mInfoTextView.setText(R.string.result_tie);
                                                           mTieCounter++;
                                                           mTieCount.setText(Integer.toString(mTieCounter));
                                                           mGameOver = true;
                                                       } else if (winner == 2) {
                                                           mInfoTextView.setText(R.string.result_human_wins);
                                                           mPlayerOneCounter++;
                                                           mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
                                                           mGameOver = true;
                                                       }
                                                       else {
                                                           mInfoTextView.setText(R.string.result_android_wins);
                                                           mPlayerTwoCounter++;
                                                           mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
                                                           mGameOver = true;
                                                       }
                                                   }

                                               }
                                               else
                                               {
                                                   if(mIsPlayerOneTurn)
                                                   setMove(mGame.PLAYER_ONE, location);
                                                   else
                                                   setMove(mGame.PLAYER_TWO,location);

                                                   int winner = mGame.checkForWinner();

                                                   if (winner == 0)
                                                   {
                                                       if(mIsPlayerOneTurn)
                                                       {
                                                           mInfoTextView.setText(name2+"'s Turn");
                                                           mIsPlayerOneTurn=false;
                                                       }
                                                       else
                                                       {
                                                           mInfoTextView.setText(name1+"'s Turn");
                                                           mIsPlayerOneTurn=true;
                                                       }

                                                   }
                                                   else {
                                                       if (winner == 1) {
                                                           mInfoTextView.setText(R.string.result_tie);
                                                           mTieCounter++;
                                                           mTieCount.setText(Integer.toString(mTieCounter));
                                                           mGameOver = true;
                                                       } else if (winner == 2) {
                                                           mInfoTextView.setText(name1+" Won !");
                                                           mPlayerOneCounter++;
                                                           mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
                                                           mGameOver = true;
                                                       }
                                                       else{
                                                           mInfoTextView.setText(name2+" Won !");
                                                           mPlayerTwoCounter++;
                                                           mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
                                                           mGameOver = true;
                                                       }
                                                   }
                                               }
                                           }
                                  }

                            if(mGameOver)
                            {
                                // for the new_game and exit_game buttons....
                                Button exit_game= (Button) findViewById(R.id.exit_game);
                                Button new_game = (Button) findViewById(R.id.new_game);

                                if(mGameOver)
                                {
                                    exit_game.setVisibility(view.VISIBLE);
                                    new_game.setVisibility(view.VISIBLE);
                                }
                                new_game.setOnClickListener(new Button.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view){

                                        startNewGame(mGameType,name1,name2);
                                    }
                                });
                                exit_game.setOnClickListener(new Button.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view) {
                                        TicTacToeActivity.this.finish();
                                    }
                                });

                            }


                       }



                }

          private void setMove(char player, int location)
          {
                  mGame.setMove(player, location);
                   mBoardButtons[location].setEnabled(false);
                   mBoardButtons[location].setText(String.valueOf(player));
                    if (player == mGame.PLAYER_ONE)
                              mBoardButtons[location].setTextColor(Color.GREEN);
                     else
                          mBoardButtons[location].setTextColor(Color.RED);
          }
         }