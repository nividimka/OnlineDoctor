package dkgroup.kz.onlinedoctor.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import dkgroup.kz.onlinedoctor.R;
import dkgroup.kz.onlinedoctor.ui.MainActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    int REQUEST_CODE = 1;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("message", "receive");
        sendNotification(remoteMessage.getData().get("body"));

    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel("channel_id", "main", importance);
            manager.createNotificationChannel(mChannel);
        }
        Intent i = new Intent(this, MainActivity.class);
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle(messageBody)
                .setSmallIcon(R.drawable.ic_menu_white_24dp)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId("channel_id");
        }

        builder.setContentIntent(PendingIntent.getActivity(this, REQUEST_CODE, i, PendingIntent.FLAG_UPDATE_CURRENT))
                .build();


        manager.notify(100, builder.build());
//        Uri defaultRingone = RingtoneManager
    }
}