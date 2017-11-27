package com.example.ziv.autoplayviewpager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.autoviewpagerview.AutoViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by ziv on 2017/11/27.
 */

public class MyAutoViewPagerAdapter extends AutoViewAdapter<String> {
    private List<String> myData;

    public MyAutoViewPagerAdapter(List<String> data) {
        super(data);
        this.myData = data;
    }

    @Override
    public View getInstantiateItem(final Context context, final int currentPosition) {
        View v = View.inflate(context, R.layout.test_layout, null);
        ImageView ivItem = ButterKnife.findById(v, R.id.iv_item);
        Picasso.with(context).load(myData.get(currentPosition)).into(ivItem);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, String.valueOf(currentPosition), Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}
