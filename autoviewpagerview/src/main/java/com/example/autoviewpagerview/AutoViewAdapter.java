package com.example.autoviewpagerview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ziv on 2017/11/24.
 */

public abstract class AutoViewAdapter<T> extends PagerAdapter {

    public List<T> data;

    public AutoViewAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return (data == null || data.size() == 0) ? 0 : (data.size() == 1 ? 1 : Integer.MAX_VALUE);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        if (null != data && data.size() > 0) {
            int currentPosition = position % data.size();
            view = getInstantiateItem(container.getContext(), currentPosition);
            container.addView(view);
        }
        return view;
    }

    public abstract View getInstantiateItem(Context context, int currentPosition);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
