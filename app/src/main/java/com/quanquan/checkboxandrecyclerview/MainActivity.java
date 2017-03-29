package com.quanquan.checkboxandrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerView;

    @OnClick(R.id.btn)
    public void onDeleteClick(){
        Log.e("sunchangquan",adapter.getSeletorDataPos().toString());
        datas.removeAll(adapter.getSeletorData());
        adapter=new BaseAdapter(this,datas);
        recyclerView.setLayoutManager(new CustomLinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    int i;
    @OnClick(R.id.btn_add)
    public void onAddData(){
        List<String> strs=new ArrayList<>();
        strs.add("南通"+i);
        strs.add("兴化"+i);
        i++;
        adapter.addDatas(strs);
    }
    private BaseAdapter adapter;

    List<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initData();
    }

    private void initData() {
        datas=new ArrayList<>();
        for(int i=0;i<10;i++){
            datas.add("帅比权"+i);
        }
        adapter=new BaseAdapter(this,datas);
        recyclerView.setLayoutManager(new CustomLinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
