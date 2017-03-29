package com.quanquan.checkboxandrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChangquanSun
 * 2017/3/29
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.Myholder> {
    private List<String> datas;
    private Context context;
    private List<Integer> seletor;
    private List<String> seletorString=new ArrayList<>();

    public BaseAdapter(Context context,List<String> datas) {
        this.datas = datas;
        this.context=context;
        seletor=new ArrayList<>();
    }

    public void addData(String str){
        datas.add(datas.size(),str);
        notifyItemInserted(datas.size());
    }

    public void addDatas(List<String> strs){
        datas.addAll(datas.size(),strs);
        //方法一
        notifyItemInserted(datas.size());
        //方法二
//        notifyItemRangeInserted(datas.size(),strs.size());
    }
    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(final Myholder holder, final int position) {
        holder.tv.setText(datas.get(position));
        holder.cb.setTag(new Integer(position));//设置tag 否则划回来时选中消失
        //checkbox  复用问题
        if (seletor != null) {
             holder.cb.setChecked((seletor.contains(new Integer(position)) ? true : false));
        } else {
             holder.cb.setChecked(false);
        }
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!seletor.contains(holder.cb.getTag())) {
                        seletor.add(new Integer(position));
                        seletorString.add(datas.get(position));
                    }
                } else {
                    if (seletor.contains(holder.cb.getTag())) {
                        seletor.remove(new Integer(position));
                        seletorString.remove(datas.get(position));
                    }
                }
            }
        });

    }

    public List<String> getSeletorData(){
        return seletorString;
    }
    public List<Integer> getSeletorDataPos(){
        return seletor;
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }


    class Myholder extends RecyclerView.ViewHolder{
        private CheckBox cb;
        private TextView tv;
        public Myholder(View itemView) {
            super(itemView);
            cb= (CheckBox) itemView.findViewById(R.id.item_cb);
            tv= (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}

