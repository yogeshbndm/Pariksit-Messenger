package com.pariksit.pariksitmessenger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;
import com.pariksit.pariksitmessenger.ChatFragment.MainUser;

public class LoginActivity extends AppCompatActivity {
    EditText userId, userPass;
    AppCompatButton login;
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson = new Gson();
    String uid, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences(Utils.PREFS_NAME, 0);
        prefsEditor = prefs.edit();

        userId = (EditText) findViewById(R.id.userid);
        userPass = (EditText) findViewById(R.id.userpass);
        login = findViewById(R.id.clogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = userId.getText().toString();
                pass = userPass.getText().toString();
                Log.e("userid",uid);
                Log.e("userpass",pass);
                if(uid.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please Enter Correct Credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    checkLogin();
                }
            }
        });

    }

    private void checkLogin() {

        AndroidNetworking.post(Utils.loginUrl)
                .addBodyParameter("uid", uid) // posting java object
                .addBodyParameter("pass", pass)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(MainUser.class, new ParsedRequestListener<MainUser>() {
                    @Override
                    public void onResponse(MainUser mainUser) {
                        // do anything with response
                        if("".equals(mainUser.getId())) {
                            //uid null, means login failed
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            saveDataInPrefs(mainUser);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("tag", error.toString());
                    }
                });
    }

    private void saveDataInPrefs(MainUser mainUser) {
        prefsEditor.putString("mainUser", mainUser.toString());
        prefsEditor.commit();
        moveToMainPage();
    }

    private void moveToMainPage() {
        Intent intent = new Intent(LoginActivity.this , MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        moveTaskToBack(true);
    }
}
