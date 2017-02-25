package com.yoflying.drivingschool.admin.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.entity.Person;

import java.util.List;

/**学员管理  学员列表适配器
 * Created by yaojiulong on 2017/1/14.
 */

public class StudentsManageAdapter extends BaseQuickAdapter<Person,BaseViewHolder>  {


    public StudentsManageAdapter(List<Person> mStudents) {
        super(R.layout.item_manage_student, mStudents);

    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {
        helper.setText(R.id.item_student_name_tv,item.getName())
                .setText(R.id.item_student_course_tv,""+item.getCourse());

        if (item.getCoachId()>0){
            helper.setBackgroundRes(R.id.item_student_bind_btn,R.color.item_bind_ok_color);
            helper.setText(R.id.item_student_bind_btn,R.string.item_manage_student_bind_ok);
        }else {
            helper.setBackgroundRes(R.id.item_student_bind_btn,R.color.item_bing_no_color);
            helper.setText(R.id.item_student_bind_btn,R.string.item_manage_teacher_bind_no);
        }
        helper.addOnClickListener(R.id.item_student_bind_btn);
    }


}
