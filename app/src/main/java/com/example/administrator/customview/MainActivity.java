package com.example.administrator.customview;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.customview.Bean.Bean1;
import com.example.administrator.customview.Bean.Bean2;
import com.example.administrator.customview.DragRecyclerView.Adapter3;
import com.example.administrator.customview.Tree.TreeHelper;
import com.example.administrator.customview.expandRecyclerView.CustomTreeNode;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    boolean flag = true;
    float x = 0,y = 0;
    SwipeRefreshLayout swipeRefreshLayout;
    List<CustomTreeNode<Bean1>> list = new ArrayList<>();
    List<String> strings = new ArrayList<>();
    CustomTreeNode<Bean1> root;
    List<Bean2.ResultlistBean.PersonBean> personBeanList = new ArrayList<>();

    Handler mhandler = new Handler();
    //Bean2 bean2;

    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //initJavaBean2();
        /*ERVAdapter adapter = new ERVAdapter(this,root);
        adapter.setOnItemClickListener(new ERVAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });*/

        for(int i = 0;i<20;i++)
            strings.add("第"+i+"项");
        /*MyAdapter adapter1 = new MyAdapter(this,strings);
        adapter1.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this,strings.get(position) + " short",Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongClick(int position) {
                Toast.makeText(MainActivity.this,strings.get(position) + " long",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        recyclerView.addOnItemTouchListener(adapter1);*/
        Adapter3 adapter3 = new Adapter3(MainActivity.this,strings);
        recyclerView.addOnItemTouchListener(adapter3);
        recyclerView.setAdapter(adapter3);

    }

    void initData(){
        root = new CustomTreeNode<>(new Bean1("root"));
        for(int i = 1;i<4;i++){
            CustomTreeNode<Bean1> node = new CustomTreeNode<>(new Bean1("层级1，子节点"+i));
            node.attach(root);
            Log.w("TAG",node.data.data);
        }
        Log.w("TAG",root.childList.size()+"");
        for(int i = 0;i<4;i++){
            CustomTreeNode<Bean1> node = new CustomTreeNode<>(new Bean1("层级2，子节点"+i));
            node.attach(root.childList.get(0));
        }

        for(int i = 0;i<4;i++){
            CustomTreeNode<Bean1> node = new CustomTreeNode<>(new Bean1("层级3，子节点"+i));
            node.attach(root.childList.get(0).childList.get(0));
        }
        TreeHelper.traverseToList(root,list);
    }

    void initJavaBean2(){
        String json = "{\"code\":\"200\",\"msg\":\"查询成功\",\"type\":\"groupper\",\"resultlist\":[{\"Gr" +
                "oupID\":99999999,\"GroupName\":\"未分小组\",\"Person\":[{\"UserID\":1,\"MIUserName\":\"1001" +
                "4AAC2F4FAD043E6BF71E30C34DEC\",\"MIPassWord\":\"123456\",\"MIPersonName\":\"刘某某\",\"MIPhot" +
                "o\":null,\"UserOrderBy\":null},{\"UserID\":2,\"MIUserName\":\"F3CAE1A97FF0A2F37B26D291F14527" +
                "5D\",\"MIPassWord\":\"123456\",\"MIPersonName\":\"张某某\",\"MIPhoto\":null,\"UserOrderBy\":" +
                "null},{\"UserID\":6,\"MIUserName\":\"96569B79169E0389AF38C9672838A397\",\"MIPassWord\":\"12" +
                "3456\",\"MIPersonName\":\"老陈\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"UserID\":15,\"MIU" +
                "serName\":\"9AE88FA6A271C1D6AB7E6D7562C1D7B1\",\"MIPassWord\":\"123456\",\"MIPersonName\":\"" +
                "测试人员1\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"UserID\":16,\"MIUserName\":\"20A82FBF1B" +
                "70B1D12C653AC695A16EA9\",\"MIPassWord\":\"123456\",\"MIPersonName\":\"测试人员2\",\"MIPhoto\":" +
                "null,\"UserOrderBy\":null},{\"UserID\":19,\"MIUserName\":\"D7297E05257A0F7F6D2DC26D95F67A2" +
                "2\",\"MIPassWord\":\"123456\",\"MIPersonName\":\"测试人员3\",\"MIPhoto\":null,\"UserOrderBy\":" +
                "null},{\"UserID\":20,\"MIUserName\":\"9CA78DBDDF5872C1C1920BB9122D5854\",\"MIPassWord\":\"12" +
                "3456\",\"MIPersonName\":\"测试人员4\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"UserID\":21,\"MIU" +
                "serName\":\"1DF444A16C5B236ABF9E3EF717805D11\",\"MIPassWord\":\"123456\",\"MIPersonName\":\"测试" +
                "人员5\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"UserID\":22,\"MIUserName\":\"163D94508AEB3D56" +
                "5F8B92C2E7A7BED0\",\"MIPassWord\":\"123456\",\"MIPersonName\":\"测试人员6\",\"MIPhoto\":null,\"Us" +
                "erOrderBy\":null},{\"UserID\":23,\"MIUserName\":\"CC84CC5454D4B6E6C1212BCF4EB64BD5\",\"MIPass" +
                "Word\":\"123456\",\"MIPersonName\":\"测试人员7\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"Use" +
                "rID\":24,\"MIUserName\":\"4C9F03D7F35943DC33AFDC2676A76B61\",\"MIPassWord\":\"123456\",\"MIPe" +
                "rsonName\":\"胡军\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"UserID\":25,\"MIUserName\":\"73" +
                "BAF690A3782F4AE912B7859194F072\",\"MIPassWord\":\"drf0001\",\"MIPersonName\":\"杜润峰\",\"MIPho" +
                "to\":null,\"UserOrderBy\":null},{\"UserID\":27,\"MIUserName\":\"38E942009BCEA7B09EED95AF4F06DF" +
                "84\",\"MIPassWord\":\"85066910\",\"MIPersonName\":\"朱民\",\"MIPhoto\":null,\"UserOrderBy\":nu" +
                "ll},{\"UserID\":28,\"MIUserName\":\"42DCF32FD8C4C0435FA4D6D407A71BD9\",\"MIPassWord\":\"78121" +
                "0\",\"MIPersonName\":\"巩彬\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"UserID\":29,\"MIUserN" +
                "ame\":\"C20B926FA5B4DE82B26EEC3BD0865D66\",\"MIPassWord\":\"031113\",\"MIPersonName\":\"杨慧" +
                "玉\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"UserID\":30,\"MIUserName\":\"E4B856BCF20209127A" +
                "ADC8282B94029D\",\"MIPassWord\":\"761228\",\"MIPersonName\":\"白卫民\",\"MIPhoto\":null,\"UserO" +
                "rderBy\":null},{\"UserID\":32,\"MIUserName\":\"E3BC8863D954B2D18463220EAAB92460\",\"MIPassWo" +
                "rd\":\"198709\",\"MIPersonName\":\"田振国\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"User" +
                "ID\":33,\"MIUserName\":\"5BA963A50B15A107BB33AB010D71745E\",\"MIPassWord\":\"8291699\",\"MIPe" +
                "rsonName\":\"武倩倩\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"UserID\":34,\"MIUserName\":\"AEFFDF" +
                "4F0CA5A2B5B183FAECDD42D764\",\"MIPassWord\":\"0613wakr\",\"MIPersonName\":\"王超\",\"MIPhoto\":nu" +
                "ll,\"UserOrderBy\":null},{\"UserID\":36,\"MIUserName\":\"10DD9D9D10CF86BC1D86078B6DAF6C93\",\"MI" +
                "PassWord\":\"20030328sjy\",\"MIPersonName\":\"宋珊\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"Use" +
                "rID\":38,\"MIUserName\":\"0DF49E3F6F4743D0677880A77AB57D10\",\"MIPassWord\":\"85061997\",\"MIPerso" +
                "nName\":\"陆健\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"UserID\":39,\"MIUserName\":\"CA3133187" +
                "77E2FDEB5032D57967A4DD\",\"MIPassWord\":\"85061997\",\"MIPersonName\":\"李春阳\",\"MIPhoto\":null,\"Use" +
                "rOrderBy\":null},{\"UserID\":40,\"MIUserName\":\"46318D7FB26B125093EDF5F0A3F571CB\",\"MIPassWord\":\"85" +
                "0850\",\"MIPersonName\":\"靳志红\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"UserID\":41,\"MIUserN" +
                "ame\":\"48B373AF66645787BD8ED3E0ADA385DF\",\"MIPassWord\":\"007517\",\"MIPersonName\":\"陈涛\",\"MIP" +
                "hoto\":null,\"UserOrderBy\":null},{\"UserID\":43,\"MIUserName\":\"A95BFDF8DCD97891DAB386D50A7B0C" +
                "50\",\"MIPassWord\":\"asdfghj\",\"MIPersonName\":\"左占平\",\"MIPhoto\":null,\"UserOrderBy\":null" +
                "},{\"UserID\":44,\"MIUserName\":\"CED0212865B40FFB84593F8F3D2CD303\",\"MIPassWord\":\"111111\",\"MIPe" +
                "rsonName\":\"杜红\",\"MIPhoto\":null,\"UserOrderBy\":null},{\"UserID\":45,\"MIUserName\":\"BCF9D50B62" +
                "D07FFEAD447A4E89E7F98E\",\"MIPassWord\":\"910531\",\"MIPersonName\":\"傅梅君\",\"MIPhoto\":null,\"Use" +
                "OrderBy\":null}]}]}";
        try {
            Gson gson = new Gson();
            JSONObject j1 = new JSONObject(json);
            JSONArray j2 = j1.getJSONArray("Person");
            for(int i = 0;i<j2.length();i++){
                JSONObject jj = j2.getJSONObject(i);
            }
        } catch (Exception e){
            e.printStackTrace();
        }



        //CustomTreeNode<>



    }


    void initView(){


        recyclerView = (RecyclerView) findViewById(R.id.rv);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                boolean tryToLoadMore = newState == RecyclerView.SCROLL_STATE_DRAGGING &&
                        ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition()
                                == recyclerView.getAdapter().getItemCount()-1;

                Log.e("TAG1",newState+"" + tryToLoadMore + " "+recyclerView.getAdapter().getItemCount()+" "
                        +((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition());
                if(tryToLoadMore){//最后一个可见 并且是拖动
                    swipeRefreshLayout.setRefreshing(true);
                    Toast.makeText(MainActivity.this,"加载更多",Toast.LENGTH_SHORT).show();
                    mhandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0;i<5;i++)
                                strings.add("第" + strings.size() + "项");
                            recyclerView.getAdapter().notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }, 4000);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_purple);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                for(int i = 0;i<5;i++) {
                    strings.add("第" + strings.size() + "项");
                    mhandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                            recyclerView.getAdapter().notifyDataSetChanged();
                        }
                    }, 6000);
                }
            }
        });

    }

}
