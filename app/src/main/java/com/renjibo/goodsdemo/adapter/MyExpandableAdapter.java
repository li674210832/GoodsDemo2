package com.renjibo.goodsdemo.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.renjibo.goodsdemo.AllCheckBoxSet;
import com.renjibo.goodsdemo.R;
import com.renjibo.goodsdemo.SetDelete;
import com.renjibo.goodsdemo.SetTextViewText;
import com.renjibo.goodsdemo.modle.GoodsBean;
import com.renjibo.goodsdemo.modle.GoodsChild;

import java.util.List;


/**
 * 任继波
 * Created by Administrator on 2017/2/10.
 */

public class MyExpandableAdapter extends BaseExpandableListAdapter implements TextWatcher,View.OnClickListener{
    private List<GoodsBean> goodsBeanList;
    private Context context;
    private LayoutInflater layoutInflater;
    private ExpandableListView expandableListView;
    //接口
    private SetTextViewText se;
    private AllCheckBoxSet allCheckBoxSet;

    //回调
    private SetDelete setDelete;
    public void add(SetTextViewText st){
        this.se=st;
    }
    public MyExpandableAdapter(List<GoodsBean> goodsBeanList, Context context, ExpandableListView expandableListView){
        this.context=context;
        this.goodsBeanList=goodsBeanList;
        layoutInflater = LayoutInflater.from(context);
        this.expandableListView=expandableListView;
    }
    public void checkSet(AllCheckBoxSet allCheckBoxSet){
        this.allCheckBoxSet=allCheckBoxSet;
    }
    @Override
    public int getGroupCount() {
        return goodsBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return goodsBeanList.get(groupPosition).getGood().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return goodsBeanList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return goodsBeanList.get(groupPosition).getGood().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;

        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.group_item, parent, false);
            groupViewHolder=new GroupViewHolder();
            groupViewHolder.textView= (TextView) convertView.findViewById(R.id.group_textView);
            groupViewHolder.group_textView_compile= (TextView) convertView.findViewById(R.id.group_textView_compile);
            groupViewHolder.checkBox= (CheckBox) convertView.findViewById(R.id.group_checkBox);
            convertView.setTag(groupViewHolder);
        }else{
            groupViewHolder= (GroupViewHolder) convertView.getTag();
        }
        final GoodsBean goodsBean = goodsBeanList.get(groupPosition);
        groupViewHolder.textView.setText(goodsBean.getName()+"");
        groupViewHolder.group_textView_compile.setOnClickListener(this);

        groupViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int a=0;
                goodsBean.setGroupChex(isChecked);
                Log.d("zzz","Group");
                List<GoodsChild> good = goodsBean.getGood();
                if(isChecked){

                    for(GoodsChild gc:good){
                        gc.setChildChe(isChecked);
                    }
                }else{
                    for(GoodsChild gc:good){
                        if(gc.isChildChe()==false) {
                            a++;
                            break;
                        }
                    }
                    if(a==0){
                        for(GoodsChild gc:good){
                            gc.setChildChe(isChecked);
                        }
                    }else{
                        goodsBeanList.get(groupPosition).setGroupChex(false);
                    }
                }
                allCheckBoxSet.setCheckBox();
                expandableListView.collapseGroup(groupPosition);
                expandableListView.expandGroup(groupPosition);

            }
        });
        groupViewHolder.checkBox.setChecked(goodsBean.isGroupChex());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildeViewHolder childeViewHolder;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.child_item, parent, false);
            childeViewHolder=new ChildeViewHolder();
            childeViewHolder.textView= (TextView) convertView.findViewById(R.id.child_textView);
            childeViewHolder.child_button_delete= (Button) convertView.findViewById(R.id.child_button_delete);
            childeViewHolder.checkBox= (CheckBox) convertView.findViewById(R.id.checkBoxChild);
            childeViewHolder.child_textView_price= (TextView) convertView.findViewById(R.id.child_textView_price);
            childeViewHolder.child_buttonAdd= (Button) convertView.findViewById(R.id.child_buttonAdd);
            childeViewHolder.child_buttonJian= (Button) convertView.findViewById(R.id.child_buttonJian);
            //childeViewHolder.child_textView_number= (TextView) convertView.findViewById(R.id.child_textView_number);
            childeViewHolder.child_editText_number= (EditText) convertView.findViewById(R.id.child_editText_number);
            childeViewHolder.right_relativeLayout = (RelativeLayout) convertView.findViewById(R.id.Right_relativeLayout);
            childeViewHolder.Right_delete_relativeLayout = (RelativeLayout) convertView.findViewById(R.id.Right_delete_relativeLayout);
            convertView.setTag(childeViewHolder);
        }else{
            childeViewHolder= (ChildeViewHolder) convertView.getTag();
        }
            if(goodsBeanList.get(groupPosition).isGoneView()){
                childeViewHolder.right_relativeLayout.setVisibility(View.GONE);
                childeViewHolder.Right_delete_relativeLayout.setVisibility(View.VISIBLE);
            }else{
                childeViewHolder.right_relativeLayout.setVisibility(View.VISIBLE);
                childeViewHolder.Right_delete_relativeLayout.setVisibility(View.GONE);
            }

        final GoodsChild goodsChild = goodsBeanList.get(groupPosition).getGood().get(childPosition);
        childeViewHolder.textView.setText(goodsChild.getName()+"");
        childeViewHolder.child_textView_price.setText("¥ "+goodsChild.getPrice());
        childeViewHolder.child_buttonJian.addTextChangedListener(this);
        childeViewHolder.child_button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("zzz","点击了按钮");
                setAlertDialog(groupPosition,childPosition);

            }
        });
        childeViewHolder.child_buttonJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("zzz","num:"+goodsChild.getNum());
                    int num=goodsChild.getNum()-1;
                    goodsChild.setNum(num);
                int num1 = goodsChild.getNum();
                if(num1==1){
                        childeViewHolder.child_buttonJian.setEnabled(false);
                }
//                goodsBeanList.get(groupPosition).getName()
//                goodsBeanList.get(groupPosition).setCountMonery();
                childeViewHolder.child_editText_number.setText(num+"");
                if(goodsChild.isChildChe()){
                    se.setTextView();
                }
            }
        });
        childeViewHolder.child_buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("zzz","num:"+goodsChild.getNum());
                int num=goodsChild.getNum()+1;
                goodsChild.setNum(num);
                int num1 = goodsChild.getNum();
                if(num1>1) {
                        childeViewHolder.child_buttonJian.setEnabled(true);
                }
                childeViewHolder.child_editText_number.setText(num+"");

                double count=goodsChild.getPrice()*goodsChild.getNum();//单个总价格
                double v1 = goodsBeanList.get(groupPosition).getCountMonery() + count;
                goodsBeanList.get(groupPosition).setCountMonery(v1);
                if(goodsChild.isChildChe()){
                    double price = v1;
                    se.setTextView();
                 }

            }
        });
        childeViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goodsChild.setChildChe(isChecked);
                childAllCheck(groupPosition);
                expandableListView.collapseGroup(groupPosition);
                expandableListView.expandGroup(groupPosition);
                setGroupCheckBox(groupPosition);
                //double price = goodsChild.getPrice()*goodsChild.getNum();
            }
        });
        if(goodsChild.getNum()==1){
            childeViewHolder.child_buttonJian.setEnabled(false);
        }else{
            childeViewHolder.child_buttonJian.setEnabled(true);
        }
        childeViewHolder.child_editText_number.setText(goodsChild.getNum()+"");
        childeViewHolder.checkBox.setChecked(goodsChild.isChildChe());
        //修改
        allCheckBoxSet.setCheckBox();
        se.setTextView();
        return convertView;
    }
    public void childAllCheck(int groupPosition){
        List<GoodsChild> good = goodsBeanList.get(groupPosition).getGood();
        int num=0;
        for(GoodsChild gc:good){
            if(gc.isChildChe()){
                num++;
            }
        }
        if(num==good.size()){
            goodsBeanList.get(groupPosition).setGroupChex(true);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.group_textView_compile:

                break;
        }
    }
    public void setAlertDialog(final int groupId, final int childId){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("删除商品");
        builder.setMessage("确定删除？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goodsBeanList.get(groupId).getGood().remove(childId);
                notifyDataSetChanged();
                Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                setGroupCheckBox(groupId);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"正在取消",Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    public void setGroupCheckBox(int groupId){
        List<GoodsChild> good1= goodsBeanList.get(groupId).getGood();
        for(GoodsChild gc:good1){
            if(gc.isChildChe()==false){
                //不是全选了
                goodsBeanList.get(groupId).setGroupChex(false);
            }
        }
    }
    class GroupViewHolder{
        TextView textView;
        TextView group_textView_compile;
        CheckBox checkBox;
    }
    class ChildeViewHolder{
        TextView textView;
        TextView child_textView_price;
        CheckBox checkBox;
        Button child_buttonJian;
        Button child_buttonAdd;
        //TextView child_textView_number;
        EditText child_editText_number;
        RelativeLayout right_relativeLayout;
        RelativeLayout Right_delete_relativeLayout;
        Button child_button_delete;
    }
}
