package com.example.acer.login.Profile_Tab.Home_reply;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.login.Login_Related.Constants;
import com.example.acer.login.Login_Related.LoginActivity;
import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.ProfileActivity;
import com.example.acer.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class ReplyActivity extends AppCompatActivity {

    RequestQueue rq;
    ReplyItemAdapter replyItemAdapter;
    String content,date,email,emailOfWriting,writing_no_param,UserEmail_Present,rental_spot;
    int writing_no,reply_no/*,picture*/;
    EditText editTextReply;
    //    TextView textViewEmail, textViewContent;
//    ImageView imageView;
    TextView textViewRental_Spot;
    Button buttonWriteReply;
    ListView reply;


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
        intent.putExtra("reply_cnt",replyItemAdapter.getCount());
        intent.putExtra("writing_no",writing_no_param);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();
//        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        //noinspection ConstantConditions
        getSupportActionBar().hide();
        UserEmail_Present = SharedPrefManager.getInstance(this).getUserEmail();
        Intent intent = getIntent();

        emailOfWriting = String.valueOf(intent.getExtras().get("email"));
//        content = String.valueOf(intent.getExtras().get("content"));
//        picture = (int)intent.getExtras().get("picture");
        writing_no_param = String.valueOf(intent.getExtras().get("writing_no"));
        rental_spot = String.valueOf(intent.getExtras().get("rental_spot"));

//        textViewEmail = (TextView)findViewById(textViewEmail);
//        textViewContent = (TextView)findViewById(textViewContent);
//        imageView = (ImageView)findViewById(imageView);
        editTextReply = (EditText)findViewById(R.id.editTextReply);
        buttonWriteReply = (Button)findViewById(R.id.buttonWriteReply);
        textViewRental_Spot = (TextView)findViewById(R.id.textViewRental_Spot);

//        textViewEmail.setText(email);
//        textViewContent.setText(content);
//        imageView.setImageResource(picture);
        textViewRental_Spot.setText(rental_spot);


        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(ReplyActivity.this, "Refresh success", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        reply = (ListView)findViewById(R.id.reply);

        replyItemAdapter = new ReplyItemAdapter();
        rq = Volley.newRequestQueue(getApplicationContext());
        sendJsonRequestToReply(writing_no_param,reply);



        buttonWriteReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Reply_Content = editTextReply.getText().toString();
                long time = System.currentTimeMillis();

                SimpleDateFormat dayTime = new SimpleDateFormat("MMMM yyyy", Locale.KOREAN);

                String cur_date = dayTime.format(new Date(time));//9시간 느린문제 해결해야됨


                int param_reply_no = insertContentToReply(Reply_Content,UserEmail_Present,writing_no_param,emailOfWriting);
                int writing_no = Integer.parseInt(writing_no_param);


                ReplyItem newRow = new ReplyItem(param_reply_no,UserEmail_Present,Reply_Content,writing_no,cur_date);
                replyItemAdapter.addItem(newRow);
                reply.setAdapter(replyItemAdapter);
                replyItemAdapter.notifyDataSetChanged();
                editTextReply.setText(null);
                InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });





    }
    public void sendJsonRequestToReply(final String writing_no_param, final ListView listView){

        final StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REPLY_INFO, new Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(!response.equals("0 results")){
                    JSONArray arr = null;
                    try {
                        arr = new JSONArray(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    assert arr != null;
                    for (int i = 0; i < arr.length(); i++) {
                        try {
                            JSONObject obj = arr.getJSONObject(i);
                            content = obj.getString("content");
                            date = obj.getString("date");
                            String month = date.substring(5,7);
                            String year = date.substring(0,4);
                            String day = date.substring(8,10);
                            date = year+"년 "+month+"월 "+day+"일 ";
                            email = obj.getString("email");
                            reply_no = obj.getInt("reply_no");
                            writing_no = obj.getInt("writing_no");
                            ReplyItem reply = new ReplyItem(reply_no,email, content,writing_no,date);
                            replyItemAdapter.addItem(reply);
                            listView.setAdapter(replyItemAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("writing_no",writing_no_param);
                return params;
            }
        };
        rq.add(stringRequest);
    }

    public int insertContentToReply(final String Reply_Content, final String shared_email, final String writing_no_param,final String emailOfWriting){
        final int[] reply_no = {0};
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REPLY_WRITECONTENT, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error")){
                        reply_no[0] = obj.getInt("reply_no");
                        Toast.makeText(getApplicationContext(),
                                "댓글이 성공적으로 작성되었습니다.",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("writing_no",writing_no_param);
                params.put("content",Reply_Content);
                params.put("email",shared_email);
                params.put("emailOfWriting",emailOfWriting);
                return params;
            }
        };
        rq.add(stringRequest);
        return reply_no[0];
    }
}