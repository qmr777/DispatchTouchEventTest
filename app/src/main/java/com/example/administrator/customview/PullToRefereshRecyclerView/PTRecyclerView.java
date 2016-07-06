package com.example.administrator.customview.PullToRefereshRecyclerView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Administrator on 2016/7/5.
 */
public class PTRecyclerView extends RecyclerView {

    private static final String TAG = "PTRecyclerView";

    private int loadmore_h;//上拉刷新需要的高度

    private boolean flag = false;//flag，是否能刷新

    private boolean refreshing = false;//正在刷新中

    private LinearLayoutManager manager;
    private loadMore loadMore;

    public PTRecyclerView(Context context) {
        super(context);
    }

    public PTRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PTRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public PTRecyclerView.loadMore getLoadMore() {
        return loadMore;
    }

    public void setLoadMore(loadMore loadMore) {
        this.loadMore = loadMore;
    }

    public void refreshed(){
        refreshing = false;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (flag && state == RecyclerView.SCROLL_STATE_IDLE &&
                manager.findLastCompletelyVisibleItemPosition() == getAdapter().getItemCount() - 1) {
            //不滑动&&加载更多完全显示 的时候
            if(!refreshing) {
                refreshing = true;
                if (loadMore != null)
                    loadMore.OnRefresh(this);
            } else {
                Log.w(TAG,"已经在刷新了");
            }
        } else if (flag && state == RecyclerView.SCROLL_STATE_IDLE &&
                manager.findLastVisibleItemPosition() == getAdapter().getItemCount() - 1 &&
                manager.findLastCompletelyVisibleItemPosition() == getAdapter().getItemCount()-2) {
            //smoothScrollUntilFooterViewInvisible(manager.findFirstVisibleItemPosition());
            //有bug！回弹位置不正确
            smoothScrollToPosition(manager.findFirstVisibleItemPosition() - 1);

        }
    }

    /*@Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        Log.e("TAG",dx+" "+dy+" scrollState:"+getScrollState());
        if (flag &&
                getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING &&
                dy < 200) {
            //是拖拽下去的
            //拖拽距离不足200
            Log.e("TAG", "state = 2 滑回原位置,拖拽距离不足200");
            if(manager!=null){
                int i = manager.findFirstVisibleItemPosition();
                smoothScrollToPosition(i);
            }
        } else if (flag &&
                getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING &&
                dy >= 200) {
            //是拖拽下去的
            //拖拽距离大于等于200
            //smoothScrollToPosition(getAdapter().getItemCount() - 1);
            smoothScrollToPosition(10);
            Log.e("TAG", "state = 3 刷新");
            if (loadMore != null)
                loadMore.OnRefresh(this);
        }
    }*/

    private void smoothScrollUntilFooterViewInvisible(int position) {
        while (manager.findLastCompletelyVisibleItemPosition() < getAdapter().getItemCount() - 1
                && manager.findLastVisibleItemPosition() == getAdapter().getItemCount() - 1) {
            //不能完全看到FooterView的时候
            Log.e("TAG", manager.findLastCompletelyVisibleItemPosition()
                    + " " + manager.findLastVisibleItemPosition()
                    + " " + (getAdapter().getItemCount() - 1)
                    + " " + position);
            if (position < 0)
                position = 0;
            smoothScrollToPosition(position);
            smoothScrollUntilFooterViewInvisible(position - 1);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //Log.w("TAG",l+"");
        if (getAdapter() instanceof PTRAdapter && ((PTRAdapter) getAdapter()).haveFooterView()) {
            //有尾布局
            if (getLayoutManager() instanceof LinearLayoutManager) {
                if (manager == null)
                    manager = (LinearLayoutManager) getLayoutManager();
                flag = (manager.findLastVisibleItemPosition() ==
                        getAdapter().getItemCount() - 1);//准备显示footerView

                if (flag && getScrollState() != RecyclerView.SCROLL_STATE_DRAGGING) {
                    //显示了最后一行并且不是手动拖拽的情况
                    stopScroll();//停止滑动
                }/* else if (flag &&
                        getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING &&
                        oldt - t < 200) {
                    //是拖拽下去的
                    //拖拽距离不足200
                    Log.e("TAG", "state = 2 滑回原位置 "+oldt +" "+t+" "+oldl +" "+l);
                    smoothScrollToPosition(getAdapter().getItemCount() - 1);
                } else if (flag &&
                        getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING &&
                        oldt - t >= 200) {
                    //是拖拽下去的
                    //拖拽距离大于等于200
                    Log.e("TAG", "state = 3 刷新");
                    if (loadMore != null)
                        loadMore.OnRefresh(this);
                }*/
                //结束
            }

        }
    }

    public interface loadMore {
        void OnRefresh(RecyclerView recyclerView);
    }

}
