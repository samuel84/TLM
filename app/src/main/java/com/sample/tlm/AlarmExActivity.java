package com.sample.tlm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class AlarmExActivity extends AppCompatActivity {

    //알람 매니져
    AlarmManager mAlarmMgr;

    Vibrator mVib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_ex);

        // 알람 관리자 핸들을 구한다
        mAlarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);

        mVib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    }

    // 1회 알람 시작 - 시간 간격 지정

    public void onBtnAlarm1() {

        mVib.vibrate(1000);
        // 수행할 동작을 생성

        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(AlarmExActivity.this, 0, intent, 0);

        // 1회 알람 시작

        mAlarmMgr.set(AlarmManager.RTC, System.currentTimeMillis() + 5000, pIntent);

        int soundId = sound.load(this, R.raw.siren, 1);

        int streamId = sound.play(soundId, 1.0F, 1.0F, 1, -1, 1.0F);
    }



    // 반복 알람 시작 - 특정 시간 지정

    public void onBtnAlarm2() {

        // 수행할 동작을 생성

        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(AlarmExActivity.this, 0, intent, 0);

        // 알람이 발생할 정확한 시간을 지정
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.add(Calendar.SECOND, 5);

        // 반복 알람 시작
        mAlarmMgr.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 5000, pIntent);

    }



    // 알람 중지
    public void onBtnStop() {

        // 수행할 동작을 생성
        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(AlarmExActivity.this, 0, intent, 0);

        // 알람 중지

        mAlarmMgr.cancel(pIntent);

        sound.stop(streamId);
    }



    public void onClick(View v) {

        switch( v.getId() ) {

            case R.id.btnAlarm1 :
                // 1회 알람 시작 - 시간 간격 지정
                onBtnAlarm1();
                break;

            case R.id.btnAlarm2 :
                // 반복 알람 시작 - 특정 시간 지정
                onBtnAlarm2();
                break;

            case R.id.btnStop :
                // 알람 중지
                onBtnStop();
                break;

        }

    }
}
