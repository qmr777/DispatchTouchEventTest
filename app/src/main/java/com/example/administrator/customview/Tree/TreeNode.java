package com.example.administrator.customview.Tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 * 树
 */
public class TreeNode<T> {

    public int level = 0;

    public List<TreeNode<T>> childList = new LinkedList<>();


    public T data;

    public TreeNode<T> parent;

    public TreeNode(){}

    public TreeNode(T data){
        this.data = data;
    }


    /**
     * 将子节点依附在某个父节点上
     * @param parent 父节点
     */
    public void attach(TreeNode<T> parent){
        this.level = parent.getLevel()+1;
        this.parent = parent;
        parent.childList.add(this);
    }

    public int getLevel(){
        if(parent == null)
            return 0;
        return level;
    }




}
