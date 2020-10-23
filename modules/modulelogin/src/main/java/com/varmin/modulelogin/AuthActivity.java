package com.varmin.modulelogin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import com.atoshi.opensdk.RespBean;
import com.atoshi.opensdk.atoshi.AuthResp;
import com.atoshi.opensdk.atoshi.IAuthResp;

public class AuthActivity extends AppCompatActivity implements IAuthResp {
    private static final String TAG = "AuthActivity";
    private AuthResp authResp;
    private Button btnAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Log.d(TAG, "onCreate: ");

        authResp = new AuthResp(this);
        authResp.handleIntent(getIntent(), this);

        btnAuth = findViewById(R.id.btnAuth);
        btnAuth.setOnClickListener(v -> getCode());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Log.d(TAG, "onNewIntent: ");
        authResp.handleIntent(intent, this);
    }

    @Override
    public void getCode() {
        String clientId = authResp.getClientID();
        new Handler().postDelayed(() -> {
            authResp.sendCode(RespBean.CODE_OK, null,"CODE1234IOP890");
        }, 2000);
    }

    @Override
    public void getThirdAppInfo(String clientId) {
        new Handler().postDelayed(() -> {
            btnAuth.setText("网赚App");
        }, 1000);
    }
}