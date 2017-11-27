# AutoPlayViewPager
一个支持自动滑动的viewpager，代码简单，支持自定义拓展，小伙伴可以根据自己需要自行修改代码

xml中使用  

    <com.example.autoviewpagerview.AutoViewPager
        android:id="@+id/autoViewPager"
        android:layout_width="0dp"
        android:layout_height="300dp"
        app:currentPoi="@drawable/current_poi"
        app:interval="3000"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:otherPoi="@drawable/other_poi"
        app:poiRadius="3dp"
        />
 <br>
   interval ：  为自定播放时长; <br>
   currentPoi： 当前页指示器; <br>
   otherPoi：   其他页指示器; <br>
   poiRadius：  指示器大小 <br>
   
   
  
  

