package com.kevinhermawan.learnactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLength, edtWidth, edtHeight;
    private Button btnResult;
    private TextView tvResult;

    private static final String RESULT_STATE = "result_state";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(RESULT_STATE, tvResult.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLength = (EditText) findViewById(R.id.edtLength);
        edtWidth = (EditText) findViewById(R.id.edtWidth);
        edtHeight = (EditText) findViewById(R.id.edtHeight);
        btnResult = (Button) findViewById(R.id.btnResult);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnResult.setOnClickListener(this);

        if(savedInstanceState != null) {
            String result = savedInstanceState.getString(RESULT_STATE);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnResult) {
            String length = edtLength.getText().toString().trim();
            String width = edtWidth.getText().toString().trim();
            String height =  edtHeight.getText().toString().trim();
            boolean isEmptyFileds = false;

            if(TextUtils.isEmpty(length)) {
                isEmptyFileds = true;
                edtLength.setError("Field caanot be null");
            }

            if(TextUtils.isEmpty(width)) {
                isEmptyFileds = true;
                edtWidth.setError("Field caanot be null");
            }

            if(TextUtils.isEmpty(height)) {
                isEmptyFileds = true;
                edtHeight.setError("Field caanot be null");
            }

            if(!isEmptyFileds) {
                double lengthDouble = Double.parseDouble(length);
                double widthDouble = Double.parseDouble(width);
                double heightDouble = Double.parseDouble(height);
                double volume = lengthDouble * widthDouble * heightDouble;
                tvResult.setText(String.valueOf(volume));
            }
        }
    }
}
