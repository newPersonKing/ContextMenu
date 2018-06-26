package com.gy.smartbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import view.MyRatingView;

public class CodeActivity extends AppCompatActivity {

    MyRatingView myRatingView;
    Button btn;
    EditText editText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_code);
        myRatingView=findViewById(R.id.ratingBar);
        btn=findViewById(R.id.commit);
        editText=findViewById(R.id.content);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("code",myRatingView.getScore()+"分");
                intent.putExtra("content",editText.getText().toString());
                setResult(200,intent);
                finish();
            }
        });
    }
}
