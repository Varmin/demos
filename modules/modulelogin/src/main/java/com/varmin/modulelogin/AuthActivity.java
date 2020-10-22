package com.varmin.modulelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.atoshi.opensdk.atoshi.AuthResp;
import com.atoshi.opensdk.atoshi.IAuthResp;
import com.atoshi.opensdk.client.AuthReq;

public class AuthActivity extends AppCompatActivity implements IAuthResp {
    private static final String TAG = "AuthActivity";
    private AuthResp authResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authResp = new AuthResp(this);
        Log.d(TAG, "onCreate: ");
        authResp.parseExtra();

        findViewById(R.id.btnAuth).setOnClickListener(v -> {
            getCode(authResp.getClientID());
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Log.d(TAG, "onNewIntent: ");
        authResp.parseExtra();
    }

    @Override
    public void getCode(String clientId) {
        new Handler().postDelayed(() -> {
            authResp.sendCode("1", null,"CODE1234IOP890");
        }, 2000);
    }
}