package krelve.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import krelve.view.R;
import krelve.view.TagLayout;

public class MainActivity extends AppCompatActivity {
    private TagLayout tagLayout;
    private List<String> mTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagLayout = (TagLayout) findViewById(R.id.tl);
        initTags();
        tagLayout.setTags(mTags);
        tagLayout.setOnItemClickListener(new TagLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View v, String tag) {
                Toast.makeText(MainActivity.this, tag + " 被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTags() {
        mTags = new ArrayList<>();
        mTags.add("krelve");
        mTags.add("大灰狼");
        mTags.add("卖火柴的小女孩");
        mTags.add("小豆丁");
        mTags.add("丁丁历险记");
        mTags.add("跑跑更健康");
        mTags.add("网易云音乐");
        mTags.add("小白兔");
        mTags.add("卖女孩的小火柴");
        mTags.add("碟中谍");
        mTags.add("Top 10");
        mTags.add("王牌特工");
        mTags.add("绿箭侠");
        mTags.add("肯尼塔");
        mTags.add("两只耳朵竖起来");
        mTags.add("美团");
        mTags.add("机器学习");
        mTags.add("无监督学习");
        mTags.add("稀奇古怪");
        mTags.add("闪电侠");
        mTags.add("再多来点喽");
        mTags.add("快编不下去了");
        mTags.add("看来只能重复了");
        mTags.add("krelve");
        mTags.add("大灰狼");
        mTags.add("卖火柴的小女孩");
        mTags.add("小豆丁");
        mTags.add("丁丁历险记");
        mTags.add("跑跑更健康");
        mTags.add("网易云音乐");
        mTags.add("小白兔");
        mTags.add("卖女孩的小火柴");
        mTags.add("碟中谍");
        mTags.add("Top 10");
        mTags.add("王牌特工");
        mTags.add("绿箭侠");
        mTags.add("肯尼塔");
        mTags.add("两只耳朵竖起来");
        mTags.add("美团");
        mTags.add("机器学习");
        mTags.add("无监督学习");
        mTags.add("稀奇古怪");
        mTags.add("闪电侠");
        mTags.add("再多来点喽");
        mTags.add("快编不下去了");
        mTags.add("看来只能重复了");
        mTags.add("krelve");
        mTags.add("大灰狼");
        mTags.add("卖火柴的小女孩");
        mTags.add("小豆丁");
        mTags.add("丁丁历险记");
        mTags.add("跑跑更健康");
        mTags.add("网易云音乐");
        mTags.add("小白兔");
        mTags.add("卖女孩的小火柴");
        mTags.add("碟中谍");
        mTags.add("Top 10");
        mTags.add("王牌特工");
        mTags.add("绿箭侠");
        mTags.add("肯尼塔");
        mTags.add("两只耳朵竖起来");
        mTags.add("美团");
        mTags.add("机器学习");
        mTags.add("无监督学习");
        mTags.add("稀奇古怪");
        mTags.add("闪电侠");
        mTags.add("再多来点喽");
        mTags.add("快编不下去了");
        mTags.add("看来只能重复了");
    }

    public void add(View v) {
        switch (new Random().nextInt(5)) {
            case 0:
                tagLayout.addTag("一个新标签");
                break;
            case 1:
                tagLayout.addTag("又一个新标签");
                break;
            case 2:
                tagLayout.addTag("我很短");
                break;
            case 3:
                tagLayout.addTag("我很长我很长我很长");
                break;
            case 4:
                tagLayout.addTag("krelve.com");
                break;
        }
    }
}
