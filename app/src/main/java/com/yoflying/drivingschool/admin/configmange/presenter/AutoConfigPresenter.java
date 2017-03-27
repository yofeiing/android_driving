package com.yoflying.drivingschool.admin.configmange.presenter;


import com.yoflying.drivingschool.admin.configmange.Iview.IAutoConfigView;
import com.yoflying.drivingschool.base.BasePresenter;

/**手动预约配置控制器

 * Created by yaojiulong on 2017/2/20.
 */

public class AutoConfigPresenter extends BasePresenter<IAutoConfigView> {
    private IAutoConfigView mAutoConfigView;

    public AutoConfigPresenter(IAutoConfigView mAutoConfigView) {
        this.mAutoConfigView = mAutoConfigView;
        attachView(mAutoConfigView);
    }

}
