package com.varmin.demos.others.poster;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.varmin.demos.R;

public class PosterActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        // TODO: yang 2020/9/7 缩放3120*3900, 网络图片不会被缩放
        // TODO: yang 2020/9/7 硬件加速
        ImageView ivImg = findViewById(R.id.imageView);


        findViewById(R.id.btnClick).setOnClickListener(v -> {
           test();
        });

        test();
    }

    private void test(){
        PosterHelper.getInstance()
                .setBgUrl("https://game-img.atoshi.mobi/share/bg01.jpg")
                .setAvatarUrl("http://thirdwx.qlogo.cn/mmopen/vi_32/oY0uMWDwe3ic4r64XmGz0PIcYeLQPjMDvyo5swUibribaRpJ3Kr6gW8nm17jagyj9PEBaXOicSfyYxRMLqb9VXYaqQ/132")
                .setCodeUrl("https://game-img.atoshi.mobi/share/erweima.jpg")
                .setCodeStr("我的邀请码：123456")
                .setIntroStr("我是黄洋\n跟我一起玩天天成语吧，填写我的邀请码送你10个原子币哦~")
                .getPosterBitmap(new PosterHelper.Callback() {
                    @Override
                    public void success(Bitmap bitmap) {
                        new PosterDialog(PosterActivity.this, bitmap, new PosterDialog.ShareClickListener() {
                            @Override
                            public void shareFriends() {
                                Toast.makeText(PosterActivity.this, "好友", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void shareFriendsCircle() {
                                Toast.makeText(PosterActivity.this, "朋友圈", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                    }
                    @Override
                    public void failed(String errMsg) {
                        System.out.println("PosterActivity.failed: "+errMsg);
                    }
                });
    }
}