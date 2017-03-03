package com.yoflying.drivingschool.admin.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.entity.CourseConfig;

import java.util.List;

/**dialog显示一天预约配置适配器
 * Created by yaojiulong on 2017/2/28.
 */

public class OneDayCourseConfigAdapter extends BaseItemDraggableAdapter<CourseConfig,BaseViewHolder> {

    public OneDayCourseConfigAdapter(List<CourseConfig> data) {
        super(R.layout.item_course_config,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseConfig item) {
        switch (item.getTestCourse()){
            case 2:
                helper.setText(R.id.item_course_config_type,"科目二");
                break;
            case 3:
                helper.setText(R.id.item_course_config_type,"科目三");
                break;
        }
    }
}
