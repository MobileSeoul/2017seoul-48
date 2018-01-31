package com.example.acer.login.Profile_Tab;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.login.Login_Related.Constants;
import com.example.acer.login.Profile_Tab.Home_Related.TogetherItem;
import com.example.acer.login.Profile_Tab.Home_Related.TogetherItemAdapter;
import com.example.acer.login.Profile_Tab.Home_Related.Writing;
import com.example.acer.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Home_Fragment extends Fragment {

    int itemcount;
    RequestQueue rq;
    String content, date, email,rental_spot,imgPath;
    int reply_cnt, with_cnt, writing_no;

    int get_writing_no, get_reply_cnt;

    EditText editTextSearch;
    ProgressDialog progressDialog;

    //real time
    Handler handler;
    Timer timerMTimer;

    boolean lastitemVisibleFlag;

    ArrayList<TogetherItem> ti;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        itemcount =5;
        ti = new ArrayList<TogetherItem>();

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity().getApplication(), "Refresh success", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        final TogetherItemAdapter adapter = new TogetherItemAdapter();
        final ListView together2 = (ListView) rootView.findViewById(R.id.together2);
        progressDialog = new ProgressDialog(rootView.getContext());
        rq = Volley.newRequestQueue(getActivity());

    //    ReceiveImg();

        progressDialog.setMessage("로딩중.. 좀만 기둘려주떼염");
        progressDialog.show();
        handler = new Handler();
        timerMTimer = new Timer(true);
        timerMTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        final JsonArrayRequest jsonArrayRequest;
                        jsonArrayRequest = new JsonArrayRequest(Constants.URL_WRITING_INFO, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                progressDialog.dismiss();
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject obj = response.getJSONObject(i);
                                        content = obj.getString("content");
                                        date = obj.getString("date");
                                        email = obj.getString("email");
                                        reply_cnt = obj.getInt("reply_cnt");
                                        with_cnt = obj.getInt("with_cnt");
                                        writing_no = obj.getInt("writing_no");
                                        rental_spot = obj.getString("rental_spot");
                                        imgPath = obj.getString("userimg");
                                        byte [] encodeByte= Base64.decode(imgPath,Base64.DEFAULT);

                                        InputStream inputStream  = new ByteArrayInputStream(encodeByte);
                                        Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
                                        Writing w = new Writing(content, reply_cnt, with_cnt, date, writing_no, email,rental_spot);


                                        TogetherItem togetherItem = new TogetherItem(w.getWriting_no(), w.getEmail(), w.getContent(), w.getDate(), bitmap,
                                                w.getWith_cnt(), w.getReply_cnt(), w.getRental_spot());
                                        ti.add(togetherItem);
//                                        adapter.addItem(togetherItem);
//                                        together2.setAdapter(adapter);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                for (int i =0; i<5; i++){
                                    adapter.addItem(ti.get(i));
                                    together2.setAdapter(adapter);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        rq.add(jsonArrayRequest);
                    }
                });
            }
        },0,60000*100);


        Bundle extra = getArguments();
        if(extra != null) {
            get_writing_no = extra.getInt("writing_no");
            get_reply_cnt = extra.getInt("reply_cnt");
            adapter.replaceItem(get_writing_no,get_reply_cnt);
        }

        lastitemVisibleFlag = false;

        together2.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if(i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastitemVisibleFlag) {
                    if(itemcount>=ti.size()){
                        Toast.makeText(getActivity().getApplication(),"지금 보고 있는 글이 마지막 글이에요!!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity().getApplication(), "스크롤을 끝까지 내렸네요!", Toast.LENGTH_SHORT).show();
                    }
                    /*progressDialog.setMessage("로딩중.. 좀만 기둘려주떼염");
                    progressDialog.show();
                    final JsonArrayRequest jsonArrayRequest2;
                    jsonArrayRequest2 = new JsonArrayRequest(Constants.URL_WRITING_INFO, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            progressDialog.dismiss();
                            int length = itemcount+5;
                            Log.d("item_count_!!!",String.valueOf(itemcount));
                            for (int i = itemcount; i < length; i++) {

                                Log.d("=========itemcount=====",String.valueOf(itemcount));
                                Log.d("========ength=========",String.valueOf(length));

                                if(i>=response.length()){
                                    break;
                                }
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    content = obj.getString("content");
                                    date = obj.getString("date");
                                    email = obj.getString("email");
                                    reply_cnt = obj.getInt("reply_cnt");
                                    with_cnt = obj.getInt("with_cnt");
                                    writing_no = obj.getInt("writing_no");
                                    rental_spot = obj.getString("rental_spot");
                                    imgPath = obj.getString("userimg");
                                    byte [] encodeByte= Base64.decode(imgPath,Base64.DEFAULT);

                                    InputStream inputStream  = new ByteArrayInputStream(encodeByte);
                                    Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
                                    Writing w = new Writing(content, reply_cnt, with_cnt, date, writing_no, email,rental_spot);


                                    TogetherItem togetherItem = new TogetherItem(w.getWriting_no(), w.getEmail(), w.getContent(), w.getDate(), bitmap,
                                            w.getWith_cnt(), w.getReply_cnt(), w.getRental_spot());

                                    adapter.addItem(togetherItem);
                                    adapter.notifyDataSetChanged();

                                    Toast.makeText(getActivity().getApplication(),String.valueOf(i),Toast.LENGTH_LONG).show();
//                                    together2.setAdapter(adapter);
                                } catch (JSONException e) {
                                    Toast.makeText(getActivity().getApplication(),e.getMessage(),Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }
                            }
                            if (itemcount<response.length()){
                                itemcount+=5;
                            }
                            Toast.makeText(getActivity().getApplication(),String.valueOf(adapter.getCount()),Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplication(),error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                    rq.add(jsonArrayRequest2);
                    progressDialog.dismiss();*/
                    int length = itemcount+5;
                    for(int j = itemcount; j<length; j++){
                        Log.d("=========itemcount=====",String.valueOf(itemcount));
                        Log.d("========ength=========",String.valueOf(length));
                        if(j>=ti.size()){
                            break;
                        }
                        adapter.addItem(ti.get(j));
                        adapter.notifyDataSetChanged();
                    }
                    if (itemcount<ti.size()){
                        itemcount+=5;
                    }
                    lastitemVisibleFlag = false;
                    Log.d("i_t_e_m_c_o_u_n_t",String.valueOf(itemcount));
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                lastitemVisibleFlag = (i2 > 0) && (i + i1 >= i2);
            }
        });

        editTextSearch = (EditText)rootView.findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editTextSearch.getText().toString().trim();
                adapter.filter(text);
            }
        });


        return rootView;
    }





}
