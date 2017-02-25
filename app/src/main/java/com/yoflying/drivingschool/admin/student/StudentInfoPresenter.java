package com.yoflying.drivingschool.admin.student;

import com.yoflying.drivingschool.base.BasePresenter;

/**
 * Created by yaojiulong on 2017/2/17.
 */

public class StudentInfoPresenter extends BasePresenter<IStutentInfoView> {
    private IStutentInfoView mInfoView;

    public StudentInfoPresenter(IStutentInfoView mInfoView) {
        this.mInfoView = mInfoView;
        attachView(mInfoView);
    }

}
