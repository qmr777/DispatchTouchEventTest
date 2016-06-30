package com.example.administrator.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.customview.Bean.Bean1;
import com.example.administrator.customview.Bean.Bean2;
import com.example.administrator.customview.Tree.TreeHelper;
import com.example.administrator.customview.expandRecyclerView.Adapter2;
import com.example.administrator.customview.expandRecyclerView.CustomTreeNode;
import com.example.administrator.customview.expandRecyclerView.ERVAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    boolean flag = true;
    float x = 0,y = 0;
    List<CustomTreeNode<Bean1>> list = new ArrayList<>();
    CustomTreeNode<Bean1> root;
    List<Bean2.ResultlistBean.PersonBean> personBeanList = new ArrayList<>();

    Bean2 bean2;

    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initJavaBean2();
        ERVAdapter adapter = new ERVAdapter(this,root);
        adapter.setOnItemClickListener(new ERVAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        recyclerView.setAdapter(adapter);
        //recyclerView.setAdapter(new Adapter2(this,personBeanList));
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
        TreeHelper.traverse(root,list);
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

    /*class RvAdapter extends RecyclerView.Adapter<RvAdapter.Myholder> {
        List<String> list;

        RvAdapter(List<String> s){
            this.list = s;

        }

        @Override
        public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Myholder(LayoutInflater.from(MainActivity.this).inflate(R.layout.cell,null));
        }

        @Override
        public void onBindViewHolder(Myholder holder, int position) {
            holder.myView2.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class Myholder extends RecyclerView.ViewHolder{
            MyView2 myView2;

            public Myholder(View itemView) {
                super(itemView);
                myView2 = (MyView2) itemView.findViewById(R.id.mv2);

            }
        }
    }*/

    /*    @Override
        public boolean onTouchEvent(MotionEvent event) {
            Log.e(MainActivity.TAG,"MainAty接收event "+event.getAction());
            return false;
        }*/
    void initView(){

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
