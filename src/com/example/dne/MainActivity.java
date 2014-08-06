package com.example.dne;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText titleET, subjectET, bodyET, secondsET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		titleET = (EditText) findViewById(R.id.title_et);
		subjectET = (EditText) findViewById(R.id.subject_et);
		bodyET = (EditText) findViewById(R.id.body_et);
		secondsET = (EditText) findViewById(R.id.seconds_et);
	}

	public void notify(View vobj) {
		String title = titleET.getText().toString();
		String subject = subjectET.getText().toString();
		String body = bodyET.getText().toString();
		int seconds = Integer.valueOf(secondsET.getText().toString());

		// Give the intent the values so that we can populate the notification
		// in the receiver
		Intent intent = new Intent(this, MyReceiver.class);
		intent.putExtra(MyReceiver.TITLE_KEY, title);
		intent.putExtra(MyReceiver.SUBJECT_KEY, subject);
		intent.putExtra(MyReceiver.BODY_KEY, body);

		// Schedule the alarm
		AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent,
				0);

		alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				SystemClock.elapsedRealtime() + seconds * 1000, alarmIntent);
	}
}