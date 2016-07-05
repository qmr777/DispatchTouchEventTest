package com.example.administrator.customview.PullToRefereshRecyclerView;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public abstract class PTRAdapter<E> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_DATA = 0;//正常布局
    public static final int TYPE_FOOTER = 1;//尾布局

    @IntDef({TYPE_DATA, TYPE_FOOTER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ItemType {
    }

    List<E> list;
    Context context;
    View footerView;

    public boolean isHasFooterView() {
        return hasFooterView;
    }

    boolean hasFooterView = false;//有尾布局

    private int dp2px(int dp){
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    public PTRAdapter(Context context, List<E> dataList) {
        this.context = context;
        this.list = dataList;
    }

    public void addFooterView(int layoutID) {
        addFooterView(LayoutInflater.from(context).inflate(layoutID, null));
    }

    public void addFooterView(View footerView) {
        this.footerView = footerView;
        hasFooterView = true;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasFooterView & position == list.size() + 1)
            return TYPE_FOOTER;
        return TYPE_DATA;
    }

    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, @ItemType int viewType);

    @Override
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        if (footerView != null)//有尾布局就加一
            return list.size() + 1;
        else
            return list.size();
    }

    class FooterHolder extends RecyclerView.ViewHolder {

        public FooterHolder(View itemView) {
            super(itemView);
        }
    }

    abstract class DataViewHolder extends RecyclerView.ViewHolder {

        public DataViewHolder(View itemView) {
            super(itemView);
        }
    }
}
