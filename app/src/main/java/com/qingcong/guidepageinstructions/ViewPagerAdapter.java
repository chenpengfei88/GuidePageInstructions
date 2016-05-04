package com.qingcong.guidepageinstructions;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by chenpengfei on 2016/5/4.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> list;

    public ViewPagerAdapter(List<View> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;//
    }


    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {
        View view = list.get(position);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        ((ViewPager)container).addView(view);
        return list.get(position);
    }


}
