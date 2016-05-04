package com.qingcong.guidepageinstructions;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {


    private ViewPager viewPager;

    private InstructionsView instructionsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instructionsView = (InstructionsView) findViewById(R.id.instructions_view);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ArrayList<View> viewList = new ArrayList<View>();
        for(int i = 0; i < 5; i++){
            View contentView = getLayoutInflater().inflate(R.layout.item_one, null);
            ((TextView)contentView.findViewById(R.id.cid)).setText("我就是"+i);
            viewList.add(contentView);
        }
        viewPager.setAdapter(new ViewPagerAdapter(viewList));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                instructionsView.setMoveOffset(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




}
