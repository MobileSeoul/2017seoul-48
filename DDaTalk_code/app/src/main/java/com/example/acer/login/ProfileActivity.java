package com.example.acer.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.login.Login_Related.Constants;
import com.example.acer.login.Login_Related.LoginActivity;
import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.Profile_Tab.Alarm_Related.AlarmActivity;
import com.example.acer.login.Profile_Tab.DD119_Fragment;
import com.example.acer.login.Profile_Tab.Home_Fragment;
import com.example.acer.login.Profile_Tab.MyPage_Fragment;
import com.example.acer.login.Profile_Tab.Stamp_Fragment;
import com.example.acer.login.Profile_Tab.Write_Fragment;
import com.example.acer.login.Profile_Tab.Write_Related.FindSpot_Fragment;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.acer.login.R.id.imageViewAlarm;

public class ProfileActivity extends AppCompatActivity {

    TabLayout tabs;
    Home_Fragment homeFragment;
    Stamp_Fragment stampFragment;
    Write_Fragment writeFragment;
    DD119_Fragment dd119FRAGMENT;
    MyPage_Fragment myPageFragment;
    FindSpot_Fragment spotFragment;


    //알람 관련 작업
    Handler handler;
    Timer timerMTimer;
    RequestQueue rq;
    String email_param;
    int original =0;
    int original2=0;

    ActionBar actionBar;
    ImageView imageViewAlarm_set;

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent =getIntent();
        if(intent!=null){
            int get_reply_cnt = intent.getIntExtra("reply_cnt",0);
            int get_writing_no = intent.getIntExtra("writing_no",0);
            Home_Fragment frament = new Home_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("reply_cnt", get_reply_cnt);
            bundle.putInt("writing_no", get_writing_no);
            frament.setArguments(bundle);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        email_param = SharedPrefManager.getInstance(getApplicationContext()).getUserEmail();
        rq = Volley.newRequestQueue(getApplicationContext());
        timerMTimer = new Timer(true);

        handler = new Handler();
        timerMTimer.schedule(new TimerTask() {

            @Override

            public void run() {

                handler.post(new Runnable(){

                    public void run(){
                        if(original==0) {
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_ALARM_INFO, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (!response.equals("0 results")) {
                                        JSONArray arr = null;
                                        try {
                                            arr = new JSONArray(response);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        assert arr != null;
                                        original = arr.length();
//                                        Toast.makeText(getApplicationContext(),String.valueOf(original),Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(),
                                            error.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }) {
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("email", email_param);
                                    return params;
                                }
                            };
                            rq.add(stringRequest);
                        }else{
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_ALARM_INFO, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (!response.equals("0 results")) {
                                        JSONArray arr = null;
                                        try {
                                            arr = new JSONArray(response);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        assert arr != null;
                                        original2 = arr.length();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(),
                                            error.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }) {
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("email", email_param);
                                    return params;
                                }
                            };
                            rq.add(stringRequest);
                            if(original2>original){
                                imageViewAlarm_set = (ImageView)actionBar.getCustomView().findViewById(R.id.imageViewAlarm);
                                imageViewAlarm_set.setImageResource(R.drawable.actionbar_new_notice);
                                Toast.makeText(getApplicationContext(),"누군가 함께타요 버튼이나 댓글을 달았습니다!",Toast.LENGTH_LONG).show();
                            }else if(original2==original){
                                imageViewAlarm_set = (ImageView)actionBar.getCustomView().findViewById(R.id.imageViewAlarm);
                                imageViewAlarm_set.setImageResource(R.drawable.actionbar_notice);
                            }
                            original = original2;
                        }
                    }
                });
            }
        }, 0/*1000 = 액티비티 onCreate 호출 후 1초후에*/, 10000*3/*1000 = 1초마다 작업 실행, 현재는 30초마다 실행*/);


        homeFragment = new Home_Fragment();
        stampFragment = new Stamp_Fragment();
        writeFragment = new Write_Fragment();
        dd119FRAGMENT = new DD119_Fragment();
        myPageFragment = new MyPage_Fragment();
        spotFragment = new FindSpot_Fragment();


        getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment).commit();

        tabInit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
        actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.actionbar_layout, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);
        return true;
    }

    public void clickEvent(View v){
        if (v.getId() == imageViewAlarm) {
            Toast.makeText(ProfileActivity.this, "알람을 눌렀쪄염",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProfileActivity.this, AlarmActivity.class);
            startActivity(intent);
        }

    }
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;

        }
        return true;
    }*/


    void tabInit() {
        tabs = (TabLayout) findViewById(R.id.tabs);
        //noinspection ConstantConditions
        tabs.getTabAt(0).setIcon(R.drawable.home_active);
        //noinspection ConstantConditions
        tabs.getTabAt(1).setIcon(R.drawable.check_disabled);
        //noinspection ConstantConditions
        tabs.getTabAt(2).setIcon(R.drawable.add_disabled);
        //noinspection ConstantConditions
        tabs.getTabAt(3).setIcon(R.drawable.em_119_disabled);
        //noinspection ConstantConditions
        tabs.getTabAt(4).setIcon(R.drawable.my_page_disabled);
        //noinspection deprecation
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = homeFragment;
                    //noinspection ConstantConditions
                    tabs.getTabAt(0).setIcon(R.drawable.home_active);
                    //noinspection ConstantConditions
                    tabs.getTabAt(1).setIcon(R.drawable.check_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(2).setIcon(R.drawable.add_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(3).setIcon(R.drawable.em_119_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(4).setIcon(R.drawable.my_page_disabled);
                } else if (position == 1) {
                    selected = stampFragment;
                    //noinspection ConstantConditions
                    tabs.getTabAt(0).setIcon(R.drawable.home_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(1).setIcon(R.drawable.check_active);
                    //noinspection ConstantConditions
                    tabs.getTabAt(2).setIcon(R.drawable.add_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(3).setIcon(R.drawable.em_119_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(4).setIcon(R.drawable.my_page_disabled);
                } else if (position == 2) {
                    selected = writeFragment;
                    //noinspection ConstantConditions
                    tabs.getTabAt(0).setIcon(R.drawable.home_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(1).setIcon(R.drawable.check_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(2).setIcon(R.drawable.add_active);
                    //noinspection ConstantConditions
                    tabs.getTabAt(3).setIcon(R.drawable.em_119_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(4).setIcon(R.drawable.my_page_disabled);
                } else if (position == 3) {
                    selected = dd119FRAGMENT;
                    //noinspection ConstantConditions
                    tabs.getTabAt(0).setIcon(R.drawable.home_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(1).setIcon(R.drawable.check_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(2).setIcon(R.drawable.add_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(3).setIcon(R.drawable.em_119_active);
                    //noinspection ConstantConditions
                    tabs.getTabAt(4).setIcon(R.drawable.my_page_disabled);
                } else if (position == 4) {
                    selected = myPageFragment;
                    //noinspection ConstantConditions
                    tabs.getTabAt(0).setIcon(R.drawable.home_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(1).setIcon(R.drawable.check_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(2).setIcon(R.drawable.add_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(3).setIcon(R.drawable.em_119_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(4).setIcon(R.drawable.my_page_active);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
