package com.varmin.demos.others.expandable;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.varmin.demos.R;

/**
 * Description: 自定义带有输入框的Bottom弹窗
 * Create by dance, at 2019/2/27
 */
public class CustomEditTextBottomPopup extends BottomPopupView implements View.OnClickListener {
    private final GamePostItem.PostItem mPostItem;
    private EditText etComment;
    private final int MAX_COUNT = 5;

    public CustomEditTextBottomPopup(@NonNull Context context, GamePostItem.PostItem item) {
        super(context);
        this.mPostItem = item;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_edittext_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.btnPost).setOnClickListener(this);
        etComment = findViewById(R.id.etComment);
        etComment.setHint("回复: "+mPostItem.userId);
        etComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > MAX_COUNT) {
                    s.delete(MAX_COUNT, s.length());
                    Toast.makeText(getContext(), String.format("最多%d个字哦", MAX_COUNT), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPost:
            addPost();
            break;
        }
    }

    private void addPost() {
        dismiss();
        String content = etComment.getText().toString().trim();
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }
}
