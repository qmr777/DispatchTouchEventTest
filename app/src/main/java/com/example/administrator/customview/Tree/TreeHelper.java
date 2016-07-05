package com.example.administrator.customview.Tree;

import com.example.administrator.customview.expandRecyclerView.CustomTreeNode;


import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 *
 */
public class TreeHelper {

    public static <T> void traverseToList(CustomTreeNode<T> node, List<CustomTreeNode<T>> list){
        if(node != null){
            list.add(node);
            if(node.isShowChild()&&node.childList!=null&&node.childList.size()!=0){
                for(CustomTreeNode<T> n:node.childList)
                    traverseToList(n,list);
            }
        }
    }

    public static <T> void traverseToList(TreeNode<T> node, List<TreeNode<T>> list){
        if(node != null){
            list.add(node);
            if(node.childList!=null&&node.childList.size()!=0){
                for(TreeNode<T> n:node.childList)
                    traverseToList(n,list);
            }
        }
    }
}
