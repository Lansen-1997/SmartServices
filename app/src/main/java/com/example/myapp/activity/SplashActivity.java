package com.example.myapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.MainActivity;
import com.example.myapp.R;

public class SplashActivity extends AppCompatActivity {

    private LinearLayout splash_ll;

    @Override
    public void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        //设置渐变效果
        setAlphaAnimation();
    }

    private void initView(){
        splash_ll = findViewById(R.id.splash_ll);
    }

    private void setAlphaAnimation(){
        //生成动画对象
        AlphaAnimation animation = new AlphaAnimation(0.3f,1.0f);
        //设置持续时间3s
        animation.setDuration(3000);
        //给控件设置动画
        splash_ll.setAnimation(animation);
        //设置动画监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jump2Activity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 根据首次启动应用与否跳转到相应界面
     */
    private void jump2Activity(){
        SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
        Boolean isFirstRun = sp.getBoolean("isFirstRun", true);
        Intent intent = new Intent();
        if (isFirstRun == true){
            intent.setClass(this, GuideActivity.class);
        }else {
            intent.setClass(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
