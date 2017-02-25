package com.yoflying.drivingschool.admin.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**绑定教练适配器
 * Created by yaojiulong on 2017/1/18.
 */

public class BindTeachersAdapter extends BaseQuickAdapter<Person,BaseViewHolder> {
    public BindTeachersAdapter(List<Person> data) {
        super(R.layout.item_bind_teacher_layout,data);

    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {
        helper.setText(R.id.item_bind_teacher_name,item.getName());
        helper.addOnClickListener(R.id.item_bind_teacher_name);
    }
}
