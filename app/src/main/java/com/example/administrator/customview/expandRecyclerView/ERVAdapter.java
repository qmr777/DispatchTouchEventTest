package com.example.administrator.customview.expandRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.customview.Bean.Bean1;
import com.example.administrator.customview.R;
import com.example.administrator.customview.Tree.TreeHelper;
import com.example.administrator.customview.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 *
 */
public class ERVAdapter extends RecyclerView.Adapter<ERVAdapter.MyViewHolder> {

    Context context;
    CustomTreeNode<Bean1> root;
    List<CustomTreeNode<Bean1>> showList = new ArrayList<>();
    OnClick onClick;

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    onItemClickListener l;

    public void setOnItemClickListener(onItemClickListener listener){
        this.l = listener;
    }


    public void refreshList(){
        showList.clear();
        TreeHelper.traverse(root,showList);
        this.notifyDataSetChanged();
    }

    public ERVAdapter(Context context, CustomTreeNode<Bean1> root){
        this.context = context;
        this.root = root;
        onClick = new OnClick();
        TreeHelper.traverse(root,showList);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_cell,null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textView.setText(showList.get(position).data.data);
        holder.textView.setPaddingRelative(showList.get(position).getLevel()*40,0,0,0);
        if(l!=null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //l.onItemClick(holder.getAdapterPosition());
                    onClick.onClick(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            //itemView.setPaddingRelative(0+20*,0,0,0);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    class OnClick{
        void onClick(int position){
            if(showList.get(position).childList==null||showList.get(position).childList.size()==0)
                Toast.makeText(context,showList.get(position).data.data,Toast.LENGTH_SHORT).show();
            else {
                showList.get(position).setShowChild(!showList.get(position).isShowChild());
                refreshList();
            }
        }
    }
}
