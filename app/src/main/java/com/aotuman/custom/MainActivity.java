package com.aotuman.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<String>();
    private StaggeredHomeAdapter adapter;
    private TwinklingRefreshLayout twinklingRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();

        initEvent();
    }

    private void initEvent() {
         adapter = new StaggeredHomeAdapter(MainActivity.this,list);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickLitener(new StaggeredHomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"is be click"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,"is be click long"+position,Toast.LENGTH_SHORT).show();
            }
        });

        View view = LayoutInflater.from(this).inflate(R.layout.recyclerview_header,null);
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.rel);
        recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView2.setAdapter(new SimpAdapter(this,list));
        twinklingRefreshLayout.setPureScrollModeOn(true);
        twinklingRefreshLayout.addFixedExHeader(view);
        recyclerView.setPadding(0,150,0,0);
        recyclerView.requestLayout();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        twinklingRefreshLayout = (TwinklingRefreshLayout) findViewById(R.id.activity_main);
    }

    private void initData() {
        for (int i = 'A'; i <= 'Z'; i++){
            list.add(String.valueOf((char)i));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_list:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new SimpAdapter(this,list));
                Toast.makeText(MainActivity.this, ""+"ListView", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_grid:
                recyclerView.setLayoutManager(new GridLayoutManager(this,2));
                recyclerView.setAdapter(new SimpAdapter(this,list));
                Toast.makeText(MainActivity.this, ""+"GridView", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_water:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);
                Toast.makeText(MainActivity.this, ""+"WaterFallView", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
