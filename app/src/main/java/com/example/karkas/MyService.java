package com.example.karkas;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.HashMap;

public class MyService extends Service {

    long startTime;
    String message;
    //MyDataBase openHelper = ;
    HashMap<String, Object> hashMap;


    public MyService() {
        startTime = System.currentTimeMillis();

        //hashMap = MyDataBase.getInformation(1);

        //message = "r";
        message = "Сообщаем вам, что ваш поезд " + /**hashMap.containsKey("number") +**/
                ", направление " +
                /**hashMap.containsKey("rout") +**/ " отправится с  N-ной платформы вокзала"  +
                /**hashMap.containsKey("place") +**/ "через  2 часа. Просим вас не опаздывать на посадку";
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        long currentTime = System.currentTimeMillis();


        while (true){
            if (currentTime - startTime > 8000) {
                Log.d("myTag", "it's work"); break;}
            currentTime = System.currentTimeMillis();
        }

        Intent backIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, backIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        //Уведомление
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Project")
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Уважаемый" +/** hashMap.containsKey("name") +**/ "!").setContentText(message).setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notificationManager.notify(4510, notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}