package com.example.acer.login.Login_Related;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private ProgressDialog progressDialog;

    private TextView textViewJoin;

    @Override
    protected void onResume() {
        super.onResume();

       Intent intent =getIntent();
        if(intent!=null){
            if(intent.getIntExtra("register",0)==1){
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.setMessage("이메일을 인증해주세요!");
                alert.show();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
            return;
        }
        //noinspection ConstantConditions
        getSupportActionBar().hide();


        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword =(EditText)findViewById(R.id.editTextPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);

        textViewJoin = (TextView)findViewById(R.id.textViewJoin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        buttonLogin.setOnClickListener(this);
        textViewJoin.setOnClickListener(this);
    }

    private void userLogin(){
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                if(obj.getString("Verify").equals("Y")) {
                                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                            obj.getString("email"),
                                            obj.getString("name"),
                                            obj.getString("birthday"),
                                            obj.getString("exp"),
                                            obj.getString("level"),
                                            obj.getString("Verify"),
                                            obj.getString("userimg")
                                    );



                                    Toast.makeText(getApplicationContext(),
                                            "User login successful",
                                            Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(),"이메일을 인증 받으세요!",Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogin){
            if (editTextEmail.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(),"email 을 입력하세요!",Toast.LENGTH_LONG).show();
            }else{
                if(editTextEmail.getText().toString().matches("^[_a-zA-Z0-9-.]+@[.a-zA-Z0-9-]+\\.[a-zA-Z]+$")){
                    if(editTextPassword.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"비밀번호를 입력하세요!",Toast.LENGTH_LONG).show();
                    }else{
                        userLogin();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"email 형식이 이상합니다!",Toast.LENGTH_LONG).show();
                }


            }

        }
        if( v == textViewJoin){
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}