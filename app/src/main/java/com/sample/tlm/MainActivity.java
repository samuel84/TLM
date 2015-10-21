package com.sample.tlm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    TextView tvTitle;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         tvTitle = (TextView) findViewById(R.id.tvTitle);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        //버튼 이벤트 핸들러 등록
        findViewById(R.id.btnAlarmStart).setOnClickListener(this);  //알람
        findViewById(R.id.btnAPI).setOnClickListener(this);         //API
    }

    /*
    * 버튼 이벤트 통합 처리
    * */
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.btnAlarmStart:
                i = new Intent(MainActivity.this,AlarmExActivity.class);
                startActivity(i);
                break;
            case R.id.fab:
                showSnackbar();
//
                tvTitle.setText("하이~~!!!");
                break;
            case R.id.btnAPI:
                i = new Intent(MainActivity.this,OpenApiExActivity.class);
                startActivity(i);
                break;
        }
    }

    private void showSnackbar() {
        Snackbar.make(fab, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
