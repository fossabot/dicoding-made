package com.kevinhermawan.learnalarmmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvOneTimeAlarmDate, tvOneTimeAlarmTime, tvRepeatingAlarmTime;
    private EditText edtOneTimeMessage, edtRepeatingMessage;
    private Button btnOneTimeAlarmDate, btnOneTimeAlarmTime, btnSetOneTimeAlarm, btnRepeatingAlarmTime, btnSetRepeatingAlarm, btnCancelAlarm;
    private Calendar calOneTimeDate, calOneTimeTime, calRepeatTimeTime;
    private AlarmReceiver alarmReceiver;
    private AlarmPreference alarmPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOneTimeAlarmDate = (TextView) findViewById(R.id.tvOneTimeAlarmDate);
        tvOneTimeAlarmTime = (TextView) findViewById(R.id.tvOneTimeAlarmTime);
        tvRepeatingAlarmTime = (TextView) findViewById(R.id.tvRepeatingAlarmTime);
        edtOneTimeMessage = (EditText) findViewById(R.id.edtOneTimeAlarmMessage);
        edtRepeatingMessage = (EditText) findViewById(R.id.edtRepeatingAlarmMessage);
        btnOneTimeAlarmDate = (Button) findViewById(R.id.btnOneTimeAlarmDate);
        btnOneTimeAlarmDate.setOnClickListener(this);
        btnOneTimeAlarmTime = (Button) findViewById(R.id.btnOneTimeAlarmTime);
        btnOneTimeAlarmTime.setOnClickListener(this);
        btnSetOneTimeAlarm = (Button) findViewById(R.id.btnSetOneTimeAlarm);
        btnSetOneTimeAlarm.setOnClickListener(this);
        btnRepeatingAlarmTime = (Button) findViewById(R.id.btnRepeatingAlarmTime);
        btnRepeatingAlarmTime.setOnClickListener(this);
        btnSetRepeatingAlarm = (Button) findViewById(R.id.btnSetRepeatingAlarm);
        btnSetRepeatingAlarm.setOnClickListener(this);
        btnCancelAlarm = (Button) findViewById(R.id.btnCancelAlarm);
        btnCancelAlarm.setOnClickListener(this);

        calOneTimeDate = Calendar.getInstance();
        calOneTimeTime = Calendar.getInstance();
        calRepeatTimeTime = Calendar.getInstance();

        alarmPreference = new AlarmPreference(this);
        alarmReceiver = new AlarmReceiver();

        if(TextUtils.isEmpty(alarmPreference.getOneTimeDate())) {
            setOneTimeText();
        }

        if (!TextUtils.isEmpty(alarmPreference.getRepeatingTime())){
            setRepeatingText();
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnOneTimeAlarmDate) {
            final Calendar currentDate = Calendar.getInstance();
            new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    calOneTimeDate.set(year, monthOfYear, dayOfMonth);
                    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    tvOneTimeAlarmDate.setText(mSimpleDateFormat.format(calOneTimeDate.getTime()));
                }
            }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
        }else if(view.getId() == R.id.btnOneTimeAlarmTime) {
            final Calendar currentDate = Calendar.getInstance();
            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    calOneTimeTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calOneTimeTime.set(Calendar.MINUTE, minute);
                    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm");
                    tvOneTimeAlarmTime.setText(mSimpleDateFormat.format(calOneTimeTime.getTime()));
                }
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
        }else if (view.getId() == R.id.btnRepeatingAlarmTime) {
            final Calendar currentDate = Calendar.getInstance();
            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    calRepeatTimeTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calRepeatTimeTime.set(Calendar.MINUTE, minute);
                    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:MM");
                    tvRepeatingAlarmTime.setText(mSimpleDateFormat.format(calRepeatTimeTime.getTime()));
                }
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
        }else if(view.getId() == R.id.btnSetOneTimeAlarm) {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String oneTimeDate = mSimpleDateFormat.format(calOneTimeDate.getTime());
            SimpleDateFormat mSimpleTimeFormat = new SimpleDateFormat("HH:mm");
            String oneTimeTime = mSimpleTimeFormat.format(calOneTimeTime.getTime());
            String oneTimeMessage = edtOneTimeMessage.getText().toString();
            alarmPreference.setOneTimeDate(oneTimeDate);
            alarmPreference.setOneTimeTime(oneTimeTime);
            alarmPreference.setOneTimeMessage(oneTimeMessage);

            alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME,
                    alarmPreference.getOneTimeDate(),
                    alarmPreference.getOneTimeTime(),
                    alarmPreference.getOneTimeMessage());
        }else if (view.getId() == R.id.btnSetRepeatingAlarm) {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm");
            String repeatingTimeTime = mSimpleDateFormat.format(calRepeatTimeTime.getTime());
            String repeatingTimeMessgae = edtRepeatingMessage.getText().toString();
            alarmPreference.setRepeatingTime(repeatingTimeTime);
            alarmPreference.setRepeatingMessage(repeatingTimeMessgae);
            alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING, alarmPreference.getRepeatingTime(), alarmPreference.getRepeatingMessage());
        }else if(view.getId() == R.id.btnCancelAlarm) {
            alarmReceiver.cancelAlarm(this, alarmReceiver.TYPE_REPEATING);
        }
    }

    private void setOneTimeText() {
        tvOneTimeAlarmTime.setText(alarmPreference.getOneTimeTime());
        tvOneTimeAlarmDate.setText(alarmPreference.getOneTimeDate());
        edtOneTimeMessage.setText(alarmPreference.getOneTimeMessage());
    }

    private void setRepeatingText() {
        tvRepeatingAlarmTime.setText(alarmPreference.getRepeatingTime());
        edtRepeatingMessage.setText(alarmPreference.getRepeatingMessage());
    }
}
