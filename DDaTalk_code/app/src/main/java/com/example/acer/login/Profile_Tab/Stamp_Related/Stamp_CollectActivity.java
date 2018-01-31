package com.example.acer.login.Profile_Tab.Stamp_Related;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Stamp_CollectActivity extends AppCompatActivity {
    ImageView woodwheelmap;
    ImageView stonewheelmap;
    ImageView tirewheelmap;
    ImageView silverwheelmap;
    ImageView goldwheelmap;
    ImageView diamondwheelmap;
    ImageButton imageButtonExit;
    ArrayList<Drawable> wheelmapList = new ArrayList<Drawable>();

    RequestQueue requestQueue;
    String L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp__collect);

        getSupportActionBar().hide();


        final SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(getApplicationContext());



        imageButtonExit = (ImageButton)findViewById(R.id.imageButtonExit);
        imageButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //로그인한 유저레벨 가져오기
        L = SharedPrefManager.getInstance(getApplicationContext()).getUserLevel();


        Resources res = getResources();
        wheelmapList.add(res.getDrawable(R.drawable.woodwheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.stonewheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.tirewheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.silverwheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.goldwheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.diamondwheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.needlevelup));


        //뷰객체 보여주
        woodwheelmap = (ImageView) findViewById(R.id.woodwheelmap);
        stonewheelmap = (ImageView) findViewById(R.id.stonewheelmap);
        tirewheelmap = (ImageView) findViewById(R.id.tirewheelmap);
        silverwheelmap = (ImageView) findViewById(R.id.silverwheelmap);
        goldwheelmap = (ImageView) findViewById(R.id.goldwheelmap);
        diamondwheelmap = (ImageView) findViewById(R.id.diamondwheelmap);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://104.198.211.126/getExp.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.equals("0 results")){
                    Toast.makeText(getApplicationContext(),"데이터 가져오기 성공",Toast.LENGTH_LONG).show();


                    try {
                        JSONArray arr = new JSONArray(response);
                        for(int i =0; i<arr.length(); i++){
                            JSONObject obj =  arr.getJSONObject(i);
                            L=obj.getString("level");
                            int Ll = Integer.parseInt(L);

                            switch (Ll) {
                                case 1:
                                    woodwheelmap.setImageDrawable(wheelmapList.get(0));
                                    stonewheelmap.setImageDrawable(wheelmapList.get(6));
                                    tirewheelmap.setImageDrawable(wheelmapList.get(6));
                                    silverwheelmap.setImageDrawable(wheelmapList.get(6));
                                    goldwheelmap.setImageDrawable(wheelmapList.get(6));
                                    diamondwheelmap.setImageDrawable(wheelmapList.get(6));
                                    break;
                                case 2:
                                    woodwheelmap.setImageDrawable(wheelmapList.get(0));
                                    stonewheelmap.setImageDrawable(wheelmapList.get(1));
                                    tirewheelmap.setImageDrawable(wheelmapList.get(6));
                                    silverwheelmap.setImageDrawable(wheelmapList.get(6));
                                    goldwheelmap.setImageDrawable(wheelmapList.get(6));
                                    diamondwheelmap.setImageDrawable(wheelmapList.get(6));
                                    break;
                                case 3:
                                    woodwheelmap.setImageDrawable(wheelmapList.get(0));
                                    stonewheelmap.setImageDrawable(wheelmapList.get(1));
                                    tirewheelmap.setImageDrawable(wheelmapList.get(2));
                                    silverwheelmap.setImageDrawable(wheelmapList.get(6));
                                    goldwheelmap.setImageDrawable(wheelmapList.get(6));
                                    diamondwheelmap.setImageDrawable(wheelmapList.get(6));
                                    break;
                                case 4:
                                    woodwheelmap.setImageDrawable(wheelmapList.get(0));
                                    stonewheelmap.setImageDrawable(wheelmapList.get(1));
                                    tirewheelmap.setImageDrawable(wheelmapList.get(2));
                                    silverwheelmap.setImageDrawable(wheelmapList.get(3));
                                    goldwheelmap.setImageDrawable(wheelmapList.get(6));
                                    diamondwheelmap.setImageDrawable(wheelmapList.get(6));
                                    break;
                                case 5:
                                    woodwheelmap.setImageDrawable(wheelmapList.get(0));
                                    stonewheelmap.setImageDrawable(wheelmapList.get(1));
                                    tirewheelmap.setImageDrawable(wheelmapList.get(2));
                                    silverwheelmap.setImageDrawable(wheelmapList.get(3));
                                    goldwheelmap.setImageDrawable(wheelmapList.get(4));
                                    diamondwheelmap.setImageDrawable(wheelmapList.get(6));
                                    break;
                                case 6:
                                    woodwheelmap.setImageDrawable(wheelmapList.get(0));
                                    stonewheelmap.setImageDrawable(wheelmapList.get(1));
                                    tirewheelmap.setImageDrawable(wheelmapList.get(2));
                                    silverwheelmap.setImageDrawable(wheelmapList.get(3));
                                    goldwheelmap.setImageDrawable(wheelmapList.get(4));
                                    diamondwheelmap.setImageDrawable(wheelmapList.get(5));
                                    break;

                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", sharedPrefManager.getUserEmail());

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
}
