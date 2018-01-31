package com.example.acer.login.Profile_Tab;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.acer.login.Profile_Tab.DD119_Related.Tab1;
import com.example.acer.login.Profile_Tab.DD119_Related.Tab2;
import com.example.acer.login.Profile_Tab.DD119_Related.Tab3;
import com.example.acer.login.Profile_Tab.DD119_Related.Tab4;
import com.example.acer.login.Profile_Tab.DD119_Related.Tab5;
import com.example.acer.login.Profile_Tab.DD119_Related.Tab6;
import com.example.acer.login.R;

public class DD119_Fragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_dd119,container,false);

        ImageButton notice_btn = (ImageButton) rootView.findViewById(R.id.woodwheelmap);
        notice_btn.setOnClickListener(new Button.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getActivity().getApplication(),
                                                    Tab1.class);
                                            startActivity(intent);

                                        }
                                    }
        );


        ImageButton accident_btn = (ImageButton) rootView.findViewById(R.id.stonewheelmap);
        accident_btn.setOnClickListener(new Button.OnClickListener() {
                                          public void onClick(View v) {
                                              Intent intent = new Intent(getActivity().getApplication(),
                                                      Tab2.class);
                                              startActivity(intent);

                                          }
                                      }
        );

        ImageButton lost_damage_btn = (ImageButton) rootView.findViewById(R.id.tirewheelmap);
        lost_damage_btn.setOnClickListener(new Button.OnClickListener() {
                                          public void onClick(View v) {
                                              Intent intent = new Intent(getActivity().getApplication(),
                                                      Tab3.class);
                                              startActivity(intent);

                                          }
                                      }
        );

        ImageButton break_handle_btn = (ImageButton) rootView.findViewById(R.id.silverwheelmap);
        break_handle_btn.setOnClickListener(new Button.OnClickListener() {
                                          public void onClick(View v) {
                                              Intent intent = new Intent(getActivity().getApplication(),
                                                      Tab4.class);
                                              startActivity(intent);

                                          }
                                      }
        );

        ImageButton tire_chain_btn = (ImageButton) rootView.findViewById(R.id.tire_chain);
        tire_chain_btn.setOnClickListener(new Button.OnClickListener() {
                                          public void onClick(View v) {
                                              Intent intent = new Intent(getActivity().getApplication(),
                                                      Tab5.class);
                                              startActivity(intent);

                                          }
                                      }
        );


        ImageButton saddle_btn = (ImageButton) rootView.findViewById(R.id.saddle);
        saddle_btn.setOnClickListener(new Button.OnClickListener() {
                                          public void onClick(View v) {
                                              Intent intent = new Intent(getActivity().getApplication(),
                                                      Tab6.class);
                                              startActivity(intent);

                                          }
                                      }
        );

        return rootView;
    }
}
