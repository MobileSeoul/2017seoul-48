package com.example.acer.login.Profile_Tab.Write_Related;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.acer.login.Profile_Tab.Write_Fragment;
import com.example.acer.login.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;


public class FindSpot_Fragment extends Fragment {

    ViewGroup rootView;
    Spinner spinner;
    ListView listview;
    ArrayList<String> list;
    ArrayAdapter listadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_findspot,container,false);

        list = new ArrayList<String>();
        listadapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
        listview = (ListView) rootView.findViewById(R.id.listview1);
        listview.setAdapter(listadapter);

        //지역구 선택 스피너
        spinner = (Spinner) rootView.findViewById(R.id.gu_select);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.gu_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        //스피너 크기조절
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);
            popupWindow.setHeight(800);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            //fail
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_item = spinner.getSelectedItem().toString();
                Resources res = getResources();

                switch (selected_item){
                    case "종로구":
                        String[] jongro = res.getStringArray(R.array.jongro);
                        list.clear();
                        Collections.addAll(list, jongro);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "광진구":
                        String[] gwangjin = res.getStringArray(R.array.gwangjin);
                        list.clear();
                        Collections.addAll(list, gwangjin);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "동작구":
                        String[] dongjak = res.getStringArray(R.array.dongjak);
                        list.clear();
                        Collections.addAll(list, dongjak);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "도봉구":
                        String[] dobong = res.getStringArray(R.array.dobong);
                        list.clear();
                        Collections.addAll(list, dobong);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "양천구":
                        String[] yangcheon = res.getStringArray(R.array.yangcheon);
                        list.clear();
                        Collections.addAll(list, yangcheon);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "서대문구":
                        String[] seodaemoon = res.getStringArray(R.array.seodaemoon);
                        list.clear();
                        Collections.addAll(list, seodaemoon);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "은평구":
                        String[] ep = res.getStringArray(R.array.ep);
                        list.clear();
                        Collections.addAll(list, ep);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "동대문구":
                        String[] dongdaemoon = res.getStringArray(R.array.dongdaemoon);
                        list.clear();
                        Collections.addAll(list, dongdaemoon);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "중랑구":
                        String[] jungnang = res.getStringArray(R.array.jungnang);
                        list.clear();
                        Collections.addAll(list, jungnang);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "마포구":
                        String[] mapo = res.getStringArray(R.array.mapo);
                        list.clear();
                        Collections.addAll(list, mapo);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "강남구":
                        String[] gangnam = res.getStringArray(R.array.gangnam);
                        list.clear();
                        Collections.addAll(list, gangnam);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "강북구":
                        String[] gangbuk = res.getStringArray(R.array.gangbuk);
                        list.clear();
                        Collections.addAll(list, gangbuk);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "강서구":
                        String[] gangseo = res.getStringArray(R.array.gangseo);
                        list.clear();
                        Collections.addAll(list, gangseo);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "강동구":
                        String[] gangdong = res.getStringArray(R.array.gangdong);
                        list.clear();
                        Collections.addAll(list, gangdong);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "구로구":
                        String[] guro = res.getStringArray(R.array.guro);
                        list.clear();
                        Collections.addAll(list, guro);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "관악구":
                        String[] gwanak = res.getStringArray(R.array.gwanak);
                        list.clear();
                        Collections.addAll(list, gwanak);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "금천구":
                        String[] geumcheon = res.getStringArray(R.array.geumcheon);
                        list.clear();
                        Collections.addAll(list, geumcheon);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "영등포구":
                        String[] ydp = res.getStringArray(R.array.ydp);
                        list.clear();
                        Collections.addAll(list, ydp);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "용산구":
                        String[] yongsan = res.getStringArray(R.array.yongsan);
                        list.clear();
                        Collections.addAll(list, yongsan);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "성동구":
                        String[] sungdong = res.getStringArray(R.array.sungdong);
                        list.clear();
                        Collections.addAll(list, sungdong);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "성북구":
                        String[] sungbuk = res.getStringArray(R.array.sungbuk);
                        list.clear();
                        Collections.addAll(list, sungbuk);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "중구":
                        String[] junggu = res.getStringArray(R.array.junggu);
                        list.clear();
                        Collections.addAll(list, junggu);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "노원구":
                        String[] nowon = res.getStringArray(R.array.nowon);
                        list.clear();
                        Collections.addAll(list, nowon);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "서초구":
                        String[] seocho = res.getStringArray(R.array.seocho);
                        list.clear();
                        Collections.addAll(list, seocho);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "송파구":
                        String[] songpa = res.getStringArray(R.array.songpa);
                        list.clear();
                        Collections.addAll(list, songpa);
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    case "구를 입력하세요":
                        list.clear();
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;

                    default:
                        list.clear();
                        listadapter.notifyDataSetChanged();
                        listview.setAdapter(listadapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//대여소 목록 검색 필터
        EditText editTextFilter = (EditText) rootView.findViewById(R.id.editTextFilter);
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString();
                if (filterText.length() > 0) {
                    ((ArrayAdapter) listview.getAdapter()).getFilter().filter(filterText);
                } else {
                    listview.clearTextFilter();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        //클릭된아이템 데이터넘기기
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("rental_spot", parent.getAdapter().getItem(position).toString());
                bundle.putString("gu_selected", spinner.getSelectedItem().toString());
                Fragment send = new Write_Fragment();
                send.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, send);
//                transaction.addToBackStack(null);
                transaction.disallowAddToBackStack();
                transaction.commit();
                getActivity().onBackPressed();


            }
        });
        //클릭된 아이템 데이터넘기기

return rootView;
    }

}
