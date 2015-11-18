# TagLayout
一个自定义的标签控件  
![](https://github.com/iKrelve/TagLayout/blob/master/1.png?raw=true)  
#####用法:  

* 添加布局文件:  
```xml
        <krelve.view.TagLayout
            android:id="@+id/tl"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="5dp"
            app:corner="4dp"
            app:pressedColor="@android:color/holo_green_dark"
            app:verticalSpacing="8dp" />
```
* 添加多个标签:  
```java
	tagLayout.setTags(mTags);//List<String>
```
* 添加单个标签:
```java
	tagLayout.addTag("一个新标签");
```
* 自定义属性:  
```xml
    <declare-styleable name="TagLayout">
	    <!--按下的颜色-->
        <attr name="pressedColor" format="color"></attr>
        <!--正常状态下的颜色，如果设置，则表示取消随机颜色-->
        <attr name="normalColor" format="color"></attr>
        <!--标签的颜色，默认白色-->
        <attr name="tagColor" format="color"></attr>
        <!--标签之间的垂直距离-->
        <attr name="verticalSpacing" format="dimension"></attr>
        <!--标签之间的水平距离-->
        <attr name="horizontalSpacing" format="dimension"></attr>
        <!--标签的背景圆角-->
        <attr name="corner" format="dimension"></attr>
    </declare-styleable>
```
* 设置点击事件:  
```java
        tagLayout.setOnItemClickListener(new TagLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View v, String tag) {
                Toast.makeText(MainActivity.this, tag + " 被点击了", Toast.LENGTH_SHORT).show();
            }
        });
```
