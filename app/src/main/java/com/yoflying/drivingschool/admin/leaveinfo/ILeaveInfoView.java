package com.yoflying.drivingschool.admin.leaveinfo;

import com.yoflying.drivingschool.entity.LeaveInfo;

import java.util.List;

/**
 * Created by yaojiulong on 2017/2/7.
 */

public interface ILeaveInfoView {
    void showRefresh();
    void stopRefresh();
    void showMessage(String msg);
    void setLeveInfoData(List<LeaveInfo> data);
}
