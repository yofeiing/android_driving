package com.yoflying.drivingschool.admin.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**教练管理适配器
 * Created by yaojiulong on 2017/1/16.
 */

public class TeachersManageAdapter extends BaseQuickAdapter<Person,BaseViewHolder> {

    public TeachersManageAdapter( List<Person> data) {
        super(R.layout.item_manage_teacher, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {
        helper.setText(R.id.item_teacher_name_tv,item.getName());
    }
}
