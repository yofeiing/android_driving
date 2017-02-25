package com.yoflying.drivingschool.admin.configmange.presenter;

import com.yoflying.drivingschool.admin.configmange.Iview.IMaualConfigView;
import com.yoflying.drivingschool.base.BasePresenter;

/**
 * 自动预约配置控制器
 * Created by yaojiulong on 2017/2/20.
 */

public class MaualConfigPresenter extends BasePresenter<IMaualConfigView> {
    private IMaualConfigView mManualConfigView;

    public MaualConfigPresenter(IMaualConfigView mManualConfigView) {
        this.mManualConfigView = mManualConfigView;
    }
}
