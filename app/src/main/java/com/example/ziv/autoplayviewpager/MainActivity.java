package com.example.ziv.autoplayviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.autoviewpagerview.AutoViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.autoViewPager)
    AutoViewPager autoViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        List<String> datas = new ArrayList<>();
        datas.add("http://pic27.photophoto.cn/20130522/0010023309252566_b.jpg");
        datas.add("http://pic28.photophoto.cn/20130827/0005018376937114_b.jpg");
        datas.add("http://pic71.nipic.com/file/20150706/21107201_230758751000_2.jpg");
        datas.add("http://pic28.photophoto.cn/20130830/0005018667531249_b.jpg");
        datas.add("http://pic13.nipic.com/20110331/6495305_095117198150_2.jpg");
        datas.add("http://img5.duitang.com/uploads/item/201601/12/20160112200836_dRTZx.jpeg");
        MyAutoViewPagerAdapter myAutoViewPagerAdapter = new MyAutoViewPagerAdapter(datas);
        autoViewPager.setAdapter(myAutoViewPagerAdapter);
    }
}
