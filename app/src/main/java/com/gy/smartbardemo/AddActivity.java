package com.gy.smartbardemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import until.DBUntil;

public class AddActivity extends AppCompatActivity {

    Button btn;
    EditText cmovie_name,emovie_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add);
        btn=findViewById(R.id.add_movie);
        cmovie_name=findViewById(R.id.cmovie_name);
        emovie_name=findViewById(R.id.emovie_name);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie=new Movie();
                movie.setEMovieName(emovie_name.getText().toString());

                movie.setMovieName(cmovie_name.getText().toString());

                DBUntil.insert(movie);
                finish();
            }
        });
    }
}
