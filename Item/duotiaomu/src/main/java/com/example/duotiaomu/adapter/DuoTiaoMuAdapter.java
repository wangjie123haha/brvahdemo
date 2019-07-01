package com.example.duotiaomu.adapter;

import android.net.Uri;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.duotiaomu.R;
import com.example.duotiaomu.bean.News;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class DuoTiaoMuAdapter extends BaseMultiItemQuickAdapter<News.DataBean, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DuoTiaoMuAdapter(List<News.DataBean> data) {
        super(data);

        addItemType(News.DataBean.TEXT, R.layout.one);
        addItemType(News.DataBean.IMG,R.layout.two);
    }

    @Override
    protected void convert(BaseViewHolder helper, News.DataBean item) {

         switch (helper.getItemViewType()){
             case News.DataBean.TEXT:
                 helper.setText(R.id.tv_01,item.getTitle());
                 SimpleDraweeView my_image_view1 = helper.getView(R.id.my_image_view1);
                 Uri uri = Uri.parse(item.getThumbnail_pic_s());
                 my_image_view1.setImageURI(uri);
                 break;
             case News.DataBean.IMG:
                 helper.setText(R.id.tv_02,item.getTitle());
                 SimpleDraweeView my_image_view2 = helper.getView(R.id.my_image_view2);
                 SimpleDraweeView my_image_view3 = helper.getView(R.id.my_image_view3);
                 SimpleDraweeView my_image_view4 = helper.getView(R.id.my_image_view4);
                 Uri uri2 = Uri.parse(item.getThumbnail_pic_s());
                 my_image_view2.setImageURI(uri2);
                 Uri uri3 = Uri.parse(item.getThumbnail_pic_s02());
                 my_image_view3.setImageURI(uri3);
                 Uri uri4 = Uri.parse(item.getThumbnail_pic_s03());
                 my_image_view4.setImageURI(uri4);
                 break;
         }
    }
}
