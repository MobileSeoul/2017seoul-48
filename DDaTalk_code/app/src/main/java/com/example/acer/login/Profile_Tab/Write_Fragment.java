package com.example.acer.login.Profile_Tab;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.example.acer.login.ProfileActivity;
import com.example.acer.login.Profile_Tab.Write_Related.FindSpot_Fragment;
import com.example.acer.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Write_Fragment extends Fragment{

    ViewGroup rootView;
    //생명주기 확인용 태그
    private String TAG = "ActivityLifeCycle";

    //변수 선언
    EditText Content = null;
    TextView spot, gu = null;


    ImageButton x_mark;

    String ContentHolder, Rental_spot_Holder, receive_spot, receive_gu, useremail, userLevel, userExp;

    ProgressDialog progressDialog;

    RequestQueue requestQueue;

    String HttpUrl = "http://104.198.211.126/insertWriting.php";

    // SharedPreferences 선언
    SharedPreferences pref;
    // SharedPreferences 에디터 선언
    SharedPreferences.Editor editor;


    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 로그인한놈 정보 가져오기
        useremail = SharedPrefManager.getInstance(getActivity().getApplicationContext()).getUserEmail();
        receive_level_exp();

        if (getArguments() != null) {
            receive_spot = getArguments().getString("rental_spot");
            receive_gu = getArguments().getString("gu_selected");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_write,container,false);



        //글쓰기 화면으로 오면 키보드 내리기
        InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(rootView.getWindowToken(),0);



        //x버튼 클릭시 이벤트 처리
        x_mark = (ImageButton)rootView.findViewById(R.id.x_Button);

        x_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Content.setText("");
                spot.setText("");
                gu.setText("");
                pref = getActivity().getSharedPreferences("content", MODE_PRIVATE);
                editor = pref.edit();
                editor.remove("content");
                editor.commit();


                Intent i = new Intent(getActivity().getApplication(), ProfileActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });


        //데이터 받아오기

        spot = (TextView)rootView.findViewById(R.id.SpotView);
        gu = (TextView)rootView.findViewById(R.id.guView);

        // Volley 준비 작업

        Content = (EditText)rootView.findViewById(R.id.writing_sec);

        //밑줄 없애기
        Content.setRawInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);




        ImageButton Insert_writing = (ImageButton)rootView.findViewById(R.id.v_Button);

        requestQueue = Volley.newRequestQueue(rootView.getContext());
        progressDialog = new ProgressDialog(rootView.getContext());
        ImageButton search = (ImageButton)rootView.findViewById(R.id.search_btn);




        // 돋보기 버튼 및 밑줄 클릭시 장소찾기 화면 가기

        spot.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment spotFragment = new FindSpot_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.L_container, spotFragment);
                transaction.addToBackStack(null);
                transaction.commit();
//                Intent intent = new Intent(getActivity().getApplication(), FindSpot_Activity.class);
//                startActivity(intent);
                InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });

        gu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment spotFragment = new FindSpot_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.L_container, spotFragment);
                transaction.addToBackStack(null);
                transaction.commit();
//                Intent intent = new Intent(getActivity().getApplication(), FindSpot_Activity.class);
//                startActivity(intent);
                InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });

        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Create new fragment and transaction
               Fragment spotFragment = new FindSpot_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.L_container, spotFragment);
                transaction.addToBackStack(null);
                transaction.commit();
//                Intent intent = new Intent(getActivity().getApplication(), FindSpot_Activity.class);
//                startActivity(intent);
                InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });




        // v_mark 클릭시 이벤트
        Insert_writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // 빈내용 있으면 입력 안받게 설정
                int content_chk, gu_chk, spot_chk = 0;

                content_chk = Content.getText().length();
                gu_chk = gu.getText().length();
                spot_chk = spot.getText().length();

                if(content_chk<=0){
                    Toast.makeText(rootView.getContext(), "내용을 입력해주세요!", Toast.LENGTH_LONG).show();

                }
                else if(gu_chk<=0 || spot_chk<=0){
                    Toast.makeText(rootView.getContext(), "함께타요 장소선택을 해주세요!", Toast.LENGTH_LONG).show();
                }
                else
                {
                // Showing progress dialog at user registration time.
                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                progressDialog.show();

                // Calling method to get value from EditText.
                GetValueFromEditText();

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                progressDialog.dismiss();

                                // Showing response message coming from server.
                                Toast.makeText(rootView.getContext(), "성공적으로 글쓰기 완료!", Toast.LENGTH_LONG).show();

                                //전송다하고 값 초기화
                                Content.setText("");
                                spot.setText("");
                                gu.setText("");
                                pref = getActivity().getSharedPreferences("content", MODE_PRIVATE);
                                editor = pref.edit();
                                editor.remove("content");
                                editor.commit();


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                Toast.makeText(rootView.getContext(), volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        })


                {
                    @Override
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<String, String>();

                        params.put("content", ContentHolder);
                        params.put("rental_spot", Rental_spot_Holder);
                        params.put("email", useremail);
                        params.put("level", userLevel);
                        params.put("exp", userExp);

                        return params;
                    }

                };


                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);


            }
                Intent i = new Intent(getActivity().getApplication(), ProfileActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

        }
    });



        Log.i(TAG, "onCreate()");
        return rootView;
    }



    // 벨류값 뽑아내기 메소드
    public void GetValueFromEditText(){

        ContentHolder = Content.getText().toString().trim();

        Rental_spot_Holder = spot.getText().toString().trim();
    }


    @Override
    public void onStart(){
        super.onStart();


        Log.i(TAG, "onStart()");
    }

    @Override
    public void onStop(){
        super.onStop();


        Log.i(TAG, "onStop()");
    }

    @Override
    public void onResume(){
        super.onResume();

        //지역구,대여소 번들 받아오기
        if(getArguments()!=null) {
            receive_spot = getArguments().getString("rental_spot");
            receive_gu = getArguments().getString("gu_selected");
        }
        spot.setText(receive_spot);
        gu.setText(receive_gu);

        //저장된 shared 부르기
        pref = getActivity().getSharedPreferences("content", MODE_PRIVATE);
        String call = pref.getString("content","");
        Content.setText(call);


        Log.i(TAG, "onResume()");
    }


    @Override
    public void onPause(){
        super.onPause();

        pref = getActivity().getSharedPreferences("content",MODE_PRIVATE);
        editor = pref.edit();
        String str = Content.getText().toString(); // 사용자가 입력한 값


        // Activity 가 종료되기 전에 저장한다
        editor.putString("content", str); // 입력
        editor.commit(); // 파일에 최종 반영함


        Log.i(TAG, "onPause()");


    }

    //디비에서 유저 레벨 경험치 가져오기 메소드
    public void receive_level_exp() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://104.198.211.126/getExp.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    for(int i =0; i<arr.length(); i++){
                        JSONObject data =  arr.getJSONObject(i);
                        userLevel = data.getString("level");
                        userExp = data.getString("exp");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("email", useremail);
                return parameters;
            }
        };
        queue.add(stringRequest);
    }


}

