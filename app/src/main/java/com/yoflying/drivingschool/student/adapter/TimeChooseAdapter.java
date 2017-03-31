package com.yoflying.drivingschool.student.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoflying.drivingschool.R;

import java.util.ArrayList;
import java.util.List;

/**学员预约课程时间选择适配器
 * Created by yaojiulong on 2017/3/27.
 */

public class TimeChooseAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public TimeChooseAdapter(List<String> data) {
        super(R.layout.item_tag_time, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_tag_time, item);
    }
}
