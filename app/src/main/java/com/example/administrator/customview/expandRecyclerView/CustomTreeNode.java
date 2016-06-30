package com.example.administrator.customview.expandRecyclerView;

import com.example.administrator.customview.Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 *
 */
public class CustomTreeNode<T>{

    public List<CustomTreeNode<T>> childList = new LinkedList<>();

    //public String message;

    public T data;

    int level;

    CustomTreeNode<T> parent;

    //绑定某个节点为父节点
    public void attach(CustomTreeNode<T> parent) {
        this.level = parent.getLevel()+1;
        this.parent = parent;
        parent.childList.add(this);
    }

    public CustomTreeNode(T data){
        this.data = data;
    }

    //是否显示子项
    public boolean isShowChild() {
        return showChild;
    }

    public void setShowChild(boolean showChild) {
        this.showChild = showChild;
    }

    private boolean showChild = false;

    //当前节点高度
    public int getLevel(){
        if(parent == null)
            return 0;
        return level;
    }

}
