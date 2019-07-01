package com.example.listzs.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.listzs.di.module.SecondActivityModule;
import com.example.listzs.mvp.contract.SecondActivityContract;

import com.jess.arms.di.scope.ActivityScope;
import com.example.listzs.mvp.ui.activity.SecondActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 01/19/2019 20:03
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = SecondActivityModule.class, dependencies = AppComponent.class)
public interface SecondActivityComponent {
    void inject(SecondActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SecondActivityComponent.Builder view(SecondActivityContract.View view);

        SecondActivityComponent.Builder appComponent(AppComponent appComponent);

        SecondActivityComponent build();
    }
}