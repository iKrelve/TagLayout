package krelve.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import krelve.taglayout.R;
import krelve.utils.DrawableUtils;

/**
 * Created by krelve on 2015/11/10.
 */
public class TagLayout extends ViewGroup {
    private Context mContext;
    private int mPressedColor;
    private int mNormalColor;
    private int mTagColor;
    private int mRealWidth;
    private List<Line> lines;
    private int mVerticalSpacing;
    private int mHorizontalSpacing;
    private int mPerHeight;
    private OnItemClickListener mOnItemClickListener;
    private int mCorner;
    private int mHorPadding;
    private int mVerPadding;

    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        lines = new ArrayList<>();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TagLayout);
        mNormalColor = ta.getColor(R.styleable.TagLayout_normalColor, 0);
        mPressedColor = ta.getColor(R.styleable.TagLayout_pressedColor, 0xffcecece);
        mTagColor = ta.getColor(R.styleable.TagLayout_tagColor, 0xffffffff);
        mHorizontalSpacing = ta.getDimensionPixelOffset(R.styleable.TagLayout_horizontalSpacing, dip2px(10));
        mVerticalSpacing = ta.getDimensionPixelOffset(R.styleable.TagLayout_verticalSpacing, dip2px(10));
        mCorner = ta.getDimensionPixelOffset(R.styleable.TagLayout_corner, dip2px(5));
        mHorPadding = dip2px(5);
        mVerPadding = dip2px(3);
        ta.recycle();
    }


    public void setTags(List<String> tags) {
        GradientDrawable pressedDrawable = DrawableUtils.createRoundRect(mPressedColor, mCorner);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        for (String tag : tags) {
            addViewInLayout(createTextView(pressedDrawable, tag), -1, lp);
        }
        requestLayout();
    }

    public void addTag(String tag) {
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        GradientDrawable pressedDrawable = DrawableUtils.createRoundRect(mPressedColor, mCorner);
        addView(createTextView(pressedDrawable, tag), -1, lp);
    }

    private int red, green, blue, color;

    @NonNull
    private TextView createTextView(GradientDrawable pressedDrawable, final String tag) {
        if (mNormalColor == 0) {
            Random random = new Random();
            red = random.nextInt(200) + 25;
            green = random.nextInt(200) + 25;
            blue = random.nextInt(200) + 25;
            color = Color.rgb(red, green, blue);
        } else {
            color = mNormalColor;
        }
        GradientDrawable normalDrable = DrawableUtils.createRoundRect(color, mCorner);
        StateListDrawable selectorDrawable = DrawableUtils.createSelectorDrawable(pressedDrawable, normalDrable);
        TextView tv = new TextView(mContext);
        tv.setPadding(mHorPadding, mVerPadding, mHorPadding, mVerPadding);
        tv.setBackgroundDrawable(selectorDrawable);
        tv.setText(tag);
        tv.setTextColor(mTagColor);
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, tag);
                }
            }
        });
        return tv;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        if (count != 0) {
            lines.clear();
            mRealWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
            int realHeight = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mRealWidth, widthMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : widthMode);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(realHeight, heightMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : heightMode);
            int spaceWidth = mRealWidth;

            View child;
            Line line = new Line();
            for (int i = 0; i < count; i++) {
                child = getChildAt(i);
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                int childWidth = child.getMeasuredWidth();
                if (spaceWidth >= childWidth) {
                    line.add(child);
                    spaceWidth -= childWidth;
                    spaceWidth -= mHorizontalSpacing;
                } else {
                    lines.add(line);
                    line = new Line();
                    line.add(child);
                    spaceWidth = mRealWidth - childWidth - mHorizontalSpacing;
                }
            }
            lines.add(line);
            mPerHeight = getChildAt(0).getMeasuredHeight();
            int totalHeight = mPerHeight * lines.size() + mVerticalSpacing * (lines.size() - 1)
                    + getPaddingTop() + getPaddingBottom();
            setMeasuredDimension(mRealWidth + getPaddingLeft() + getPaddingRight(),
                    resolveSize(totalHeight, heightMeasureSpec));
        } else {
            setMeasuredDimension(mRealWidth + getPaddingLeft() + getPaddingRight(),
                    resolveSize(0, heightMeasureSpec));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        t += getPaddingTop();
        l += getPaddingLeft();
        for (Line line : lines) {
            line.layout(l, t);
            t += mPerHeight + mVerticalSpacing;
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, String tag);
    }

    class Line {
        private List<View> children;
        private int totalWidth;

        public Line() {
            children = new ArrayList<>();
            totalWidth = 0;
        }

        public void add(View child) {
            totalWidth += child.getMeasuredWidth();
            children.add(child);
        }


        public void layout(int l, int t) {
            totalWidth += (children.size() - 1) * mHorizontalSpacing;
            int perPlus = 0;
            if (totalWidth < mRealWidth) {
                perPlus = (mRealWidth - totalWidth) / children.size();
            }
            for (View child : children) {
                int childWidth = child.getMeasuredWidth() + perPlus;
                if (perPlus > 0) {
                    int widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
                    int heightMeasureSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), MeasureSpec.EXACTLY);
                    child.measure(widthMeasureSpec, heightMeasureSpec);
                }
                child.layout(l, t, l + childWidth, t + child.getMeasuredHeight());
                l = l + childWidth + mHorizontalSpacing;
            }
        }
    }


    private int dip2px(int dip) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }
}
