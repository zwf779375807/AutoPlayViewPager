package com.example.autoviewpagerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by ziv on 2017/11/23.
 */

public class AutoViewPager extends FrameLayout {

    //当前指示器
    private int currentPoiId;
    //其他指示器
    private int otherPoiId;
    //指示器直径
    private float poiRadius;
    //间隔时长
    private int interval;
    private ViewPager viewPager;
    //指示器集合
    private LinearLayout linearLayout;

    public AutoViewPager(@NonNull Context context) {
        this(context, null);
    }

    public AutoViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoViewPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoViewPager);
        currentPoiId = typedArray.getResourceId(R.styleable.AutoViewPager_currentPoi, 0);
        otherPoiId = typedArray.getResourceId(R.styleable.AutoViewPager_otherPoi, 0);
        poiRadius = typedArray.getDimension(R.styleable.AutoViewPager_poiRadius, 0f);
        interval = typedArray.getInteger(R.styleable.AutoViewPager_interval, 3000);
        typedArray.recycle();
        initView(context);
    }

    /**
     * 初始化布局
     */
    private void initView(Context context) {
        viewPager = new ViewPager(context);
        LayoutParams viewPagerLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(viewPagerLayoutParams);
        //指示器容器
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        LayoutParams linearLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.gravity = Gravity.BOTTOM;
        linearLayoutParams.bottomMargin = ViewUtil.Dp2Px(getContext(), 10);
        linearLayout.setLayoutParams(linearLayoutParams);
        addView(viewPager);
        addView(linearLayout);

    }

    /**
     * 设置适配器
     *
     * @param pagerAdapter
     */
    public void setAdapter(AutoViewAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
        final int size = pagerAdapter.data.size();
        //初始化当前页为MAX中间
        if (size > 0) {
            int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % size);
            viewPager.setCurrentItem(pos);
        } else {
            viewPager.setCurrentItem(0);
        }
        if (size > 1) { //一个以上可以滑动
            mHandler.sendEmptyMessageDelayed(1, interval);
        } else {
            mHandler.removeCallbacksAndMessages(null);
        }
        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //停止滑动
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.sendEmptyMessageDelayed(1, interval);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        mHandler.sendEmptyMessageDelayed(1, interval);
                        break;
                }
                return false;
            }
        });
        //根据项目条数添加指导点
        if (size > 1) {
            for (int i = 0; i < size; i++) {
                LinearLayout.LayoutParams poiParams = new LinearLayout.LayoutParams(ViewUtil.Dp2Px(getContext(), poiRadius), ViewUtil.Dp2Px(getContext(), poiRadius));
                ImageView poi = new ImageView(getContext());
                poiParams.rightMargin = ViewUtil.Dp2Px(getContext(), 2f);
                poi.setLayoutParams(poiParams);
                poi.setImageResource(otherPoiId);
                linearLayout.addView(poi);
            }
        }
        //初始化当前点
        setCurrentPoi(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (size > 0)
                    setCurrentPoi(position % size);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 指示器当前点显示
     *
     * @param currentPosition
     */
    private void setCurrentPoi(int currentPosition) {
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == currentPosition) {
                ImageView currentImg = (ImageView) linearLayout.getChildAt(currentPosition);
                currentImg.setImageResource(currentPoiId);
            } else {
                ImageView otherImg = (ImageView) linearLayout.getChildAt(i);
                otherImg.setImageResource(otherPoiId);
            }
        }
    }

    /**
     * 循环动力
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int nextPosition = viewPager.getCurrentItem() + 1;
            if (nextPosition < Integer.MAX_VALUE) { //当滚动到int最大时停止
                viewPager.setCurrentItem(nextPosition);
            }
            mHandler.removeMessages(1);
            mHandler.sendEmptyMessageDelayed(1, interval);
        }
    };


}
