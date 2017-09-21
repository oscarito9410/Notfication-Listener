package com.oscar.notficacionlistener.Utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

import com.oscar.notficacionlistener.R;
import com.oscar.notficacionlistener.UI.MainActivity;

/**
 * Created by oemy9 on 21/09/2017.
 */

public class Utilerias {
    public static void sendNotification(Context ctx, String text){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx);
        builder.setContentText(text);
        builder.setContentTitle(ctx.getString( R.string.app_name ) );
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(PendingIntent.getActivity(ctx, 0,
                new Intent(ctx, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
        builder.setAutoCancel(true);
        NotificationManagerCompat.from(ctx).notify(0, builder.build());

    }
}
