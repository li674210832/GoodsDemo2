package com.renjibo.goodsdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renjibo.goodsdemo.R;

/**
 * 任继波
 * Created by Administrator on 2017/2/9.
 */

public class TopGroupView extends RelativeLayout implements View.OnClickListener {

    private Drawable leftImage;
    private int titleTextColor;
    private int titleTextSize;
    private LayoutInflater inflater;
    private Drawable rightImage;
    private String titleText;
    private ImageView back_image;
    private ImageView right_image;
    private TextView text_title;

    public TopGroupView(Context context) {
        this(context,null);
    }
    public TopGroupView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public TopGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);
        getConfig(context,attrs);
        initView(context);
    }
    public void initView(Context context){
        View view=inflater.inflate(R.layout.custom_groupwidget,this,true);
        back_image = (ImageView) view.findViewById(R.id.back_image);
        right_image = (ImageView) view.findViewById(R.id.right_image);
        text_title = (TextView) view.findViewById(R.id.text_title);
        back_image.setOnClickListener(this);
        right_image.setOnClickListener(this);

        if(leftImage!=null){
            back_image.setImageDrawable(leftImage);
        }
        if(right_image !=null){
            right_image.setImageDrawable(rightImage);
        }
        if(text_title !=null){
            text_title.setText(titleText);
        }
    }
    public void getConfig(Context context,AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopGroupView);
        int indexCount=ta.getIndexCount();
        for(int i=0;i<indexCount;i++){
            int attr=ta.getIndex(i);
            switch (attr){
                case R.styleable.TopGroupView_RenleftBtn:
                    leftImage = ta.getDrawable(R.styleable.TopGroupView_RenleftBtn);
                    break;
                case R.styleable.TopGroupView_RenrightBtn:
                    rightImage = ta.getDrawable(R.styleable.TopGroupView_RenrightBtn);
                    break;
                case R.styleable.TopGroupView_RentitleColor:
                    titleTextColor = ta.getColor(R.styleable.TopGroupView_RentitleColor, Color.RED);
                    break;
                case R.styleable.TopGroupView_RentitleSize:
                    titleTextSize = ta.getDimensionPixelSize(R.styleable.TopGroupView_RentitleSize, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.TopGroupView_RentitleText:
                    titleText = ta.getString(R.styleable.TopGroupView_RentitleText);
                    break;
            }
        }
        //回收容器
        ta.recycle();
    }

    /**
     * 获取返回按钮
     * @return
     */
    public ImageView getBackView() {
        return back_image;
    }

    /**
     * 获取标题控件
     * @return
     */
    public TextView getTitleView() {
        return text_title;
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title){
        text_title.setText(title);
    }

    /**
     * 获取右侧按钮,默认不显示
     * @return
     */
    public ImageView getRightView() {
        return right_image;
    }

    private onTitleBarClickListener onMyClickListener;

    /**
     * 设置按钮点击监听接口
     * @param
     */
    public void setClickListener(onTitleBarClickListener listener) {
        this.onMyClickListener = listener;
    }

    /**
     * 导航栏点击监听接口
     */
    public static interface onTitleBarClickListener{
        /**
         * 点击返回按钮回调
         */
        void onBackClick();

        void onRightClick();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back_image:
                if(null != onMyClickListener)
                    onMyClickListener.onBackClick();
                break;
            case R.id.right_image:
                if(null != onMyClickListener)
                    onMyClickListener.onRightClick();
                break;
        }
    }
}
