package com.kevinhermawan.learnintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MoveForResultActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgNumber;
    private Button btnSelect;
    public static String EXTRA_SELECTED_VALUE = "extra_selected_person";
    public static int RESULT_CODE = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_for_result);

        rgNumber = (RadioGroup) findViewById(R.id.rgNumber);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSelect) {
            if(rgNumber.getCheckedRadioButtonId() != 0) {
                int value = 0;
                switch (rgNumber.getCheckedRadioButtonId()) {
                    case R.id.rb50:
                        value = 50;
                        break;
                    case R.id.rb100:
                        value = 100;
                        break;
                    case R.id.rb150:
                        value = 150;
                        break;
                    case R.id.rb200:
                        value = 200;
                        break;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value);
                setResult(RESULT_CODE, resultIntent);
                finish();
            }
        }
    }
}
