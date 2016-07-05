package com.example.administrator.customview.PullToRefereshRecyclerView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/7/5.
 */
public class PTRecyclerView extends RecyclerView {

    private int loadmore_h;//上拉刷新需要的高度

    public PTRecyclerView(Context context) {
        super(context);
    }

    public PTRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PTRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public interface loadMore{
        void OnRefresh(RecyclerView recyclerView);
    }

    public PTRecyclerView.loadMore getLoadMore() {
        return loadMore;
    }

    private loadMore loadMore;

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(getAdapter() instanceof PTRAdapter && ((PTRAdapter) getAdapter()).isHasFooterView()) {
            //有尾布局
            if (getLayoutManager() instanceof LinearLayoutManager) {
                if (((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition() ==
                        getAdapter().getItemCount() && getScrollState() != RecyclerView.SCROLL_STATE_DRAGGING) {
                    //显示了最后一行并且不是手动拖拽的情况 false
                    stopScroll();//停止滑动
                }
            } else if (oldt - t < 200) {//true并且滑动距离小于200
                smoothScrollToPosition(getAdapter().getItemCount() - 1);
            } else {
                if(loadMore!=null)
                    loadMore.OnRefresh(this);
            }
            //结束

        }
    }

}
