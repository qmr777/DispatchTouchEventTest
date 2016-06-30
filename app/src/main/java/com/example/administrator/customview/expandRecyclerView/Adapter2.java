package com.example.administrator.customview.expandRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.customview.Bean.Bean2;
import com.example.administrator.customview.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public class Adapter2 extends RecyclerView.Adapter<Adapter2.MyViewHolder1> {

    List<Bean2.ResultlistBean.PersonBean> list;
    //Bean2 bean2;
    Context context;

    public Adapter2(Context context,List<Bean2.ResultlistBean.PersonBean> list){
        //this.bean2 = bean2;
        this.list = list;
        this.context = context;
    }

    @Override
    public Adapter2.MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Adapter2.MyViewHolder1(LayoutInflater.from(context).inflate(R.layout.rv_cell,null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder1 holder, int position) {
        holder.textView.setText(list.get(position).getUserID());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        public TextView textView;
        public MyViewHolder1(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
