package com.kevinhermawan.learnintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMoveActivity, btnMoveActivityData, btnMoveActivityObject, btnDialNumber, btnMoveActivityForResult;
    private TextView tvResult;
    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveActivity = (Button) findViewById(R.id.btnMoveActivity);
        btnMoveActivity.setOnClickListener(this);
        btnMoveActivityData = (Button) findViewById(R.id.btnMoveActivityData);
        btnMoveActivityData.setOnClickListener(this);
        btnMoveActivityObject = (Button) findViewById(R.id.btnMoveActivityObject);
        btnMoveActivityObject.setOnClickListener(this);
        btnDialNumber = (Button) findViewById(R.id.btnDialNumber);
        btnDialNumber.setOnClickListener(this);
        btnMoveActivityForResult = (Button) findViewById(R.id.btnMoveActivityForResult);
        btnMoveActivityForResult.setOnClickListener(this);
        tvResult = (TextView) findViewById(R.id.tvResultForActivity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMoveActivity:
                Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.btnMoveActivityData:
                Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Kevin Hermawan");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 19);
                startActivity(moveWithDataIntent);
                break;
            case R.id.btnMoveActivityObject:
                Person mPerson = new Person();
                mPerson.setName("Kevin Hermawan");
                mPerson.setAge(19);
                mPerson.setEmail("kevinhermawanx@gmail.com");
                mPerson.setCity("Jakarta");

                Intent moveWithObjectIntent = new Intent(MainActivity.this, MoveWithObjectActivity.class);
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, mPerson);
                startActivity(moveWithObjectIntent);
                break;
            case R.id.btnDialNumber:
                String phoneNumber = "0895800271271";
                Intent dialNumberIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialNumberIntent);
                break;
            case R.id.btnMoveActivityForResult:
                Intent intent = new Intent(MainActivity.this, MoveForResultActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE) {
            int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
            tvResult.setText("RESULT : "+selectedValue);
        }
    }
}
