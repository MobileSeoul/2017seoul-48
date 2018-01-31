package com.example.acer.login.Profile_Tab.Home_reply;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import com.tubb.smrv.SwipeHorizontalMenuLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ReplyItemAdapter extends BaseAdapter {
    private ArrayList<ReplyItem> items = new ArrayList<>();
    private static final int VIEW_TYPE_ENABLE = 0;
    private static final int VIEW_TYPE_DISABLE = 1;
    private RequestQueue rq;


    @Override
    public int getCount() {
        return items.size();
    }
    void addItem(ReplyItem item){
        items.add(item);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        final ViewHolder viewHolder;
        rq = Volley.newRequestQueue(context);
        ReplyItemView view;
        if(convertView == null){
            view = new ReplyItemView(context);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.together_item, parent, false);
        }else{
            view = (ReplyItemView) convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        final ReplyItem item = items.get(position);
        viewHolder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // must close normal
                if(SharedPrefManager.getInstance(context).getUserEmail().equals(item.getEmail())) {
                    deleteReplyByEmailAndWriting_No(item.getReply_no(),item.getWriting_no(),context);
                    viewHolder.sml.smoothCloseMenu();
                    items.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
        view.setName(item.getEmail());
        view.setContent(item.getContent());
//        String time = item.getReply_time().substring(0,9);
        view.setTime(item.getReply_time());
        boolean swipeEnable = swipeEnableByViewType(getItemViewType(position));
        viewHolder.sml.setSwipeEnable(swipeEnable);
        return view;
    }
    private boolean swipeEnableByViewType(int viewType) {
        return viewType == VIEW_TYPE_ENABLE || viewType != VIEW_TYPE_DISABLE;
    }
    private class ViewHolder{
        View btOpen;
        View btDelete;
        SwipeHorizontalMenuLayout sml;
        ViewHolder(View itemView) {
            btOpen = itemView.findViewById(R.id.btOpen);
            btDelete = itemView.findViewById(R.id.btDelete);
            sml = (SwipeHorizontalMenuLayout) itemView.findViewById(R.id.sml);

        }
    }

    private void deleteReplyByEmailAndWriting_No(final int reply_no, final int writing_no, final Context context){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REPLY_DELETE_BY_REPLYNO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error")){
                        Toast.makeText(context,
                                "댓글이 성공적으로 삭제되었습니다.",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,
                        error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("reply_no", String.valueOf(reply_no));
                params.put("writing_no",String.valueOf(writing_no));
                return params;
            }
        };
        rq.add(stringRequest);
    }
}