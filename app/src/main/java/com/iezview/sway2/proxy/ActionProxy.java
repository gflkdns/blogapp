package com.iezview.sway2.proxy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iezview.sway2.model.ActionModel;
import com.iezview.sway2.R;
import com.iezview.sway2.adapter.TAdapter;
import com.iezview.sway2.adapter.houlder.ActionHolder;
import com.iezview.sway2.bean.Action;
import com.miqt.wand.activity.ActivityProxy;
import com.miqt.wand.activity.ProxyActivity;
import com.miqt.wand.anno.AddToFixPatch;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by t54 on 2019/4/3.
 */
@AddToFixPatch
public class ActionProxy extends BaseProxy implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView rv_list;
    SwipeRefreshLayout ref_layout;
    private List<Action> data;
    private TAdapter<ActionHolder> adapter;

    public ActionProxy(ProxyActivity acty) {
        super(acty);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mActy.setContentView(R.layout.activity_action);

        this.rv_list = mActy.findViewById(R.id.rv_list);
        this.ref_layout = mActy.findViewById(R.id.ref_layout);

        rv_list.setLayoutManager(new GridLayoutManager(mActy, 4));
        data = new ArrayList<>();
        adapter = new TAdapter<ActionHolder>(data, mActy, R.layout.item_action, ActionHolder.class);
        rv_list.setAdapter(adapter);
        ref_layout.setOnRefreshListener(this);

        getData();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public void onRefresh() {
        getData();
    }

    private void getData() {
        ActionModel model = new ActionModel();
        data.clear();
        data.addAll(model.getActions());
        adapter.notifyDataSetChanged();
    }
}
