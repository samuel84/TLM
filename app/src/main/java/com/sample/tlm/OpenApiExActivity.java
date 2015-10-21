package com.sample.tlm;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenApiExActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtAPIData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_api_ex);


        findViewById(R.id.btnNaver).setOnClickListener(this);


         txtAPIData = (TextView)findViewById(R.id.txtAPIData);

        // 네트워크 접속 여부를 구한다
        if( isNetConnect() )
            txtAPIData.setText("Network is connected");
        else
            txtAPIData.setText("Network is disconnected");

    }


    // 네트워크 접속 여부 반환
    public boolean isNetConnect() {

        try {

            ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected())
                return true;

        } catch (Exception e) {
            Log.d("tag", "Connection state error");
        }

        return false;

    }

    public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.apple:
//                    textFruit.setText("Apple");
//                    break;
//                case R.id.orange:
//                    textFruit.setText("Orange");
//                    break;
//            }


//        f1230c10bbe1022805ff92eddbccc9f7


        String addr = "http://openapi.naver.com/search?key=c1b406b32dbbbbeee5f2a36ddc14067f&query=안드로이드&target=webkr&start=1&display=4";


        new HttpReqTask().execute( "","",addr);


//        String response = getHttpConnResult(addr);
//
//        txtAPIData.setText(response);
    }

    // HTTP 요청 결과 반환

    public String getHttpConnResult(String strUrl) {

        String line, result = new String();



        try {

            // Http 클라이언트 생성

            URL url = new URL(strUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 접속 정보 설정

            conn.setReadTimeout(5000);

            conn.setConnectTimeout(5000);

            conn.setRequestMethod("GET");

            conn.setDoInput(true);

            // 접속 시작

            conn.connect();



            // 데이터 추출

            InputStream is = conn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            while((line = reader.readLine()) != null) {
                result += line + '\n';
                if( result.length() > 2000 ) break;
            }

            // 접속 종료

            reader.close();

            conn.disconnect();

        }

        catch(Exception e) {

            Log.d("tag", "HttpURLConnection error");

        }

        return result;

    }




    private class HttpReqTask extends AsyncTask<String,String,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg) {

            String response = "";


                response =(String)getHttpConnResult(arg[2]);



            return response;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtAPIData.setText(s);
        }

//        protected void onPostExecute(String result) {
//
//            txtAPIData.setText(result);
//
//        }

    }
}
