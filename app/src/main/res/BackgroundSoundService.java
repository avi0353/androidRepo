package com.test.brzoracunanje;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class BackgroundSoundService extends Service {
private static final String TAG = null;
MediaPlayer player;
public IBinder onBind(Intent arg0) {

    return null;
}
@Override
public void onCreate() {
    // TODO Auto-generated method stub
    super.onCreate();
       player = MediaPlayer.create(this, R.raw.test_cbr);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        player.start();
}
@Override
public void onStart(Intent intent, int startId) {
    super.onStart(intent, startId);
}
public void onDestroy() {
    // TODO Auto-generated method stub
    super.onDestroy();
}

protected void onNewIntent() {
    player.pause();
}
}