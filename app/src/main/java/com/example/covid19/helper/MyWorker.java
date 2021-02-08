package com.example.covid19.helper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.covid19.MainActivity;
import com.example.covid19.R;

public class MyWorker extends Worker {
    private Context context;
    private final String CHANNEL_ID = "my_channel_01";
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        String msg = getInputData().getString("msg");
        sendNotification(msg);


        return Result.success();
    }

    private void sendNotification(String msg) {

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 123, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setContentTitle("Covid-19 TodayUpdate");
        builder.setContentText(msg);
        builder.setSmallIcon(R.drawable.ic_notifications_active_black_24dp);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, "some description",
                            NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }


        manager.notify(123, builder.build());


    }
}
