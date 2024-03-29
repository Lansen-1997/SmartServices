package com.example.myapp.adapter;

import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class GuideAdapter extends PagerAdapter {

    private List<ImageView> imageViews;

    public GuideAdapter(List<ImageView> imageViews){
        this.imageViews = imageViews;
    }

    /**
     * 获取当前要显示对象的数量
     */
    @Override
    public int getCount() {
        return imageViews.size();
    }

    /**
     * 判断是否用对象生成界面
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * 从ViewGroup中移除当前对象
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(imageViews.get(position));

    }
    /**
     * 当前要显示的对象
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

}
