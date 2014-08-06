package com.example.dne;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
public  static final String TITLE_KEY = "TITLE_KEY";
	public static final String BODY_KEY = "body_key";
	public static final String SUBJECT_KEY = "subject_key";

	@Override
	public void onReceive(Context context, Intent intent) {
		String title = intent.getStringExtra(TITLE_KEY);
		String subject = intent.getStringExtra(SUBJECT_KEY);
		String body = intent.getStringExtra(BODY_KEY);
		
		PendingIntent pending = PendingIntent.getActivity(
				context, 0,
				new Intent(context, MainActivity.class), 0);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context).setSmallIcon(android.R.drawable.stat_notify_more)
				.setContentTitle(title).setContentText(body).setTicker(subject)
				.setContentIntent(pending)
				.setWhen(System.currentTimeMillis());
		
		NotificationManager NM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		NM.notify(0, mBuilder.build());		
	}

}
