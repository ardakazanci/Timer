package com.ardakazanci.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

	private CheckBox cb_single_shot;
	private Button btn_start, btn_cancel;
	private TextView tv_counter;

	Timer timer;
	MyTimerTask myTimerTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cb_single_shot = findViewById(R.id.singleShot);
		btn_start = findViewById(R.id.start);
		btn_cancel = findViewById(R.id.cancel);
		tv_counter = findViewById(R.id.counter);

		btn_start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (timer != null) {
					timer.cancel();
				}

				timer = new Timer();
				myTimerTask = new MyTimerTask();
				if (cb_single_shot.isChecked()) {
					// Tek bir işlem yapacak, mesela 10 dakika sonra beni uyar gibi. 
					timer.schedule(myTimerTask, 1000);

				} else {
					// Herbir saniye için işlem yapacak
					timer.schedule(myTimerTask, 1000, 1000);

				}

			}
		});


		btn_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if(timer != null){
					timer.cancel();
					timer = null;
				}

			}
		});


	}

	class MyTimerTask extends TimerTask {


		@Override
		public void run() {

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yy HH:mm:ss a");

			final String strDate = simpleDateFormat.format(calendar.getTime());

			runOnUiThread(new Runnable() {
				@Override
				public void run() {

					tv_counter.setText(strDate);

				}
			});


		}
	}
}
