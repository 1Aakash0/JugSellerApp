package com.example.gkjug_seller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class login extends AppCompatActivity {

    TextView t_forgot_password,trp,t_sign_up;
    Button btn;
    EditText e_login_enter_email,e_login_enter_password;
    CheckBox cb_login;

    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    String pageurl = "teacherlogin.php";

    String e,p;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        t_forgot_password = findViewById(R.id.tv_forgot_password);
        trp = findViewById(R.id.rp);
        btn = findViewById(R.id.btn_login);
        e_login_enter_email = findViewById(R.id.edt_login_enter_email);
        e_login_enter_password = findViewById(R.id.edt_login_enter_password);
        cb_login = findViewById(R.id.cb_remember_me);

        t_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(login.this, forgotpassword.class);
                startActivity(intent);*/

                Toast.makeText(login.this, "You Clicked On Forgot Password", Toast.LENGTH_SHORT).show();
            }
        });


/*
        t_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               */
/* Intent intent = new Intent(login.this, register.class);
                intent.putExtra("ustype","seller");
                startActivity(intent);*//*


                Toast.makeText(login.this, "You Clicked On Sign Up", Toast.LENGTH_SHORT).show();

            }
        });
*/


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 e = e_login_enter_email.getText().toString();
                 p = e_login_enter_password.getText().toString();

                if( e.matches(emailPattern) && p.length()>0)
                {
                    //please add proper validation and than add below method


                    LoginTeacher();
                    
                    
                   // Toast.makeText(login.this, "You are successfully login", Toast.LENGTH_SHORT).show();

                  //  Toast.makeText(login.this, "Click Any of These", Toast.LENGTH_LONG).show();

                }
                else
                {
                    e_login_enter_email.setError("Enter valid email ");
                    e_login_enter_password.setError("Enter valid password");
                }

            }
        });


    }

    private void LoginTeacher() {
        ProgressDialog pd = new ProgressDialog(login.this);
        pd.setMessage("Validating......");
        pd.setIndeterminate(true);
        pd.setCancelable(true);
        pd.show();

        RequestParams requestParams = new RequestParams();
        requestParams.put("email",e);
        requestParams.put("password",p);
        requestParams.put("appname","GE");
        asyncHttpClient.get(CommonUtils.myBaseUrl+pageurl,requestParams,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                if (pd.isShowing()){
                    pd.dismiss();


                    try {
                        String mn=response.getString("message");
                        if (mn.equals("Invalid email or password")){
                            Toast.makeText(login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();

                        }else {
                            String mns=response.getString("message");
                            Toast.makeText(login.this, mns, Toast.LENGTH_SHORT).show();
                           /* Intent intent = new Intent(login.this, bottomnavigationbar.class);
                            startActivity(intent);*/
                        }

                    } catch (JSONException ex) {
                        throw new RuntimeException(ex);
                    }




                }

            }
        });


    }
}