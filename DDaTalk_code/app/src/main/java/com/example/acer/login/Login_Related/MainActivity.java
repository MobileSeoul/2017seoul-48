package com.example.acer.login.Login_Related;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.acer.login.ProfileActivity;
import com.example.acer.login.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername, editTextEmail, editTextBirth,editTextPassword, editTextPasswordCheck;
    private Button buttonRegister;

    public void finishToLogin(){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        intent.putExtra("register",1);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
            return;
        }
        //noinspection ConstantConditions
        getSupportActionBar().hide();


        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPasswordCheck = (EditText)findViewById(R.id.editTextPasswordCheck);
        editTextBirth =(EditText)findViewById(R.id.editTextBirth);



        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);

    }

    private void registerUser() {
        final String name = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String birthday = editTextBirth.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressDialog.dismiss();
                        Log.d("메세지",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            finishToLogin();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                params.put("name",name);
                params.put("birthday",birthday);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegister) {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String password_check = editTextPasswordCheck.getText().toString().trim();
            String name = editTextUsername.getText().toString().trim();
            String birth = editTextBirth.getText().toString().trim();
            if(email.equals("")){
                Toast.makeText(getApplicationContext(),"이메일을 입력해주세요!!",Toast.LENGTH_LONG).show();
            }else {
                if(email.matches("^[_a-zA-Z0-9-.]+@[.a-zA-Z0-9-]+\\.[a-zA-Z]+$")){
                    if (password.equals("")) {
                        Toast.makeText(getApplicationContext(),"비밀번호를 입력해주세요!!",Toast.LENGTH_LONG).show();
                    }else {
                        if (password.equals(password_check)) {
                            if(name.equals("")){
                                Toast.makeText(getApplicationContext(),"이름을 입력해주세요!!",Toast.LENGTH_LONG).show();
                            }else {
                                if(birth.equals("")){
                                    Toast.makeText(getApplicationContext(),"생일을 입력해주세요!!",Toast.LENGTH_LONG).show();
                                }else {
                                    if(birth.matches("(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])" )) {
                                        registerUser();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"생일을 형식에 맞춰서 입력해주세요!!",Toast.LENGTH_LONG).show();
                                    }

                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"email 형식에 맞게 입력하셨나요??",Toast.LENGTH_LONG).show();
                }
            }


        }
    }
}
