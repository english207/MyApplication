package com.example.wto.myapplication.compoment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.example.wto.myapplication.R;

/**
 * Created by WTO on 2017/9/6 0006.
 *
 */
public class TitleOnClickListener implements View.OnClickListener
{
    private Context context;

    public TitleOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view)
    {
        AppCompatActivity activity = (AppCompatActivity) context;
        View popupView = activity.getLayoutInflater().inflate(R.layout.popupwindow, null);
        PopupWindow window = new PopupWindow(popupView, dip2px(context, 200), dip2px(context, 120));         // 2016/5/17 创建PopupWindow对象，指定宽度和高度
        window.setAnimationStyle(R.style.popup_window_anim);                                // 2016/5/17 设置动画
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));       // 2016/5/17 设置背景颜色
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.setFocusable(true);
        backgroundAlpha(0.5f);
        window.setOnDismissListener(new poponDismissListener());
        window.update();
        window.showAtLocation(view, Gravity.CENTER, 0 , 0);
    }

    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void backgroundAlpha(float bgAlpha)
    {
        AppCompatActivity activity = (AppCompatActivity) context;
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    private class poponDismissListener implements PopupWindow.OnDismissListener{
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }
}
