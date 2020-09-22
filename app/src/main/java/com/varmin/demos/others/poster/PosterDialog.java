package com.varmin.demos.others.poster;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.tamsiree.rxkit.RxImageTool;
import com.varmin.demos.R;

/**
 * author：yang
 * created on：2020/9/8 16:30
 * description:
 */
public class PosterDialog extends Dialog {
    private final Bitmap mPostBitmap;
    private final ShareClickListener mClickListener;;

    public interface ShareClickListener{
        void shareFriends();
        void shareFriendsCircle();
    }

    public PosterDialog(@NonNull Context context, Bitmap postBitmap, ShareClickListener listener) {
        super(context, R.style.custom_dialog);
        this.mPostBitmap = postBitmap;
        this.mClickListener = listener;
        init();
    }

    private void init() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_poster, null);
        setContentView(contentView);

        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = displayMetrics.widthPixels;
        attributes.height = displayMetrics.heightPixels;
        getWindow().setAttributes(attributes);

        ImageView ivPoster = contentView.findViewById(R.id.ivPoster);
        ivPoster.setImageBitmap(mPostBitmap);
        contentView.findViewById(R.id.iv_friends).setOnClickListener(v -> {
            mClickListener.shareFriends();
            dismiss();
        });
        contentView.findViewById(R.id.iv_friends_circle).setOnClickListener(v -> {
            mClickListener.shareFriendsCircle();
            dismiss();
        });

        contentView.findViewById(R.id.iv_dismiss).setOnClickListener(v -> dismiss());
    }


}
