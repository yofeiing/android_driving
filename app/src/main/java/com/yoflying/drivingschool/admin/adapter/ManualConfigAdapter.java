package com.yoflying.drivingschool.admin.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.entity.CourseConfig;

import java.util.List;
import java.util.Map;

/**手动预约配置适配器
 * Created by yaojiulong on 2017/2/27.
 */

public class ManualConfigAdapter extends BaseQuickAdapter<Map<String,List<CourseConfig>>,BaseViewHolder>{


    public ManualConfigAdapter(List<Map<String, List<CourseConfig>>> data) {
        super(R.layout.item_manual_config,data);

    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, List<CourseConfig>> item) {
        String time = "";
        for (String key : item.keySet()){
            time=key;
        }
        helper.setText(R.id.item_manual_config_time,time);
    }
}
