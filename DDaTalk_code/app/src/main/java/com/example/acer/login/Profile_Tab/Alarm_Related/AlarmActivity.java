package com.example.acer.login.Profile_Tab.Alarm_Related;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.login.Login_Related.Constants;
import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AlarmActivity extends AppCompatActivity {

    RequestQueue rq;
    String content,/*triger_email*email,*/date,name;
    int alarm_no,writing_no;
    SharedPrefManager sharedPrefManager;
    String email_param;

    ListView listViewAlarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //noinspection ConstantConditions
        getSupportActionBar().hide();

        sharedPrefManager = SharedPrefManager.getInstance(getApplicationContext());
        email_param = sharedPrefManager.getUserEmail();
        listViewAlarm = (ListView)findViewById(R.id.listViewAlarm);

        final AlarmItemAdapter adapter = new AlarmItemAdapter();
        rq = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_ALARM_INFO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressDialog.dismiss();
                if (!response.equals("0 results")) {
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
//                            email = obj.getString("email");
                            name = obj.getString("name");
//                            triger_email = obj.getString("triger_email");
                            alarm_no = obj.getInt("alarm_no");
                            writing_no = obj.getInt("writing_no");
                            AlarmItem alarmItem = new AlarmItem(alarm_no, content, date, name, writing_no, sharedPrefManager.getUsername());
                            adapter.addItem(alarmItem);
                            listViewAlarm.setAdapter(adapter);
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
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("email",email_param );
                    return params;
                }
        };
        rq.add(stringRequest);

        Button buttonExit = (Button)findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
