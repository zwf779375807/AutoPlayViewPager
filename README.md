# AutoPlayViewPager

一个支持自动滑动的viewpager，代码简单，支持自定义拓展，小伙伴可以根据自己需要自行修改代码

  ![img](https://github.com/zwf779375807/AutoPlayViewPager/blob/master/app/show.gif)
  
  
# 如何使用
 第1
      allprojects {
	repositories {
	maven { url 'https://www.jitpack.io' }
	}
         }
 第2     dependencies {
           compile 'com.github.zwf779375807:AutoPlayViewPager:v1.5'
         }
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
   
   
java代码中使用,adapter继承AutoViewAdapter<T>，在构造函数中设置传递参数，重写getInstantiateItem(Context context,int currentPosition)方法，返回子view
  
  
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

  

