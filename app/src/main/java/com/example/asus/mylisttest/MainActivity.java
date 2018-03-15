package com.example.asus.mylisttest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,Object>> data;
    private String[] from = {"title", "cont", "img"};
    private int[] to = {R.id.item_title,R.id.item_cont,R.id.item_img};

    private int[] img = {R.drawable.banana,R.drawable.baozi,
                        R.drawable.breakfast,R.drawable.cereal,
                        R.drawable.espresso,R.drawable.hamburger,
                        R.drawable.noodles,R.drawable.apple,
                        R.drawable.cheese,R.drawable.toastmarmalae};

    private int removeIndex = -1; //我們index 從0開始 所以預設值給他-1
    private int dataIndex = 1; //從1開始 第1個資料 第2個資料 第3個資料

//    private String[] title = {"早餐","午餐","晚餐"};
//    private String[] cont = {"在家裡吃","去外面吃","去百貨公司吃"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        data = new LinkedList<>();
        initListView();

    }//onCreate

    private void initListView(){
        HashMap<String,Object> data0 = new HashMap<>();
        data0.put(from[0],"早餐");     //title
        data0.put(from[1],"吃漢堡");   //cont
        data0.put(from[2],img[5]);
        data0.put("index", "" + dataIndex++);  //index
        data0.put("detail","我是細節  漢堡");  //detail

        data.add(data0);

        HashMap<String,Object> data1 = new HashMap<>();
        data1.put(from[0],"午餐");              //title
        data1.put(from[1],"隨便吃 產生食物亂數");//cont
        data1.put(from[2],img[(int)(Math.random()*img.length)]);
        data1.put("index", "" + dataIndex++);  //index
        data1.put("detail","我是細節  隨便的");  //detail
        data.add(data1);

        HashMap<String,Object> data2 = new HashMap<>();
        data2.put(from[0],"中式餐點");
        data2.put(from[1],"香蕉");
        data2.put(from[2],img[0]);
        data2.put("index", "" + dataIndex++);  //index
        data2.put("detail","我是細節  香蕉");  //detail
        data.add(data2);

        HashMap<String,Object> data3 = new HashMap<>();
        data3.put(from[0],"西式餐點");
        data3.put(from[1],"麥片");
        data3.put("index", "" + dataIndex++);  //index
        data3.put("detail","我是細節  按鈕新增的");  //detail

        data3.put(from[2],img[3]);
//        data3.put("index", "" + dataIndex++);  //index
//        data3.put("detail","我是細節  按鈕新增的");  //detail
        data.add(data3);

        adapter = new SimpleAdapter(this, data, R.layout.item_layout, from, to);

        listView.setAdapter(adapter);

        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);


        //show detail
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "index =" + position, Toast.LENGTH_SHORT).show();
                gotoDetail(position);

            }
        });//ItemClick


        //del item
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                //removeItem(position);
                removeIndex = position;
                showDelDialog();
                //return false; 預設false
                return true;
            }
        });//ItemLongClick

    }//initLIstView()


    private void gotoDetail(int position){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("index", (String)data.get(position).get("index"));
        intent.putExtra("title", (String)data.get(position).get("title"));
        intent.putExtra("cont", (String)data.get(position).get("cont"));
        intent.putExtra("detail", (String)data.get(position).get("detail"));
        startActivity(intent);
    }


    private void showDelDialog(){
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("確認一下")
                .setMessage("真的要刪除" + data.get(removeIndex).get(from[0]) + "嗎?")
                .setPositiveButton("確定刪除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        removeItem(removeIndex);
                    }
                })
                .setNegativeButton("放棄刪除", null)
                .show();

    }//showDelDialog

    private void removeItem(int index){
        removeIndex = -1; //避險風險  設為-1 就不會刪到  我們一開始是0
        data.remove(index);
        adapter.notifyDataSetChanged();
    }//removeItem


    public void addItem(View view) {
        HashMap<String,Object> dataWhatever = new HashMap<>();
        dataWhatever.put(from[0],"晚餐");
        dataWhatever.put(from[1],"去百貨公司吃 亂數");
        dataWhatever.put("index", "" + dataIndex++);  //index
        dataWhatever.put("detail","我是細節  按鈕新增的");  //detail
        dataWhatever.put(from[2],img[(int)(Math.random()*img.length)]);
        data.add(dataWhatever);

        adapter.notifyDataSetChanged();

    }//addItem


}//CLASS
