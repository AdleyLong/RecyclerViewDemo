>RecyclerView 是Android L版本中新添加的一个用来取代ListView和GridView的控件，它的灵活性与可替代性比listview更好。android系统源码里面也已经开始使用了，比如Launcher界面，比如Message界面等。接下来通过下面的文章讲解如何使用RecyclerView,为啥可以抛弃ListView和GridView，后面，还会分析下腾讯首页是如何实现的。

# 一、简介

我们先来看看[官网](https://developer.android.com/guide/topics/ui/layout/recyclerview.html)上对recyclerview的一段功能介绍。
![这里写图片描述](https://github.com/AdleyLong/RecyclerViewDemo/blob/master/screenshots/recyclerview.png)
简单点说，包括以下几点：

1、使用`LinearLayoutManager`的布局管理，可以用来代替ListView

2、使用`GridLayoutManager `的布局管理，可以用来代替GridView

3、使用`StaggeredGridLayoutManager`的布局管理，可以用来实现瀑布流。


其实，RecyclerView的功能和好处完全不止这么多，比如，还可以实现横向的ListView，比如实现不同position显示不同布局的功能等等。下面，将结合例子给大家展示下RecyclerView的功能。
先看效果图吧。
![这里写图片描述](https://github.com/AdleyLong/RecyclerViewDemo/blob/master/screenshots/all.gif)


# 二、实现ListView、GridView、瀑布流

## 1、导入RecyclerView的库

现在貌似RecyclerView已经集成进v7包里面，所以，可以直接

```
compile 'com.android.support:appcompat-v7:25.0.0'
```
或者也可以直接导recyclerview的库

```
compile 'com.android.support:recyclerview-v7:25.0.0'
```

## 2、xml布局里面使用

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerview"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</LinearLayout>
```

## 3、设置基本布局

先上效果图
![这里写图片描述](https://github.com/AdleyLong/RecyclerViewDemo/blob/master/screenshots/2.gif)

**a、设置linearlayout布局**
```
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(manager);
```
如果设置Orientation是VERTICAL的话，就是竖直方向的ListView
如果要实现水平方向上的ListView，则如下：
```
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerview.setLayoutManager(manager);
```
如果不设置Orientation，则默认是竖直方向的。

**b、设置成GridLayoutManager**

```
        mRecyclerview.setLayoutManager(new GridLayoutManager(mContext, 2));
```

这样就是上面那个实现成gridView效果的，`GridLayoutManager`里面有2个参数，第一个传上下文，第二个传网格是几列。我们设置的是2列。看一下效果。

![这里写图片描述](https://github.com/AdleyLong/RecyclerViewDemo/blob/master/screenshots/3.gif)

**c、设置成StaggeredGridLayoutManager**

```
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
```
这里面有2个参数，第一个是指分几列，第二个指是横向的还是纵向的，我们还是看下效果图

![这里写图片描述](https://github.com/AdleyLong/RecyclerViewDemo/blob/master/screenshots/4.gif)

## 4、设置adapter
这个Adapter可不是继承BaseAdapter那么简单，而是要继承RecyclerView.Adapter，

```
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
              ......
｝
```
adapter有几个Override的方法
1、onCreateViewHolder，设置item的布局
2、onBindViewHolder，绑定数据，类似于之前ListView的getView
3、getItemCount，recyclerView显示多少项。
4、getItemViewType，根据position返回类型，返回的类型传给onCreateViewHolder。（这个方法很重要，下面将混合布局的时候会具体讲）


当然，还需要设置一下ViewHolder，RecyclerView中强制客户使用ViewHolder，之前的ListView就经常说到使用ViewHolder来进行优化。使用ViewHolder其中一点好处是能够避免重复调用方法findViewById()，对当前item的View中的子View进行管理。
ViewHolder 描述RecylerView中某个位置的itemView和元数据信息，属于Adapter的一部分。其实现类通常用于保存 findViewById 的结果。

# 三、显示混合布局
效果图
![这里写图片描述](https://github.com/AdleyLong/RecyclerViewDemo/blob/master/screenshots/1.gif)

如上图这种，现在很流行的布局，里面包含很多不同的布局，但还是可以很流畅的滑动，其实这个本身就是一个RecyclerView实现的，但是RecyclerView是如何根据不同的position来显示不同的布局的呢，大家可以参考我之前写的博客： [RecyclerView的不同position加载不同View实现](http://blog.csdn.net/picasso_l/article/details/50697844)。

其实原理就是使用到了RecyclerView.Adapter里面另外一个重写的方法：

```
public int getItemViewType(int position)
```
这个方法可以根据不同的position返回不同的type，这个type会以参数的形式给`onCreateViewHolder`的第二个参数，

```
public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) 
```
这样，onCreateViewHolder就可以根据不同的viewType来加载不同的xml布局

```
switch (type) {
            case ITEM_BANNER:
                return new BannerViewHolder
                        (mLayoutInflater.inflate(R.layout.fragment_home_banner, parent, false));
            case ITEM_THIRD:
                return new ThirdViewHolder
                        (mLayoutInflater.inflate(R.layout.fragment_home_third, parent, false));
            case ITEM_CATEGORY:
                return new CategoryViewHolder
                        (mLayoutInflater.inflate(R.layout.fragment_home_category, parent, false));
            case ITEM_LIST:
                return new ListViewHolder
                        (mLayoutInflater.inflate(R.layout.fragment_home_list, parent, false));
            case ITEM_AD:
                return new AdViewHolder
                        (mLayoutInflater.inflate(R.layout.fragment_home_ad, parent, false));
        }
```
具体可以参考demo里面的TecentAdapter这个类，腾讯首页这个页面，按照类型来分，可以分为banner、三方应用、大图、列表、广告这5块。

# 四、点击事件
RecyclerView不比ListView，ListView直接有onItemClick的监听事件，但是RecyclerView是需要自己写的。
在adapter里面，先定义callback接口

```
/**
     * add onItemClick begin
     */
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }
```
在adapter的onBindViewHolder方法里面

```
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
```

之后，在Activity中，可以直接回调

```
mAdapter.setOnItemClickListener(new NewsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //写点击事件
            }
        });
```

看一下效果
![这里写图片描述](https://github.com/AdleyLong/RecyclerViewDemo/blob/master/screenshots/5.gif)

---
其实，关于RecyclerView，我之前写过很多博客了

[RecyclerView的拖动和滑动：基本的ItemTouchHelper示例（转）](http://blog.csdn.net/picasso_l/article/details/49679059)

[RecyclerView的不同position加载不同View实现](http://blog.csdn.net/picasso_l/article/details/50697844)

[RecycleView三种表现形式的上拉加载和下拉刷新](http://blog.csdn.net/picasso_l/article/details/49275923)

[RecyclerView的插入和删除](http://blog.csdn.net/picasso_l/article/details/51691629)

大家有兴趣可以看一下，另外，编译好的app就在更目录下，app-debug.apk，把代码拉下来直接安装apk就能看到演示。
