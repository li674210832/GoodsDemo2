package com.renjibo.goodsdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.renjibo.goodsdemo.adapter.MyExpandableAdapter;
import com.renjibo.goodsdemo.modle.GoodsBean;
import com.renjibo.goodsdemo.modle.GoodsChild;
import com.renjibo.goodsdemo.view.TopGroupView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TopGroupView.onTitleBarClickListener,SetTextViewText
        ,AllCheckBoxSet{

    private ExpandableListView expandableListView;
    private MyExpandableAdapter myExpandableAdapter;
    private List<GoodsBean> goodsBeanList;
    private CheckBox bottom_checkBox;
    private Button buttonCountPrice;
    private TextView textView_countPrices;
    private double allCount;
    //全部是allCheck
    private boolean allCheck;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                textView_countPrices.setText("¥ " + msg.obj);
            }
            if(msg.what==2){
                myExpandableAdapter.notifyDataSetChanged();
                bottom_checkBox.setChecked(allCheck);
            }
//            if(msg.what==3)
//                bottom_checkBox.setChecked(false);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDatas();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置自动展开
        //自动展开必须写在onResume方法中，否则会发生错误
        for (int i = 0; i < goodsBeanList.size(); i++) {
            expandableListView.expandGroup(i);
        }

//将ExpandableListView groupitem中系统自带的下拉箭头图标去掉
        expandableListView.setGroupIndicator(null);
    }

    public void setDatas(){
        goodsBeanList = new ArrayList<>();
        List<GoodsChild> good=new ArrayList<>();
        for(int j=0;j<10;j++){
            GoodsChild goodsChild = new GoodsChild(false,"","薯片商品"+j,getNumber(),1);
            good.add(goodsChild);
        }
        List<GoodsChild> good1=new ArrayList<>();
        for(int j=0;j<11;j++){
            GoodsChild goodsChild = new GoodsChild(false,"","红茶商品"+j,getNumber(),1);
            good1.add(goodsChild);
        }
        List<GoodsChild> good2=new ArrayList<>();
        for(int j=0;j<8;j++){
            GoodsChild goodsChild = new GoodsChild(false,"","飞机商品"+j,getNumber(),1);
            good2.add(goodsChild);
        }
        GoodsBean goodsBean = new GoodsBean("零食店铺", good, false);
        goodsBeanList.add(goodsBean);
        GoodsBean goodsBean1 = new GoodsBean("茶叶店铺", good1, false);
        goodsBeanList.add(goodsBean1);
        GoodsBean goodsBean2 = new GoodsBean("玩具店铺", good2, false);
        goodsBeanList.add(goodsBean2);
    }
    //随机生成一个数
    public double getNumber(){
//        Random random=new Random();
//        int i = random.nextInt(100)+1;
        return 10.01;
    }
    public void initView(){
        bottom_checkBox = (CheckBox) findViewById(R.id.bottom_checkBox);
        buttonCountPrice = (Button) findViewById(R.id.buttonCountPrice);
        textView_countPrices = (TextView) findViewById(R.id.textView_countPrices);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        TopGroupView topGroupView= (TopGroupView) findViewById(R.id.topGroupView);
        topGroupView.setClickListener(this);
        myExpandableAdapter = new MyExpandableAdapter(goodsBeanList, this, expandableListView);
        //回调
        myExpandableAdapter.add(this);
        myExpandableAdapter.checkSet(this);
        expandableListView.setAdapter(myExpandableAdapter);
        bottom_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                new Thread(){
                    @Override
                    public void run() {
                        if(isChecked){
                            for(GoodsBean gb:goodsBeanList){
                                Log.d("zzz","bottom_checkBox");
                                gb.setGroupChex(isChecked);
                                for(GoodsChild gc:gb.getGood()){
                                    gc.setChildChe(isChecked);
                                }
                            }
                            allCheck=isChecked;
                        }else{
                            int num=0;
                            for(GoodsBean gb:goodsBeanList){
                                if(gb.isGroupChex()==false){
                                    num++;
                                    break;
                                }
                            }
                            if(num==0){
                                for(GoodsBean gb:goodsBeanList){
                                    gb.setGroupChex(false);
                                    for(GoodsChild gc:gb.getGood()){
                                        gc.setChildChe(false);
                                    }
                                }
                            }
                            allCheck=false;

                        }

                        mHandler.sendEmptyMessage(2);
                    }

                }.start();

            }
        });

//        expandableListView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(MainActivity.this,"changan",Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(MainActivity.this,"setOnChildClickListener  "+groupPosition+"  "+childPosition,Toast.LENGTH_SHORT).show();
//                        CheckBox checkBoxChild= (CheckBox) v.findViewById(R.id.checkBoxChild);
//                goodsBeanList.get(groupPosition).getGood().get(childPosition).setChildChe(!checkBoxChild.isChecked());
//                return true;
//            }
//        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(MainActivity.this,"setOnGroupClickListener",Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }
    @Override
    public void onBackClick() {

        Toast.makeText(MainActivity.this,"点击了onBackClick",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onRightClick() {
        Toast.makeText(MainActivity.this,"开始编辑",Toast.LENGTH_SHORT).show();
        for(GoodsBean gb:goodsBeanList){
            gb.setGoneView(!gb.isGoneView());
        }
        myExpandableAdapter.notifyDataSetChanged();
    }

    //修改值
    @Override
    public void setTextView() {
        new Thread(){
            @Override
            public void run() {
                double allCount1=0;
                for(GoodsBean gb:goodsBeanList){
                    for(GoodsChild gc:gb.getGood()){
                        //Log.d("zzz","gc:"+gc.isChildChe());
                        if(gc.isChildChe()) {
                            allCount1 += gc.getNum() * gc.getPrice();
                            //Log.d("zzz", "count:" + allCount1);
                        }else{
                            mHandler.sendEmptyMessage(3);
                        }
                    }
                }
                Message mess=Message.obtain();
                mess.what=1;
                float countAll= (float) allCount1;
                mess.obj=countAll;
                mHandler.sendMessage(mess);
            }
        }.start();
        //textView_countPrices.setText("¥ " + allCount1);
    }
    //监听子条目的checkbox 改变
    @Override
    public void setCheckBox() {
        int num=0;
        for(GoodsBean gb:goodsBeanList){
            if(gb.isGroupChex()){
                num++;
            }
        }
        if(num==goodsBeanList.size()){
            bottom_checkBox.setChecked(true);
        }else{
            bottom_checkBox.setChecked(false);
        }
    }
}

