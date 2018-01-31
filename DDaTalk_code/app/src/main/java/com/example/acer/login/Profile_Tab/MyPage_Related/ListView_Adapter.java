package com.example.acer.login.Profile_Tab.MyPage_Related;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.login.R;

import java.util.ArrayList;

public class ListView_Adapter extends BaseAdapter {

    // Activity에서 가져온 객체정보를 저장할 변수
    private List_writing mWriting;
    private Context mContext;

    // ListView 내부 View들을 가르킬 변수들
    private ImageView imgWithIcon;
    private TextView tvContent;
    private TextView tvWith_cnt;
    private TextView tvDate;

    // 리스트 아이템 데이터를 저장할 배열
    private ArrayList<List_writing> mWritingData;

    public ListView_Adapter(Context context) {
        super();
        mContext = context;
        mWritingData = new ArrayList<List_writing>();
    }

    @Override
    /**
     * @return 아이템의 총 개수를 반환
     */
    public int getCount() {
        // TODO Auto-generated method stub
        return mWritingData.size();
    }

    @Override
    /**
     * @return 선택된 아이템을 반환
     */
    public List_writing getItem(int position) {
        // TODO Auto-generated method stub
        return mWritingData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    /**
     * getView
     *
     * @param position - 현재 몇 번째로 아이템이 추가되고 있는지 정보를 갖고 있다.
     * @param convertView - 현재 사용되고 있는 어떤 레이아웃을 가지고 있는지 정보를 갖고 있다.
     * @param parent - 현재 뷰의 부모를 지칭하지만 특별히 사용되지는 않는다.
     * @return 리스트 아이템이 저장된 convertView
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;

        // 리스트 아이템이 새로 추가될 경우에는 v가 null값이다.
        // view는 어느 정도 생성된 뒤에는 재사용이 일어나기 때문에 효율을 위해서 해준다.
        if (v == null) {
            // inflater를 이용하여 사용할 레이아웃을 가져옵니다.
            v = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_items, null);

            // 레이아웃이 메모리에 올라왔기 때문에 이를 이용하여 포함된 뷰들을 참조할 수 있습니다.
            imgWithIcon = (ImageView) v.findViewById(R.id.imageView5);
            tvContent = (TextView) v.findViewById(R.id.textViewContent);
            tvWith_cnt = (TextView) v.findViewById(R.id.textViewWith_cnt);
            tvDate = (TextView) v.findViewById(R.id.textViewDate);
        }

        // 받아온 position 값을 이용하여 배열에서 아이템을 가져온다.
        mWriting = getItem(position);

        // Tag를 이용하여 데이터와 뷰를 묶습니다.
        //btnSend.setTag(mWriting);

        // 데이터의 실존 여부를 판별합니다.
        if (mWriting != null) {
            // 데이터가 있다면 갖고 있는 정보를 뷰에 알맞게 배치시킵니다.
            if (mWriting.getwithicon() != null) {
                imgWithIcon.setImageDrawable(mWriting.getwithicon());
            }
            tvContent.setText(mWriting.getcontent());
            tvWith_cnt.setText(mWriting.getwith_cnt());
            tvDate.setText(mWriting.getdate());
        }
        // 완성된 아이템 뷰를 반환합니다.
        return v;
    }

    // 데이터를 추가하는 것을 위해서 만들어 준다.
    public void add(List_writing user) {
        mWritingData.add(user);
    }

}