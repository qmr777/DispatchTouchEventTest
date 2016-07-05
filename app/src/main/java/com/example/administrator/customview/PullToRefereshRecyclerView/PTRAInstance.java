package com.example.administrator.customview.PullToRefereshRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.customview.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 *
 */
public class PTRAInstance extends PTRAdapter<String> {

    public PTRAInstance(Context context, List<String> dataList) {
        super(context, dataList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, @ItemType int viewType) {
        if(viewType == PTRAdapter.TYPE_FOOTER)
            return new PTRAdapter.FooterHolder(footerView);
        else if(viewType == PTRAdapter.TYPE_DATA)
            return new DataHolder(LayoutInflater.from(context).inflate(R.layout.cell2,null));

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DataHolder)
            ((DataHolder) holder).textView.setText(list.get(position));
    }

    class DataHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public DataHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View itemView) {
            super(itemView);
        }
    }
}
