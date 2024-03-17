package com.example.myapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.example.myapp.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private Button guide_btn;
    private ViewPager guide_viewpager;
    private GuideAdapter guideAdapter;
    private int[] images = {R.mipmap.y1,R.mipmap.y2,R.mipmap.y3,R.mipmap.y4};


    @Override
    public void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_guide);
        initView();

        //初始化图片
        initImgs();
        //初始化底部圆点指示器
        initDots();
        guideAdapter = new GuideAdapter(imageViews);
        guide_viewpager.setAdapter(guideAdapter);

        guide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putBoolean("isFirstRun", false);
                editor.apply();
                Intent intent= new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        guide_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotViews.length; i++){
                    if (position == i){
                        dotViews[i].setImageResource(R.drawable.guide_red_circle);
                    }else{
                        dotViews[i].setImageResource(R.drawable.guide_gray_circle);
                    }
                    if (position == dotViews.length - 1){
                        guide_btn.setVisibility(View.VISIBLE);
                    }else{
                        guide_btn.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView(){
        guide_btn = findViewById(R.id.guide_btn);
        guide_viewpager = findViewById(R.id.guide_viewpager);
    }

    private List<ImageView> imageViews;
    /**
     * 初始化图片
     */
    private void initImgs(){
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        imageViews = new ArrayList<>();
        for (int i = 0; i < images.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViews.add(imageView);
        }
    }

    private ImageView[] dotViews;
    /**
     * 初始化底部圆点指示器
     */
    private void initDots(){
        LinearLayout guide_ll = findViewById(R.id.guide_ll);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
        params.setMargins(10,0,10,0);
        dotViews = new ImageView[images.length];
        for (int i = 0; i < imageViews.size(); i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            if (i == 0){
                imageView.setImageResource(R.drawable.guide_red_circle);
            }else{
                imageView.setImageResource(R.drawable.guide_gray_circle);
            }
            dotViews[i] = imageView;
            final int currentViewPagerIndex = i;
            dotViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    guide_viewpager.setCurrentItem(currentViewPagerIndex);
                }
            });
            guide_ll.addView(imageView);
        }
    }
}





