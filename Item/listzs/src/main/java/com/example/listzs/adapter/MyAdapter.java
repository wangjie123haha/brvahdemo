package com.example.listzs.adapter;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.listzs.R;
import com.example.listzs.bean.User;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class MyAdapter extends BaseQuickAdapter<User.ResultsBean,BaseViewHolder> {


    public MyAdapter(int layoutResId, @Nullable List<User.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, User.ResultsBean item) {
         helper.setText(R.id.text_view,item.getType());
         SimpleDraweeView helperView = helper.getView(R.id.my_image_view);
         Uri uri = Uri.parse(item.getUrl());
         helperView.setImageURI(uri);
    }
}
