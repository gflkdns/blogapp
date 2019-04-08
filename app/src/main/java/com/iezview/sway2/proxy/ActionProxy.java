package com.iezview.sway2.proxy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
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

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


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

        showTS();
    }

    private void showTS() {
        if (!SPUtils.getInstance().getBoolean("isShowTS", true)) {
            return;
        }
        new AlertDialog.Builder(mActy)
                .setTitle("使用方法")
                .setMessage(
                        "1. 打开一个视频平台。\n" +
                                "2. 打开想要观看的视频页面等待加载完成。\n" +
                                "3. 屏幕向右滑出侧边栏，选择线路耐心等待一会解析加载结束播放就可以了。不同线路可能对平台的支持性不同，如果资源无法播放，可以多尝试其它的线路。\n" +
                                "注意：app线路配置需要网络获取，因此请保持手机网络畅通。"
                )
                .setNegativeButton("不在提示", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SPUtils.getInstance().put("isShowTS", false);
                    }
                }).setPositiveButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
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
        ref_layout.setRefreshing(false);
    }

    private void getData() {
        BmobQuery<Action> query = new BmobQuery<>();
        query.findObjects(new ActionFindListener());
    }

    public class ActionFindListener extends FindListener<Action> {
        @Override
        public void done(List<Action> object, BmobException e) {
            if (e == null) {
                data.clear();
                data.addAll(object);
                adapter.notifyDataSetChanged();
            } else {
                ToastUtils.showShort("获取平台信息失败：" + e.getMessage());
            }
        }
    }
}
