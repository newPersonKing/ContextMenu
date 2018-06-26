package com.gy.smartbardemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import until.DBUntil;

public class DetailActivity extends AppCompatActivity {

    TextView name,code,content;
    Button DELETE;
      Long id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details);
        name=findViewById(R.id.name);
        code=findViewById(R.id.code);
        content=findViewById(R.id.content);
        name.setText(getIntent().getStringExtra("name"));
        code.setText(getIntent().getStringExtra("code"));
        content.setText(  getIntent().getStringExtra("content"));
        id=getIntent().getLongExtra("id",0);
        DELETE=findViewById(R.id.delete);
        DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id==0){

                }else {
                    DBUntil.delete(id);
                    finish();
                }
            }
        });
    }
}
