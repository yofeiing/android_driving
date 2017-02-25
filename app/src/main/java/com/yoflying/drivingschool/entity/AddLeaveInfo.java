package com.yoflying.drivingschool.entity;

/**添加请假信息实体类
 * Created by yaojiulong on 2017/2/7.
 */

public class AddLeaveInfo {


    /**
     * coachId : 6
     * status : 1
     * leaveDate2 : 2016-12-23 15:25:13
     * leaveDate3 : 2016-12-25 10:25:13
     */

    private int coachId;
    private int status;
    private String leaveDate2;
    private String leaveDate3;
    private String coachName;

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLeaveDate2() {
        return leaveDate2;
    }

    public void setLeaveDate2(String leaveDate2) {
        this.leaveDate2 = leaveDate2;
    }

    public String getLeaveDate3() {
        return leaveDate3;
    }

    public void setLeaveDate3(String leaveDate3) {
        this.leaveDate3 = leaveDate3;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }
}
