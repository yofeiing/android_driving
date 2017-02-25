package com.yoflying.drivingschool.modules.login;

import com.yoflying.drivingschool.entity.User;

/**登录事件
 * Created by yaojiulong on 2016/12/21.
 */

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}
