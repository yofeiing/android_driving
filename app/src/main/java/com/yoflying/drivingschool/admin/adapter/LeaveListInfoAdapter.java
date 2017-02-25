package com.yoflying.drivingschool.admin.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoflying.drivingschool.DriverApplication;
import com.yoflying.drivingschool.R;
import com.yoflying.drivingschool.entity.LeaveInfo;

import java.util.List;

/**请假列表适配器
 * Created by yaojiulong on 2017/2/7.
 */

public class LeaveListInfoAdapter extends BaseQuickAdapter<LeaveInfo,BaseViewHolder> {
    public LeaveListInfoAdapter(List<LeaveInfo> data) {
        super(R.layout.item_leaveinfo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LeaveInfo item) {
        helper.setText(R.id.item_leave_name_tv,item.getCoachName());
        helper.setText(R.id.item_leave_start_time_tv,item.getLeaveDate2());
        helper.setText(R.id.item_leave_end_time_tv,item.getLeaveDate3());

        switch (item.getStatus()){
            case 0:
                helper.setBackgroundRes(R.id.item_leave_status_btn,R.drawable.status_leave_end_bg);
                helper.setBackgroundColor(R.id.item_leave_status_btn,R.string.item_status_leave_end);
                break;
            case 1:
                helper.setBackgroundRes(R.id.item_leave_status_btn, R.drawable.status_leave_ing_bg);
                helper.setText(R.id.item_leave_status_btn,R.string.item_status_leave_ing);
                break;
            case 2:
                helper.setBackgroundRes(R.id.item_leave_status_btn,R.drawable.status_leave_no_start_bg);
                helper.setText(R.id.item_leave_status_btn,R.string.item_status_leave_no_start);
                break;
        }
    }
}
