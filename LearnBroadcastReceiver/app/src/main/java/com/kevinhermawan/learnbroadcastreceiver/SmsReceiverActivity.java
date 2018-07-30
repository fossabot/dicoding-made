package com.kevinhermawan.learnbroadcastreceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SmsReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvPhoneNumber, tvMessage;
    private Button btnClose;
    public static final String EXTRA_SMS_NO = "extra_sms_no";
    public static final String EXTRA_SMS_MESSAGE = "extra_sms_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receiver);

        tvPhoneNumber = (TextView) findViewById(R.id.tvPhoneNumber);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
        String senderNo = getIntent().getStringExtra(EXTRA_SMS_NO);
        String senderMessage = getIntent().getStringExtra(EXTRA_SMS_MESSAGE);
        tvPhoneNumber.setText("From : "+senderNo);
        tvMessage.setText(senderMessage);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnClose) {
            finish();
        }
    }
}
