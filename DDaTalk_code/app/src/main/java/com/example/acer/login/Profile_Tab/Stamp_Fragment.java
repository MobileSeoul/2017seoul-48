package com.example.acer.login.Profile_Tab;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.example.acer.login.Profile_Tab.Stamp_Related.Stamp_CollectActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Stamp_Fragment extends Fragment {
    TextView showLevel;
    ProgressBar progressBar;
    RequestQueue requestQueue;

    ImageView wheel;
    ImageView levelname;
    ImageView levelbar;
    ImageButton stampcollect;

    String L,E,showExp;
    int maxExp,Ll,Ee ;


    ProgressDialog progressDialog;

    public void setProgressBarColor(ProgressBar progressBar, int newColor){
        LayerDrawable ld = (LayerDrawable) progressBar.getProgressDrawable();
        ClipDrawable d1 = (ClipDrawable) ld.findDrawableByLayerId(R.id.progressshape);
        d1.setColorFilter(newColor, PorterDuff.Mode.SRC_IN);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stamp, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        showLevel = (TextView) rootView.findViewById(R.id.showLevel);
        wheel = (ImageView) rootView.findViewById(R.id.wheel);
        levelname = (ImageView) rootView.findViewById(R.id.levelname);
        levelbar = (ImageView) rootView.findViewById(R.id.levelbar);
        final SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(rootView.getContext());

        progressDialog = new ProgressDialog(rootView.getContext());
// 서브스탬프로 들어가기
        stampcollect = (ImageButton) rootView.findViewById(R.id.stampcollect);
        stampcollect.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Create new fragment and transaction
                /*Fragment anyFragment = new Stamp_Fragment_sub();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, anyFragment);
                transaction.addToBackStack(null);

                transaction.commit();*/
                Intent intent = new Intent(getActivity().getApplication(), Stamp_CollectActivity.class);
                startActivity(intent);

            }
        });

        progressDialog.setMessage("로딩중.. 좀만 기둘려주떼염");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(getActivity().getApplication());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://104.198.211.126/getExp.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if(!response.equals("0 results")){
                    Toast.makeText(getActivity().getApplication(),"데이터 가져오기 성공",Toast.LENGTH_LONG).show();


                    try {
                        JSONArray arr = new JSONArray(response);
                        for(int i =0; i<arr.length(); i++){
                            JSONObject obj =  arr.getJSONObject(i);
                            L=obj.getString("level");
                            E=obj.getString("exp");
                            Ee = Integer.parseInt(E);
                            Ll = Integer.parseInt(L);
                            maxExp = 100;
                            switch (Ll){
                                case 2:
                                    maxExp = 340;
                                    break;
                                case 3:
                                    maxExp = 640;
                                    break;
                                case 4:
                                    maxExp = 1000;
                                    break;
                                case 5:
                                    maxExp = 1420;
                                    break;
                                case 6:
                                    maxExp = 0;
                            }

                            showExp = String.valueOf(maxExp - Ee);
                            progressBar.setMax(maxExp);
                            progressBar.setProgress(Ee);

                            switch (Ll){

                                case 1:
                                    wheel.setImageResource(R.drawable.woodwheel);
                                    levelname.setImageResource(R.drawable.woodname);
                                    levelbar.setImageResource(R.drawable.woodbar);
                                    if(Ee > maxExp * 0.0 && Ee <= maxExp * 0.33) {
                                        setProgressBarColor(progressBar, Color.parseColor("#edb043"));
                                    } else if(Ee > maxExp*0.33 && Ee <= maxExp * 0.66) {
                                        setProgressBarColor(progressBar, Color.parseColor("#af740b"));
                                    } else if(Ee > maxExp*0.66 && Ee <= maxExp * 1.0) {
                                        setProgressBarColor(progressBar, Color.parseColor("#7a520a"));
                                    }
                                    showLevel.setText("현재레벨은  " + L +"이며, " + "다음레벨까지 " + (showExp) +"남았습니다.");
                                    break;
                                case 2:
                                    wheel.setImageResource(R.drawable.stonewheel);
                                    levelname.setImageResource(R.drawable.stonename);
                                    levelbar.setImageResource(R.drawable.stonebar);
                                    if(Ee > maxExp * 0.0 && Ee <=maxExp * 0.33) {
                                        setProgressBarColor(progressBar, Color.parseColor("#e5e5e5"));
                                    } else if(Ee > maxExp*0.33 && Ee <= maxExp * 0.66) {
                                        setProgressBarColor(progressBar, Color.parseColor("#bababa"));
                                    } else if(Ee > maxExp*0.66 && Ee <= maxExp * 1.0) {
                                        setProgressBarColor(progressBar, Color.parseColor("#7c7c7c"));
                                    }
                                    showLevel.setText("현재레벨은  " + L +"이며, " + "다음레벨까지 " + (showExp) +"남았습니다.");
                                    break;
                                case 3:
                                    wheel.setImageResource(R.drawable.tirewheel);
                                    levelname.setImageResource(R.drawable.tirename);
                                    levelbar.setImageResource(R.drawable.tirebar);
                                    if(Ee > maxExp * 0.0 && Ee <= maxExp * 0.33) {
                                        setProgressBarColor(progressBar, Color.parseColor("#718aa8"));
                                    } else if(Ee > maxExp*0.33 && Ee <= maxExp * 0.66) {
                                        setProgressBarColor(progressBar, Color.parseColor("#47557f"));
                                    } else if(Ee > maxExp*0.66 && Ee <= maxExp * 1.0) {
                                        setProgressBarColor(progressBar, Color.parseColor("#1f2a49"));
                                    }
                                    showLevel.setText("현재레벨은  " + L +"이며, " + "다음레벨까지 " + (showExp) +"남았습니다.");
                                    break;
                                case 4:
                                    wheel.setImageResource(R.drawable.silverwheel);
                                    levelname.setImageResource(R.drawable.silvername);
                                    levelbar.setImageResource(R.drawable.silverbar);
                                    if(Ee > maxExp * 0.0 && Ee <= maxExp * 0.33) {
                                        setProgressBarColor(progressBar, Color.parseColor("#e5e5e5"));
                                    } else if(Ee > maxExp*0.33 && Ee <= maxExp * 0.66) {
                                        setProgressBarColor(progressBar, Color.parseColor("#99999"));
                                    } else if(Ee > maxExp*0.66 && Ee <= maxExp * 1.0) {
                                        setProgressBarColor(progressBar, Color.parseColor("#666666"));
                                    }
                                    showLevel.setText("현재레벨은  " + L +"이며, " + "다음레벨까지 " + (showExp) +"남았습니다.");
                                    break;
                                case 5:
                                    wheel.setImageResource(R.drawable.goldwheel);
                                    levelname.setImageResource(R.drawable.goldname);
                                    levelbar.setImageResource(R.drawable.goldbar);
                                    if(Ee > maxExp * 0.0 && Ee <= maxExp * 0.33) {
                                        setProgressBarColor(progressBar, Color.parseColor("#ffce00"));
                                    } else if(Ee > maxExp*0.33 && Ee <= maxExp * 0.66) {
                                        setProgressBarColor(progressBar, Color.parseColor("#f99f00"));
                                    } else if(Ee > maxExp*0.66 && Ee <= maxExp * 1.0) {
                                        setProgressBarColor(progressBar, Color.parseColor("#f97600"));
                                    }
                                    showLevel.setText("현재레벨은  " + L +"이며, " + "다음레벨까지 " + (showExp) +"남았습니다.");
                                    break;
                                case 6:
                                    wheel.setImageResource(R.drawable.diamondwheel);
                                    levelname.setImageResource(R.drawable.diamondname);
                                    levelbar.setImageResource(R.drawable.diamondbar);
                                    if(Ee > maxExp * 0.0 && Ee <= maxExp * 0.33) {
                                        setProgressBarColor(progressBar, Color.parseColor("#e6f0fc"));
                                    } else if(Ee > maxExp*0.33 && Ee <= maxExp * 0.66) {
                                        setProgressBarColor(progressBar, Color.parseColor("#b4cbf2"));
                                    } else if(Ee > maxExp*0.66 && Ee <= maxExp * 1.0) {
                                        setProgressBarColor(progressBar, Color.parseColor("#8da8e2"));
                                    }
                                    showLevel.setText("현재레벨은  " + L +"이며, " + "다음레벨까지 " + (showExp) +"남았습니다.");
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
                Toast.makeText(getActivity().getApplication(),error.getMessage(),Toast.LENGTH_LONG).show();
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

        return rootView;
    }


    private void updateLevelUp(final SharedPrefManager sharedPrefManager, final Context context){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://104.198.211.126/updateLevelUp.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(response);
                    if(!obj.getBoolean("error")){
                        Toast.makeText(context,obj.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
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