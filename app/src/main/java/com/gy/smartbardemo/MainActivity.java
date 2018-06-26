package com.gy.smartbardemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import until.DBUntil;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    int currentposition;
    boolean isfinish=false;
    private HomeWatcherReceiver mHomeWatcherReceiver = null;
    boolean isC=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapter(new MyAdapter.CallBack() {
            @Override
            public void getView(View view, int position) {
                registerForContextMenu(view);
            }

            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("code",movies.get(position).getCode());
                intent.putExtra("name",movies.get(position).getMovieName());
                intent.putExtra("content",movies.get(position).getMovieContent());
                intent.putExtra("id",movies.get(position).getId());
                startActivityForResult(intent,300);
            }
        });

        recyclerView.setAdapter(adapter);

        getDatasC();

        mHomeWatcherReceiver = new HomeWatcherReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(mHomeWatcherReceiver, filter);
    }
    private List<Movie> movies=new ArrayList<>();
    private void getDatasC(){
        movies=DBUntil.query();
        adapter.setDatas(movies,isC);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.chinese:
                isC=true;
                getDatasC();
                break;
            case R.id.english:
                isC=false;
                getDatasC();
                break;
            case R.id.add:
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,200);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        currentposition= (int) v.getTag();
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.cancle:
                movies.get(currentposition).setCode("");
                adapter.notifyItemChanged(currentposition);
                break;
            case R.id.add:
                Intent intent=new Intent(this,CodeActivity.class);
                startActivityForResult(intent,100);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
             Movie movie=movies.get(currentposition);
            movie.setMovieContent(data.getStringExtra("content"));
            movie.setCode("评分："+data.getStringExtra("code"));
            DBUntil.upDate(movie);
           getDatasC();
        }else {
            getDatasC();
        }
    }
    Handler handler=new Handler();
    @Override
    public void onBackPressed() {
        if (isfinish){
            this.finish();
        }else {
            Toast.makeText(this,"再点击一次退出",Toast.LENGTH_SHORT).show();
            isfinish=true;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isfinish=false;
                }
            },2000);
        }
    }

    public class HomeWatcherReceiver extends BroadcastReceiver {

        private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
        private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {

            String intentAction = intent.getAction();
            if (TextUtils.equals(intentAction, Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (TextUtils.equals(SYSTEM_DIALOG_REASON_HOME_KEY, reason)) {
                    Toast.makeText(context,"你点击了home键",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
