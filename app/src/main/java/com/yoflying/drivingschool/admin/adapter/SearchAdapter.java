package com.yoflying.drivingschool.admin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**搜索适配器
 * Created by yaojiulong on 2017/2/9.
 */

public class SearchAdapter extends BaseQuickAdapter<Person,BaseViewHolder> {

    public SearchAdapter( List<Person> data) {
        super(R.layout.item_search_result, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {
        helper.setText(R.id.item_search_name,item.getName());
        if (item.getDiscern()==1){

        }
    }

}
