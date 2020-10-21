package com.atoshi.opensdk.atoshi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.atoshi.opensdk.IAuthResp;
import com.atoshi.opensdk.R;

public class AuthActivity extends AppCompatActivity implements IAuthResp {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }
}