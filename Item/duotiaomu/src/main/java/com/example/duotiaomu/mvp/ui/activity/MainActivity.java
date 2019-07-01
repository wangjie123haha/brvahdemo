package com.example.duotiaomu.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.HttpAuthHandler;

import com.example.duotiaomu.R;
import com.example.duotiaomu.adapter.DuoTiaoMuAdapter;
import com.example.duotiaomu.bean.News;
import com.example.duotiaomu.di.component.DaggerMainComponent;
import com.example.duotiaomu.mvp.contract.MainContract;
import com.example.duotiaomu.mvp.presenter.MainPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.header.FunGameBattleCityHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 01/19/2019 20:17
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.recyclervirew)
    RecyclerView recyclervirew;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;
    private  int page=1;
    private HashMap<String, Integer> hashMap;
    private DuoTiaoMuAdapter duoTiaoMuAdapter;
    private ArrayList<News.DataBean> list=null;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        smart();
        hashMap = new HashMap<>();
        hashMap.put("page",page);
        mPresenter.showData(hashMap);
    }
  //smartrefresh
    private void smart() {
        smartRefresh.setEnableLoadMore(true);
        smartRefresh.setEnableRefresh(true);
        //smartRefresh.setEnableAutoLoadMore(false);
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                Log.d("xxx", "page:" + page);
                hashMap = new HashMap<>();
                hashMap.put("page",page);
                mPresenter.showData(hashMap);
                duoTiaoMuAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败

            }
        });
        smartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page+=1;
                Log.d("xxx", "page:" + page);
                hashMap = new HashMap<>();
                hashMap.put("page",page);
                mPresenter.showData(hashMap);
                duoTiaoMuAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败

            }
        });
        //设置 Header 为 贝塞尔雷达 样式
        smartRefresh.setRefreshHeader(new FunGameBattleCityHeader(this));
      //设置 Footer 为 球脉冲 样式
        smartRefresh.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void showView(News news) {
        List<News.DataBean> data = news.getData();
        if (page==1){
           /* list.clear();*/
            list = new ArrayList<>();
        }
        list.addAll(data);
        duoTiaoMuAdapter = new DuoTiaoMuAdapter(list);
        recyclervirew.setAdapter(duoTiaoMuAdapter);
        duoTiaoMuAdapter.notifyDataSetChanged();
        smartRefresh.finishLoadMore();
        smartRefresh.finishRefresh();
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclervirew.setLayoutManager(manager);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
